package com.oussema.keylearn.exception;

import java.io.Serial;

public class CloudStorageException extends ApplicationException {

  @Serial private static final long serialVersionUID = 5477619054099558741L;

  public CloudStorageException(ExceptionType type) {
    super(type);
  }

  protected CloudStorageException(ExceptionType type, Object[] valueParams, Object... keyParams) {
    super(type, valueParams, keyParams);
  }

  protected CloudStorageException(
      ExceptionType type, Throwable cause, Object[] valueParams, Object... keyParams) {
    super(type, cause, valueParams, keyParams);
  }

  protected CloudStorageException(ExceptionType type, Throwable cause) {
    super(type, cause);
  }

  protected CloudStorageException(
      ExceptionType type, String message, Throwable cause, Object... keyParams) {
    super(type, message, cause, keyParams);
  }

  protected CloudStorageException(ExceptionType type, Throwable cause, Object... valueParams) {
    super(type, cause, valueParams);
  }

  protected CloudStorageException(ExceptionType type, String message) {
    super(type, message);
  }

  public enum CloudStorageExceptionType implements ExceptionType {
    GENERIC("error.server.storage.title", "error.server.storage.msg", "Azure error 400"),

    AZURE_FAILED_TO_UPLOAD_FILE(
        "error.server.azure.title.storage.failed.to.upload.file",
        "error.server.storage.msg.storage.failed.to.upload.file",
        "Failed to upload file"),

    AWS_FAILED_TO_UPLOAD_FILE(
        "error.server.aws.title.aws.failed.to.upload.file",
        "error.server.aws.msg.aws.failed.to.upload.file",
        "Failed to upload file"),

    FILE_SIZE_LIMIT_EXCEEDED(
        "error.server.storage.title.file.size.limit.exceeded",
        "error.server.storage.msg.file.size.limit.exceeded",
        "File size must be less than 5MB"),
    FILE_TYPE_MUST_BE_PDF(
        "error.server.storage.title.file.type.must.be.pdf",
        "error.server.storage.msg.file.type.must.be.pdf",
        "File type must be pdf"),
    IMAGE_SIZE_LIMIT_EXCEEDED(
        "error.server.storage.title.image.size.limit.exceeded",
        "error.server.storage.msg.image.size.limit.exceeded",
        "Image size must be less than 5MB"),
    IMAGE_TYPE_MUST_BE_JPEG_OR_PNG_OR_JPG(
        "error.server.storage.title.image.type.must.be.jpeg.or.png.or.jpg",
        "error.server.storage.msg.image.type.must.be.jpeg.or.png.or.jpg",
        "Image type must be jpeg or png or jpg"),
    INVALID_IMAGE_EXTENSION(
        "error.server.storage.title.invalid.image.extension",
        "error.server.storage.msg.invalid.image.extension",
        "Invalid image extension"),
    INVALID_FILE_TYPE(
        "error.server.conflict.invalid-file-type.title",
        "error.server.conflict.invalid-file-type.msg",
        "Invalid file type. Only PDF, DOCX, XLSX, and similar formats are allowed."),
    INVALID_IMAGE_TYPE(
        "error.server.conflict.invalid-image-type.title",
        "error.server.conflict.invalid-image-type.msg",
        "Invalid image type. Only image files are allowed."),
    ;

    private final String messageKey;
    private final String titleKey;
    private final String messageCause;

    CloudStorageExceptionType(String titleKey, String messageKey, String messageCause) {
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
