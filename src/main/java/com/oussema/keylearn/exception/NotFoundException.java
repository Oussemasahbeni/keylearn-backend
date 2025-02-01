package com.oussema.keylearn.exception;

import java.io.Serial;

public class NotFoundException extends ApplicationException {

  @Serial private static final long serialVersionUID = 5477619054099558741L;

  public NotFoundException(NotFoundExceptionType type) {
    super(type);
  }

  public NotFoundException(NotFoundExceptionType type, Throwable cause) {
    super(type, cause);
  }

  public NotFoundException(NotFoundExceptionType type, String message, Throwable cause) {
    super(type, message, cause);
  }

  public NotFoundException(
      NotFoundExceptionType type, String message, Throwable cause, Object... keyParams) {
    super(type, message, cause, keyParams);
  }

  public NotFoundException(NotFoundExceptionType type, Object[] valueParams, Object... keyParams) {
    super(type, valueParams, keyParams);
  }

  public NotFoundException(NotFoundExceptionType type, Object... valueParams) {
    super(type, valueParams);
  }

  public enum NotFoundExceptionType implements ExceptionType {
    GENERIC(
        "error.server.not-found.generic.title",
        "error.server.not-found.generic.msg",
        "Resource not found 400"),

    USER_NOT_FOUND(
        "error.server.not-found.user.title",
        "error.server.not-found.user.msg",
        "User not found : {0}"),

    CODE_NOT_FOUND(
        "error.server.not-found.code.title",
        "error.server.not-found.code.msg",
        "Code not found : {0}"),

    CODE_USED(
        "error.server.not-found.code.used.title",
        "error.server.not-found.code.used.msg",
        "Code is already used : {0}"),

    TOKEN_NOT_FOUND(
        "error.server.not-found.token.title",
        "error.server.not-found.token.msg",
        "Token not found : {0}"),

    CODE_EXPIRED(
        "error.server.not-found.code.expired.title",
        "error.server.not-found.code.expired.msg",
        "Code is expired : {0}"),
    ARTICLE_NOT_FOUND(
        "error.server.not-found.article.title",
        "error.server.not-found.article.msg",
        "Article not found : {0}"),

    COLLECTION_NOT_FOUND(
        "error.server.not-found.collection.title",
        "error.server.not-found.collection.msg",
        "Collection not found : {0}"),

    ARTICLE_NOT_FOUND_IN_COLLECTION(
        "error.server.not-found.article.in.collection.title",
        "error.server.not-found.article.in.collection.msg",
        "Article not found in collection : {0}"),

    NOTIFICATION_NOT_FOUND(
        "error.server.not-found.notification.title",
        "error.server.not-found.notification.msg",
        "Notification not found : {0}"),

    SPECIALITY_NOT_FOUND(
        "error.server.not-found.speciality.title",
        "error.server.not-found.speciality.msg",
        "Speciality not found : {0}"),

    NEWS_LETTER_NOT_FOUND(
        "error.server.not-found.newsletter.title",
        "error.server.not-found.newsletter.msg",
        "Newsletter not found : {0}"),
    RESOURCE_NOT_FOUND(
        "error.server.not-found.resource.title",
        "error.server.not-found.resource.msg",
        "Resource not found : {0}"),
    AXE_NOT_FOUND(
        "error.server.not-found.axe.title",
        "error.server.not-found.axe.msg",
        "Axe not found : {0}"),
    OBJECTIVE_NOT_FOUND(
        "error.server.not-found.objective.title",
        "error.server.not-found.objective.msg",
        "Objective not found : {0}"),
    POST_NOT_FOUND(
        "error.server.not-found.post.title",
        "error.server.not-found.post.msg",
        "Post not found : {0}"),
    COMMENT_NOT_FOUND(
        "error.server.not-found.comment.title",
        "error.server.not-found.comment.msg",
        "Comment not found : {0}"),
    THEMATIC_NOT_FOUND(
        "error.server.not-found.thematic.title",
        "error.server.not-found.thematic.msg",
        "Thematic not found : {0}"),
    ACTOR_NOT_FOUND(
        "error.server.not-found.actor.title",
        "error.server.not-found.actor.msg",
        "Actor not found : {0}"),
    CONFIGURATION_NOT_FOUND(
        "error.server.not-found.configuration.title",
        "error.server.not-found.configuration.msg",
        "Configuration not found : {0}"),
    ;

    private final String messageKey;
    private final String titleKey;
    private final String messageCause;

    NotFoundExceptionType(String titleKey, String messageKey, String messageCause) {
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
