// package com.oussema.keylearn.users.infrastructure.adapter.persistence;
//
//
// import java.util.Set;
// import java.util.UUID;
//
// import com.oussema.keylearn.shared.annotation.PersistenceAdapter;
// import com.oussema.keylearn.users.domain.model.User;
// import com.oussema.keylearn.users.domain.port.output.Admins;
// import com.oussema.keylearn.users.infrastructure.mapper.UserMapper;
// import com.oussema.keylearn.users.infrastructure.repository.UserRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.transaction.annotation.Transactional;
//
// @PersistenceAdapter
// @RequiredArgsConstructor
// @Transactional
// public class AdminJpaAdapter implements Admins {
//
//  private final UserRepository userRepository;
//  private final UserMapper userMapper;
//  private final RoleRepository roleRepository;
//
//  @Override
//  public Page<User> findAll(String criteria, Pageable pageable, RoleType role) {
//    return userRepository
//        .findAll(UserSpecifications.hasCriteria(criteria, role), pageable)
//        .map(userMapper::toUser);
//  }
//
//  @Override
//  public User save(User user) {
//    RoleEntity roleEntity = roleRepository.findByName(RoleType.ADMIN);
//    UserEntity userEntity = userMapper.toUserEntity(user);
//    userEntity.setRoles(Set.of(roleEntity));
//    UserEntity savedUser = userRepository.save(userEntity);
//    return userMapper.toUser(savedUser);
//  }
//
//  @Override
//  public Boolean deleteById(UUID id) {
//    try {
//      userRepository.deleteById(id);
//      return true;
//    } catch (Exception e) {
//      return false;
//    }
//  }
// }
