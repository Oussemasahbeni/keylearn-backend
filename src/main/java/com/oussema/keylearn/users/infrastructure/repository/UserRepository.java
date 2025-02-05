package com.oussema.keylearn.users.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import com.oussema.keylearn.users.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository
    extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
  Optional<UserEntity> findByEmail(String email);
}
