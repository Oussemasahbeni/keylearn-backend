package com.oussema.keylearn.users.infrastructure.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.Collections;
import java.util.List;

import com.oussema.keylearn.shared.CycleAvoidingMappingContext;
import com.oussema.keylearn.shared.DoIgnore;
import com.oussema.keylearn.users.domain.model.User;
import com.oussema.keylearn.users.infrastructure.dto.request.AddAdminRequest;
import com.oussema.keylearn.users.infrastructure.dto.request.UpdateProfileRequest;
import com.oussema.keylearn.users.infrastructure.dto.response.AdminDto;
import com.oussema.keylearn.users.infrastructure.dto.response.UserDto;
import com.oussema.keylearn.users.infrastructure.entity.UserEntity;
import com.oussema.keylearn.users.infrastructure.entity.UserEntityForNotification;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public abstract class UserMapper {

  public abstract UserEntity mapToUserEntity(
      User user, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

  public abstract User mapToUser(
      UserEntity userEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

  @DoIgnore
  public UserEntity toUserEntity(User user) {
    return mapToUserEntity(user, new CycleAvoidingMappingContext());
  }

  @DoIgnore
  public User toUser(UserEntity userEntity) {
    return mapToUser(userEntity, new CycleAvoidingMappingContext());
  }

  public abstract User toUser(AddAdminRequest addAdminRequest);

  public abstract UserDto toUserDto(User user);

  public abstract AdminDto toAdminDto(User user);

  public abstract User toUserFromNotificationProjection(UserEntityForNotification userEntity);

  @Mapping(target = "id", ignore = true)
  public abstract UserEntity updateUserFromProfileUpdateRequest(
      UpdateProfileRequest updateProfileRequest, @MappingTarget UserEntity user);
}
