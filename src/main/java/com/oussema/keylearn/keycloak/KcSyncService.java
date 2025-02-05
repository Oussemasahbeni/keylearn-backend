package com.oussema.keylearn.keycloak;

import com.oussema.keylearn.exception.ExistsException;
import com.oussema.keylearn.users.infrastructure.entity.UserEntity;
import com.oussema.keylearn.users.infrastructure.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KcSyncService {

  private final UserRepository userRepository;

  public Boolean syncUser(KcUser request) {

    if (request.action().equals(Actions.DELETE)) {
      userRepository.deleteById(UUID.fromString(request.id()));
      return true;
    }

    if (request.action().equals(Actions.CREATE)) {
      userRepository
          .findById(UUID.fromString(request.id()))
          .ifPresent(
              user -> {
                throw new ExistsException(ExistsException.ExistsExceptionType.USER_ALREADY_EXISTS);
              });
      UserEntity newUser =
          UserEntity.builder()
              .id(UUID.fromString(request.id()))
              //            .username(request.username())
              .email(request.email())
              .firstName(request.firstName())
              .lastName(request.lastName())
              .build();
      userRepository.save(newUser);
    }

    return true;
  }
}
