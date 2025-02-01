package com.oussema.keylearn.exception;

import java.io.Serial;

public class BadRequestException extends ApplicationException {
  @Serial private static final long serialVersionUID = 1152907649742554198L;

  public BadRequestException(BadRequestExceptionType type) {
    super(type);
  }

  public BadRequestException(BadRequestExceptionType type, Throwable cause) {
    super(type, cause);
  }

  public BadRequestException(BadRequestExceptionType type, String message, Throwable cause) {
    super(type, message, cause);
  }

  public BadRequestException(
      BadRequestExceptionType type, String message, Throwable cause, Object... keyParams) {
    super(type, message, cause, keyParams);
  }

  public BadRequestException(
      BadRequestExceptionType type, Object[] valueParams, Object... keyParams) {
    super(type, valueParams, keyParams);
  }

  public BadRequestException(
      BadRequestExceptionType type, Throwable cause, Object[] valueParams, Object... keyParams) {
    super(type, cause, valueParams, keyParams);
  }

  public BadRequestException(BadRequestExceptionType type, Object... valueParams) {
    super(type, valueParams);
  }

  public enum BadRequestExceptionType implements ExceptionType {
    PASSWORDS_DO_NOT_MATCH(
        "error.server.bad-request.passwords-do-not-match.title",
        "error.server.bad-request.passwords-do-not-match.msg",
        "Passwords do not match"),
    WRONG_PASSWORD(
        "error.server.bad-request.wrong-password.title",
        "error.server.bad-request.wrong-password.msg",
        "Wrong password"),
    INVALID_REQUEST(
        "error.server.bad-request.invalid-request.title",
        "error.server.bad-request.invalid-request.msg",
        "Invalid request"),
    TOKEN_USED(
        "error.server.bad-request.token-used.title",
        "error.server.bad-request.token-used.msg",
        "Token is already used"),
    INVALID_TOKEN(
        "error.server.bad-request.invalid-token.title",
        "error.server.bad-request.invalid-token.msg",
        "Invalid token"),
    NO_PASSWORD(
        "error.server.bad-request.no-password.title",
        "error.server.bad-request.no-password.msg",
        "No password provided"),
    RECAPTCHA_INVALID(
        "error.server.bad-request.recaptcha-invalid.title",
        "error.server.bad-request.recaptcha-invalid.msg",
        "ReCAPTCHA validation failed"),
    INVALID_SCHEDULED_DATE(
        "error.server.bad-request.invalid-scheduled-date.title",
        "error.server.bad-request.invalid-scheduled-date.msg",
        "Scheduled date is in the past");

    private final String messageKey;
    private final String titleKey;
    private final String messageCause;

    BadRequestExceptionType(String titleKey, String messageKey, String messageCause) {
      this.messageKey = messageKey;
      this.titleKey = titleKey;
      this.messageCause = messageCause;
    }

    @Override
    public String getTitleKey() {
      return titleKey;
    }

    @Override
    public String getMessageKey() {
      return messageKey;
    }

    @Override
    public String getMessageCause() {
      return messageCause;
    }
  }
}
