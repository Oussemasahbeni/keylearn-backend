package com.oussema.keylearn.cloudstorage.infrastructure.adapter;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.oussema.keylearn.cloudstorage.domain.model.Blob;
import com.oussema.keylearn.cloudstorage.domain.service.CloudStorage;
import com.oussema.keylearn.exception.CloudStorageException;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/** Adapter class for Azure cloud storage */
@Component
@Log4j2
@Profile("dev | test")
public class AzureAdapter implements CloudStorage {

  @Value("${spring.cloud.azure.storage.blob.connection-string}")
  private String connectionString;

  @PostConstruct
  public void logActiveAdapter() {
    log.info("Azure Adapter is active");
  }

  /**
   * Method to get the blob client for a blob
   *
   * @param blobNameOrUrl the name of the blob or the URL of the blob
   * @param containerName the name of the container
   * @return the blob client
   */
  public BlockBlobClient getBlobClient(String blobNameOrUrl, String containerName) {
    String blobName = getBlobNameFromUrl(blobNameOrUrl);
    BlobServiceClient blobServiceClient =
        new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
    BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
    return containerClient.getBlobClient(blobName).getBlockBlobClient();
  }

  /**
   * Method to get the blob client for a blob
   *
   * @param file the file to upload
   * @param containerName the name of the container
   * @return the blob client
   */
  public Blob uploadToAzure(MultipartFile file, String containerName) {
    String blobName = java.util.UUID.randomUUID() + "-" + file.getOriginalFilename();
    BlockBlobClient blobClient = getBlobClient(blobName, containerName);
    try {
      byte[] bytes = file.getBytes();
      try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
        blobClient.upload(inputStream, bytes.length, true);
        BlobHttpHeaders headers = new BlobHttpHeaders();
        headers.setContentType(file.getContentType());
        blobClient.setHttpHeaders(headers);
      }
    } catch (Exception e) {
      log.error("Failed to upload file to azure", e);
      throw new CloudStorageException(
          CloudStorageException.CloudStorageExceptionType.AZURE_FAILED_TO_UPLOAD_FILE);
    }
    return new Blob(file.getOriginalFilename(), file.getContentType(), blobClient.getBlobUrl());
  }

  /**
   * Method to upload a file to the cloud storage
   *
   * @param file the file to upload
   * @return the URL of the uploaded file
   */
  @Override
  public Blob uploadFile(MultipartFile file) {
    if (file.getSize() > 5242880) {
      throw new CloudStorageException(
          CloudStorageException.CloudStorageExceptionType.FILE_SIZE_LIMIT_EXCEEDED);
    }
    return uploadToAzure(file, "files");
  }

  /**
   * Method to delete a file from the cloud storage
   *
   * @param url the URL of the file to delete
   */
  @Override
  public void deleteFile(String url) {
    BlockBlobClient blobClient = getBlobClient(url, "files");
    blobClient.deleteIfExists();
  }

  /**
   * Method to upload an image to the cloud storage
   *
   * @param file the image to upload
   * @return the URL of the uploaded image
   */
  @Override
  public Blob uploadImage(MultipartFile file) {
    if (file.getSize() > 5242880) {
      throw new CloudStorageException(
          CloudStorageException.CloudStorageExceptionType.IMAGE_SIZE_LIMIT_EXCEEDED);
    }
    String contentType = file.getContentType();
    if (contentType == null || !contentType.contains("image/")) {
      throw new CloudStorageException(
          CloudStorageException.CloudStorageExceptionType.IMAGE_TYPE_MUST_BE_JPEG_OR_PNG_OR_JPG);
    }
    return uploadToAzure(file, "images");
  }

  /**
   * Method to delete an image from the cloud storage
   *
   * @param url the URL of the image to delete
   */
  @Override
  public void deleteImage(String url) {
    BlockBlobClient blobClient = getBlobClient(url, "images");
    blobClient.deleteIfExists();
  }

  /**
   * Method to get the blob name from a blob URL
   *
   * @param blobNameOrUrl the name of the blob or the URL of the blob
   * @return the blob name
   */
  private String getBlobNameFromUrl(String blobNameOrUrl) {
    if (blobNameOrUrl.startsWith("http")) {
      // Extract the blob name from the URL
      return blobNameOrUrl.substring(blobNameOrUrl.lastIndexOf("/") + 1);
    }
    // Assuming the blob name is the last segment of the URL
    return blobNameOrUrl.substring(blobNameOrUrl.lastIndexOf("/") + 1);
  }
}
