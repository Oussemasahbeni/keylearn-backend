package com.oussema.keylearn.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class FileValidator {

  private static final Set<String> ALLOWED_EXTENSIONS =
      Set.of("pdf", "docx", "xlsx", "pptx", "txt");
  private static final Set<String> ALLOWED_IMAGE_EXTENSIONS =
      Set.of("jpg", "jpeg", "png", "gif", "bmp", "webp");
  private static final Set<String> ALLOWED_IMAGE_CONTENT_TYPES =
      Set.of("image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp");

  private FileValidator() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static String getFileExtension(MultipartFile file) {
    var filename = file.getOriginalFilename();
    return getExtensionFromString(filename);
  }

  public static String getExtensionFromString(String filename) {
    if (filename == null || filename.lastIndexOf('.') == -1) {
      return null;
    }
    return filename.substring(filename.lastIndexOf('.') + 1);
  }

  /**
   * Method to check if a file extension is an image extension
   *
   * @param file the file to be checked
   * @return true if the file extension is an image extension, false otherwise
   */
  public static boolean isValidImageExtension(MultipartFile file) {
    var fileName = file.getOriginalFilename();
    String extension = StringUtils.getFilenameExtension(fileName);
    return extension != null && ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase());
  }

  /**
   * Method to check if the extension of a file is valid
   *
   * @param file the file to be checked
   * @return true if the extension is valid, false otherwise
   */
  public static boolean isValidFileExtension(MultipartFile file) {
    // Get the name of the file
    String fileName = file.getOriginalFilename();
    // Get the extension of the file
    String extension = StringUtils.getFilenameExtension(fileName);

    // Check if the extension is valid
    return extension != null && ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
  }

  /**
   * Method to check if a content type is an image
   *
   * @param file the file to be checked
   * @return true if the content type is an image, false otherwise
   */
  public static boolean isImage(MultipartFile file) {

    var contentType = file.getContentType();
    return contentType != null && ALLOWED_IMAGE_CONTENT_TYPES.contains(contentType);
  }
}
