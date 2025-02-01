package com.oussema.keylearn.exception;

import java.io.Serial;

public class ForbiddenException extends ApplicationException {

  @Serial private static final long serialVersionUID = 5477619054099558741L;

  public ForbiddenException(ExceptionType type) {
    super(type);
  }

  protected ForbiddenException(ExceptionType type, Object[] valueParams, Object... keyParams) {
    super(type, valueParams, keyParams);
  }

  protected ForbiddenException(
      ExceptionType type, Throwable cause, Object[] valueParams, Object... keyParams) {
    super(type, cause, valueParams, keyParams);
  }

  protected ForbiddenException(ExceptionType type, Throwable cause) {
    super(type, cause);
  }

  protected ForbiddenException(
      ExceptionType type, String message, Throwable cause, Object... keyParams) {
    super(type, message, cause, keyParams);
  }

  protected ForbiddenException(ExceptionType type, Throwable cause, Object... valueParams) {
    super(type, cause, valueParams);
  }

  public ForbiddenException(ExceptionType type, String message) {
    super(type, message);
  }

  public enum ForbiddenExceptionType implements ExceptionType {
    GENERIC(
        "error.server.forbidden.generic.title",
        "error.server.forbidden.generic.msg",
        "Forbidden error 403"),

    ACTION_NOT_ALLOWED(
        "error.server.forbidden.action-not-allowed.title",
        "error.server.forbidden.action-not-allowed.msg",
        "Action not allowed : {0}");

    private final String messageKey;
    private final String titleKey;
    private final String messageCause;

    ForbiddenExceptionType(String titleKey, String messageKey, String messageCause) {
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
