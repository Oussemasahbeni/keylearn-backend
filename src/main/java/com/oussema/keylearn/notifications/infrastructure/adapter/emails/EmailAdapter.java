package com.oussema.keylearn.notifications.infrastructure.adapter.emails;

import com.oussema.keylearn.notifications.domain.enums.EmailType;
import com.oussema.keylearn.notifications.domain.port.output.Emails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class EmailAdapter implements Emails {

  private static final int RETRY_DELAY = 2000;
  private static final int MAX_ATTEMPTS = 4;
  private final JavaMailSender emailSender;
  private final TemplateEngine templateEngine;

  @Value("${spring.mail.username}")
  private String from;

  @Async
  @Retryable(
      retryFor = MessagingException.class,
      backoff = @Backoff(delay = RETRY_DELAY), // Retry after 2 seconds
      maxAttempts = MAX_ATTEMPTS)
  @Override
  public void sendEmail(String emailRecipient, EmailType emailType, Map<String, Object> properties)
      throws MessagingException {
    log.info("Attempting to send email to: {}", emailRecipient);

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    try {
      helper.setSubject(emailType.getSubject());
      helper.setFrom(from);
      helper.setTo(emailRecipient);
      Context context = new Context();
      context.setVariables(properties);
      String template = templateEngine.process(emailType.getTemplateName(), context);
      helper.setText(template, true);
      emailSender.send(message);
      log.info("Email sent successfully to: {}", emailRecipient);
    } catch (MessagingException e) {
      log.error("Failed to send email to: {}", emailRecipient, e);
      throw e;
    }
  }

  @Recover
  public void recover(
      MessagingException e, String emailRecipient, String code, EmailType emailType) {
    log.error("Failed to send email after retries to: {}", emailRecipient, e);
  }
}
