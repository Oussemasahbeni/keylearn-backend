// package com.oussema.keylearn.users.domain.port.output;
//
// import com.inspark.sabeel.auth.domain.enums.RoleType;
// import com.inspark.sabeel.auth.domain.model.Role;
// import com.inspark.sabeel.users.domain.enums.NotificationPreference;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.infrastructure.dto.request.UpdateProfileRequest;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
//
// public interface Users {
//
//  Optional<User> findById(UUID id);
//
//  Optional<User> findByIdForProfile(UUID id);
//
//  Role findRoleByName(RoleType roleType);
//
//  Optional<User> findByEmail(String email);
//
//  User update(User user);
//
//  User updateProfile(UpdateProfileRequest updateProfileRequest);
//
//  List<User> findByRolesContaining(Role role);
//
//  void updateNotificationPreference(
//      UUID id, User user, NotificationPreference notificationPreference);
// }
