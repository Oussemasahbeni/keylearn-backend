// package com.oussema.keylearn.users.domain.port.input;
//
// import com.inspark.sabeel.cloudstorage.domain.model.Blob;
// import com.inspark.sabeel.users.domain.enums.NotificationPreference;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.infrastructure.dto.request.UpdateProfileRequest;
// import java.util.UUID;
// import org.springframework.web.multipart.MultipartFile;
//
/// ** Interface defining the use cases for managing users. */
// public interface UserUseCases {
//
//  /**
//   * Finds a user by their unique identifier.
//   *
//   * @param id the unique identifier of the user
//   * @return the user associated with the given id
//   */
//  User findById(UUID id);
//
//  /**
//   * Finds a user by their unique identifier for profile.
//   *
//   * @param id the unique identifier of the user
//   * @return the user associated with the given id
//   */
//  User findByIdForProfile(UUID id);
//
//  /**
//   * Updates the user's profile information.
//   *
//   * @param id the unique identifier of the user
//   * @param updateProfileRequest the request containing the updated profile information
//   * @return the updated user
//   */
//  User updateProfile(UUID id, UpdateProfileRequest updateProfileRequest);
//
//  /**
//   * Updates the user's profile picture.
//   *
//   * @param id the unique identifier of the user
//   * @param file the new profile picture file
//   * @return the updated user
//   */
//  Blob updateProfilePicture(UUID id, MultipartFile file);
//
//  Boolean deleteProfilePicture(UUID id);
//
//  void updateNotificationPreference(UUID id, NotificationPreference notificationPreference);
// }
