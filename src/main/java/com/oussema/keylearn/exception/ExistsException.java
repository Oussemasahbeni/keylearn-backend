package com.oussema.keylearn.exception;

import java.io.Serial;

public class ExistsException extends ApplicationException {
  @Serial private static final long serialVersionUID = 1152907649742554198L;

  public ExistsException(ExistsExceptionType type) {
    super(type);
  }

  public ExistsException(ExistsExceptionType type, Throwable cause) {
    super(type, cause);
  }

  public ExistsException(ExistsExceptionType type, String message, Throwable cause) {
    super(type, message, cause);
  }

  public ExistsException(
      ExistsExceptionType type, String message, Throwable cause, Object... keyParams) {
    super(type, message, cause, keyParams);
  }

  public ExistsException(ExistsExceptionType type, Object[] valueParams, Object... keyParams) {
    super(type, valueParams, keyParams);
  }

  public ExistsException(
      ExistsExceptionType type, Throwable cause, Object[] valueParams, Object... keyParams) {
    super(type, cause, valueParams, keyParams);
  }

  public ExistsException(ExistsExceptionType type, Object... valueParams) {
    super(type, valueParams);
  }

  public enum ExistsExceptionType implements ExceptionType {
    EMAIL_ALREADY_VERIFIED(
        "error.server.exists.email-already-verified.title",
        "error.server.exists.email-already-verified.msg",
        "Email already verified"),
    USER_ALREADY_EXISTS(
        "error.server.exists.user-already-exists.title",
        "error.server.exists.user-already-exists.msg",
        "User already exists"),

    CONTENT_ALREADY_EXISTS(
        "error.server.exists.content-already-exists.title",
        "error.server.exists.content-already-exists.msg",
        "Content already exists");

    private final String messageKey;
    private final String titleKey;
    private final String messageCause;

    ExistsExceptionType(String titleKey, String messageKey, String messageCause) {
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
