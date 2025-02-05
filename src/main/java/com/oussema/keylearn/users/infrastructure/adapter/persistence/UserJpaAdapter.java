// package com.oussema.keylearn.users.infrastructure.adapter.persistence;
//
// import com.inspark.sabeel.auth.domain.enums.RoleType;
// import com.inspark.sabeel.auth.domain.model.Role;
// import com.inspark.sabeel.auth.infrastructure.entity.RoleEntity;
// import com.inspark.sabeel.auth.infrastructure.mapper.RoleMapper;
// import com.inspark.sabeel.auth.infrastructure.repository.RoleRepository;
// import com.inspark.sabeel.exception.ConflictException;
// import com.inspark.sabeel.exception.NotFoundException;
// import com.inspark.sabeel.shared.annotation.PersistenceAdapter;
// import com.inspark.sabeel.users.domain.enums.NotificationPreference;
// import com.inspark.sabeel.users.domain.model.User;
// import com.inspark.sabeel.users.domain.port.output.Users;
// import com.inspark.sabeel.users.infrastructure.dto.request.UpdateProfileRequest;
// import com.inspark.sabeel.users.infrastructure.entity.UserEntity;
// import com.inspark.sabeel.users.infrastructure.entity.UserEntityInfo;
// import com.inspark.sabeel.users.infrastructure.mapper.UserMapper;
// import com.inspark.sabeel.users.infrastructure.repository.UserRepository;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
// import lombok.RequiredArgsConstructor;
// import org.hibernate.StaleObjectStateException;
// import org.springframework.orm.ObjectOptimisticLockingFailureException;
// import org.springframework.transaction.annotation.Transactional;
//
// @PersistenceAdapter
// @RequiredArgsConstructor
// @Transactional
// public class UserJpaAdapter implements Users {
//
//  private final UserRepository userRepository;
//  private final RoleRepository roleRepository;
//  private final UserMapper userMapper;
//  private final RoleMapper roleMapper;
//
//  @Override
//  @Transactional
//  public Optional<User> findById(UUID id) {
//    UserEntity userEntity =
//        userRepository
//            .findById(id)
//            .orElseThrow(
//                () ->
//                    new
// NotFoundException(NotFoundException.NotFoundExceptionType.USER_NOT_FOUND));
//
//    return Optional.of(userMapper.toUser(userEntity));
//  }
//
//  @Override
//  public Optional<User> findByIdForProfile(UUID id) {
//    UserEntityInfo userEntity =
//        userRepository
//            .findByIdForProfile(id)
//            .orElseThrow(
//                () ->
//                    new
// NotFoundException(NotFoundException.NotFoundExceptionType.USER_NOT_FOUND));
//    User user = userMapper.toUserFromProjection(userEntity);
//    return Optional.of(user);
//  }
//
//  @Override
//  public Role findRoleByName(RoleType roleType) {
//    RoleEntity role = this.roleRepository.findByName(roleType);
//    return roleMapper.toRole(role);
//  }
//
//  @Override
//  public Optional<User> findByEmail(String email) {
//    return userRepository.findByEmail(email).map(userMapper::toUser);
//  }
//
//  @Override
//  public User update(User user) {
//    try {
//      UserEntity userEntity = userMapper.toUserEntity(user);
//      var savedUser = userRepository.save(userEntity);
//      return userMapper.toUser(savedUser);
//    } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException ex) {
//      throw new ConflictException(ConflictException.ConflictExceptionType.CONFLICT_LOCK_VERSION);
//    }
//  }
//
//  @Override
//  public User updateProfile(UpdateProfileRequest updateProfileRequest) {
//    try {
//      UserEntity existingUser =
//          userRepository
//              .findById(updateProfileRequest.id())
//              .orElseThrow(
//                  () ->
//                      new NotFoundException(
//                          NotFoundException.NotFoundExceptionType.USER_NOT_FOUND));
//      UserEntity user =
//          userMapper.updateUserFromProfileUpdateRequest(updateProfileRequest, existingUser);
//      var savedUser = userRepository.save(user);
//      return userMapper.toUser(savedUser);
//    } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException ex) {
//      throw new ConflictException(ConflictException.ConflictExceptionType.CONFLICT_LOCK_VERSION);
//    }
//  }
//
//  @Override
//  public List<User> findByRolesContaining(Role role) {
//    RoleEntity roleEntity = roleMapper.toRoleEntity(role);
//    return userRepository.findByRolesContainingAndEnabledTrue(roleEntity).stream()
//        .map(userMapper::toUserFromNotificationProjection)
//        .toList();
//  }
//
//  @Override
//  public void updateNotificationPreference(
//      UUID id, User user, NotificationPreference notificationPreference) {
//    user.setNotificationPreference(notificationPreference);
//    this.update(user);
//  }
// }
