package com.oussema.keylearn.exception;

import java.io.Serial;

public class UnauthorizedException extends ApplicationException {
  @Serial private static final long serialVersionUID = 5477619054099558741L;

  public UnauthorizedException(ExceptionType type) {
    super(type);
  }

  protected UnauthorizedException(ExceptionType type, Object[] valueParams, Object... keyParams) {
    super(type, valueParams, keyParams);
  }

  protected UnauthorizedException(
      ExceptionType type, Throwable cause, Object[] valueParams, Object... keyParams) {
    super(type, cause, valueParams, keyParams);
  }

  protected UnauthorizedException(ExceptionType type, Throwable cause) {
    super(type, cause);
  }

  protected UnauthorizedException(
      ExceptionType type, String message, Throwable cause, Object... keyParams) {
    super(type, message, cause, keyParams);
  }

  protected UnauthorizedException(ExceptionType type, Throwable cause, Object... valueParams) {
    super(type, cause, valueParams);
  }

  protected UnauthorizedException(ExceptionType type, String message) {
    super(type, message);
  }

  public enum UnauthorizedExceptionType implements ExceptionType {
    GENERIC(
        "error.server.exception.forbidden.generic.title",
        "error.server.exception.forbidden.generic.msg",
        "Forbidden error 403"),

    USER_NOT_AUTHORIZED(
        "error.server.exception.unauthorized.user.not.authorized.title",
        "error.server.exception.unauthorized.user.not.authorized.msg",
        "User not authorized to perform this action");

    private final String messageKey;
    private final String titleKey;
    private final String messageCause;

    UnauthorizedExceptionType(String titleKey, String messageKey, String messageCause) {
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
