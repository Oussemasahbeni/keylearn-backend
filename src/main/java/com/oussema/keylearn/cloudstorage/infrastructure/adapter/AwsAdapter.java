package com.oussema.keylearn.cloudstorage.infrastructure.adapter;

import com.oussema.keylearn.cloudstorage.config.AwsS3BucketProperties;
import com.oussema.keylearn.cloudstorage.domain.model.Blob;
import com.oussema.keylearn.cloudstorage.domain.service.CloudStorage;
import com.oussema.keylearn.exception.CloudStorageException;
import io.awspring.cloud.s3.S3Template;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Profile("prod")
@RequiredArgsConstructor
@Log4j2
@EnableConfigurationProperties(AwsS3BucketProperties.class)
public class AwsAdapter implements CloudStorage {

  private final S3Template s3Template;
  private final AwsS3BucketProperties awsS3BucketProperties;

  @PostConstruct
  public void logActiveAdapter() {
    log.info("AWS Adapter is active");
  }

  /**
   * Uploads a file to the cloud storage
   *
   * @param file the file to upload
   * @return the URL of the uploaded file
   */
  @Override
  public Blob uploadFile(MultipartFile file) {
    var originalFilename = file.getOriginalFilename();
    var bucketName = awsS3BucketProperties.getBucketName();

    String keyName = "files" + "/" + java.util.UUID.randomUUID() + "-" + originalFilename;

    try {
      s3Template.upload(bucketName, keyName, file.getInputStream());
      String fileUrl = awsS3BucketProperties.getCdnBaseUrl() + "/" + keyName;
      return new Blob(keyName, file.getContentType(), fileUrl);
    } catch (IOException e) {
      log.error("Error uploading file to S3", e);
      throw new CloudStorageException(
          CloudStorageException.CloudStorageExceptionType.AWS_FAILED_TO_UPLOAD_FILE);
    }
  }

  /**
   * Uploads an image to the cloud storage
   *
   * @param file the image to upload
   * @return the URL of the uploaded image
   */
  @Override
  public Blob uploadImage(MultipartFile file) {
    var originalFilename = file.getOriginalFilename();
    var bucketName = awsS3BucketProperties.getBucketName();

    String keyName = "images" + "/" + java.util.UUID.randomUUID() + "-" + originalFilename;

    try {
      s3Template.upload(bucketName, keyName, file.getInputStream());
      String fileUrl = awsS3BucketProperties.getCdnBaseUrl() + "/" + keyName;
      return new Blob(keyName, file.getContentType(), fileUrl);
    } catch (IOException e) {
      log.error("Error uploading file to S3", e);
      throw new CloudStorageException(
          CloudStorageException.CloudStorageExceptionType.AWS_FAILED_TO_UPLOAD_FILE);
    }
  }

  /**
   * Deletes a file from the cloud storage
   *
   * @param url the URL of the file to delete
   */
  @Override
  public void deleteFile(String url) {
    var bucketName = awsS3BucketProperties.getBucketName();
    var objectKey = url.substring(url.indexOf("files/"));
    s3Template.deleteObject(bucketName, objectKey);
  }

  /**
   * Deletes an image from the cloud storage
   *
   * @param url the URL of the image to delete
   */
  @Override
  public void deleteImage(String url) {
    var bucketName = awsS3BucketProperties.getBucketName();
    var objectKey = url.substring(url.indexOf("images/"));
    s3Template.deleteObject(bucketName, objectKey);
  }
}
