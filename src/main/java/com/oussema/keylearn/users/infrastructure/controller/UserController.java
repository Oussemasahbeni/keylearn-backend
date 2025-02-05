// package com.oussema.keylearn.users.infrastructure.controller;
//
// import static com.inspark.sabeel.utils.AuthUtils.getCurrentAuthenticatedUserId;
// import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
//
// import com.inspark.sabeel.cloudstorage.domain.model.Blob;
// import com.inspark.sabeel.users.domain.enums.NotificationPreference;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.domain.port.input.UserUseCases;
// import com.inspark.sabeel.users.infrastructure.dto.request.UpdateProfileRequest;
// import com.inspark.sabeel.users.infrastructure.dto.response.UserDto;
// import com.inspark.sabeel.users.infrastructure.mapper.UserMapper;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
// import java.util.UUID;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
//
// @RestController
// @RequestMapping("/users")
// @Tag(
//    name = "Users",
//    description =
//        "Operations related to managing users, including retrieving user information, updating
// user preferences, and updating user profile.")
// @RequiredArgsConstructor
// public class UserController {
//  private final UserUseCases usersUseCases;
//  private final UserMapper userMapper;
//
//  @Operation(
//      summary = "Find user by ID",
//      description = "Retrieve user information by their unique ID.")
//  @GetMapping("{id}")
//  public ResponseEntity<UserDto> findById(@PathVariable UUID id) {
//    return ResponseEntity.ok(userMapper.toUserDto(usersUseCases.findById(id)));
//  }
//
//  @Operation(
//      summary = "Get current authenticated user",
//      description = "Retrieve information for the currently authenticated user.")
//  @GetMapping("/me")
//  public ResponseEntity<UserDto> findMe() {
//    UUID id = getCurrentAuthenticatedUserId();
//    return ResponseEntity.ok(userMapper.toUserDto(usersUseCases.findById(id)));
//  }
//
//  @Operation(
//      summary = "Update user profile",
//      description =
//          "Update profile details such as name, email, etc., for the current authenticated user.")
//  @PatchMapping("/update-profile")
//  public ResponseEntity<UserDto> updateProfile(
//      @Valid @RequestBody UpdateProfileRequest updatedProfileRequest) {
//    UUID id = getCurrentAuthenticatedUserId();
//    User user = usersUseCases.updateProfile(id, updatedProfileRequest);
//    return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK);
//  }
//
//  @Operation(
//      summary = "Update user profile picture",
//      description = "Update the profile picture for the current authenticated user.")
//  @PatchMapping(value = "/profile-picture", consumes = MULTIPART_FORM_DATA_VALUE)
//  public ResponseEntity<Blob> updateProfilePicture(@RequestParam("file") MultipartFile file) {
//    UUID id = getCurrentAuthenticatedUserId();
//    Blob blob = usersUseCases.updateProfilePicture(id, file);
//    return new ResponseEntity<>(blob, HttpStatus.OK);
//  }
//
//  @DeleteMapping("/profile-picture")
//  public ResponseEntity<Boolean> deleteProfilePicture() {
//    UUID id = getCurrentAuthenticatedUserId();
//    return ResponseEntity.ok(usersUseCases.deleteProfilePicture(id));
//  }
//
//  @Operation(
//      summary = "Update user notification preference",
//      description = "Update the notification preference for the current authenticated user.")
//  @PatchMapping(value = "/notification/{notificationPreference}")
//  public ResponseEntity<Void> updateNotificationPreference(
//      @PathVariable NotificationPreference notificationPreference) {
//    UUID id = getCurrentAuthenticatedUserId();
//    usersUseCases.updateNotificationPreference(id, notificationPreference);
//    return new ResponseEntity<>(HttpStatus.OK);
//  }
// }
