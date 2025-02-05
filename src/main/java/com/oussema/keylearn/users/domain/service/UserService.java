// package com.oussema.keylearn.users.domain.service;
//
// import com.inspark.sabeel.cloudstorage.domain.model.Blob;
// import com.inspark.sabeel.cloudstorage.domain.service.CloudStorage;
// import com.inspark.sabeel.exception.ForbiddenException;
// import com.inspark.sabeel.exception.NotFoundException;
// import com.inspark.sabeel.shared.annotation.DomainService;
// import com.inspark.sabeel.users.domain.enums.NotificationPreference;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.domain.port.input.UserUseCases;
// import com.inspark.sabeel.users.domain.port.output.Users;
// import com.inspark.sabeel.users.infrastructure.dto.request.UpdateProfileRequest;
// import java.util.UUID;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.multipart.MultipartFile;
//
// @DomainService
// @RequiredArgsConstructor
// public class UserService implements UserUseCases {
//
//  private final Users users;
//  private final CloudStorage cloudStorage;
//
//  @Override
//  public User findById(UUID id) {
//    return users
//        .findById(id)
//        .orElseThrow(
//            () -> new NotFoundException(NotFoundException.NotFoundExceptionType.USER_NOT_FOUND));
//  }
//
//  @Override
//  public User findByIdForProfile(UUID id) {
//    return users
//        .findByIdForProfile(id)
//        .orElseThrow(
//            () -> new NotFoundException(NotFoundException.NotFoundExceptionType.USER_NOT_FOUND));
//  }
//
//  @Override
//  public User updateProfile(UUID id, UpdateProfileRequest updateProfileRequest) {
//    if (!id.equals(updateProfileRequest.id())) {
//      throw new ForbiddenException(ForbiddenException.ForbiddenExceptionType.ACTION_NOT_ALLOWED);
//    }
//    return users.updateProfile(updateProfileRequest);
//  }
//
//  @Override
//  public Blob updateProfilePicture(UUID id, MultipartFile file) {
//    User user = findById(id);
//    Blob uploadFile = cloudStorage.uploadFile(file);
//    String oldProfilePicture = user.getProfilePicture();
//    try {
//      user.setProfilePicture(uploadFile.url());
//      users.update(user);
//      if (oldProfilePicture != null) {
//        cloudStorage.deleteFile(oldProfilePicture);
//      }
//      return uploadFile;
//    } catch (Exception e) {
//      cloudStorage.deleteFile(uploadFile.url());
//      throw e;
//    }
//  }
//
//  @Override
//  public Boolean deleteProfilePicture(UUID id) {
//    try {
//      User user = findById(id);
//      cloudStorage.deleteFile(user.getProfilePicture());
//      user.setProfilePicture(null);
//      users.update(user);
//      return true;
//
//    } catch (Exception e) {
//      throw new ForbiddenException(ForbiddenException.ForbiddenExceptionType.ACTION_NOT_ALLOWED);
//    }
//  }
//
//  @Override
//  public void updateNotificationPreference(UUID id, NotificationPreference notificationPreference)
// {
//    User user = findById(id);
//    users.updateNotificationPreference(id, user, notificationPreference);
//  }
// }
