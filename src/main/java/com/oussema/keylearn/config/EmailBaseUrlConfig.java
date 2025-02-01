package com.oussema.keylearn.config;//package com.oussema.keylearn.config;

import com.oussema.keylearn.notifications.domain.enums.EmailType;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class EmailBaseUrlConfig {

  @Value("${application.frontend.url}")
  private String frontendUrl;

  @PostConstruct
  public void init() {
    log.info("Setting frontend URL for email types on the url: {}", frontendUrl);
    EmailType.setFrontendUrl(frontendUrl);
  }
}
