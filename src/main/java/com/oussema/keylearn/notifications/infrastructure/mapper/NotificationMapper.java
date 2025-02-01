package com.oussema.keylearn.notifications.infrastructure.mapper;

import com.oussema.keylearn.notifications.domain.model.Notification;
import com.oussema.keylearn.notifications.infrastructure.dto.response.NotificationDto;
import com.oussema.keylearn.notifications.infrastructure.entity.NotificationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NotificationMapper {
  public abstract NotificationEntity toEntity(Notification notification);

  public abstract Notification toNotification(NotificationEntity notificationEntity);

  public abstract List<NotificationEntity> toEntities(List<Notification> notifications);

  public abstract List<Notification> toNotifications(List<NotificationEntity> notificationEntities);

  public abstract List<NotificationDto> toDtos(List<Notification> notifications);

  public abstract NotificationDto toDto(Notification notification);
}
