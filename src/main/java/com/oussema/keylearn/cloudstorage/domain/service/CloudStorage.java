package com.oussema.keylearn.cloudstorage.domain.service;

import com.oussema.keylearn.cloudstorage.domain.model.Blob;
import org.springframework.web.multipart.MultipartFile;

/** Interface for cloud storage services */
public interface CloudStorage {

  /**
   * Uploads a file to the cloud storage
   *
   * @param file the file to upload
   * @return the URL of the uploaded file
   */
  Blob uploadFile(MultipartFile file);

  /**
   * Deletes a file from the cloud storage
   *
   * @param url the URL of the file to delete
   */
  void deleteFile(String url);

  /**
   * Uploads an image to the cloud storage
   *
   * @param file the image to upload
   * @return the URL of the uploaded image
   */
  Blob uploadImage(MultipartFile file);

  /**
   * Deletes an image from the cloud storage
   *
   * @param url the URL of the image to delete
   */
  void deleteImage(String url);
}
