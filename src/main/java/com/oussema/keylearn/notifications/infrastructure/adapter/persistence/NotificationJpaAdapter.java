package com.oussema.keylearn.notifications.infrastructure.adapter.persistence;

import com.oussema.keylearn.exception.NotFoundException;
import com.oussema.keylearn.notifications.domain.enums.NotificationType;
import com.oussema.keylearn.notifications.domain.model.Notification;
import com.oussema.keylearn.notifications.domain.port.output.Notifications;
import com.oussema.keylearn.notifications.infrastructure.entity.NotificationEntity;
import com.oussema.keylearn.notifications.infrastructure.mapper.NotificationMapper;
import com.oussema.keylearn.notifications.infrastructure.repository.NotificationRepository;
import com.oussema.keylearn.shared.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
@Log4j2
public class NotificationJpaAdapter implements Notifications {

  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;

  // Create a new notification
  @Override
  public Notification create(Notification notification) {
    NotificationEntity notificationEntity = notificationMapper.toEntity(notification);
    // need to set version to 0 to avoid detached entity passed to persist exception
    notificationEntity.setVersion((short) 0);
    NotificationEntity savedNotification = notificationRepository.save(notificationEntity);
    return notificationMapper.toNotification(savedNotification);
  }

  // Create a list of notifications
  @Override
  public List<Notification> createBulk(List<Notification> notifications) {
    List<NotificationEntity> notificationEntities = notificationMapper.toEntities(notifications);
    // need to set version to 0 to avoid detached entity passed to persist exception
    notificationEntities.forEach(notificationEntity -> notificationEntity.setVersion((short) 0));
    List<NotificationEntity> savedNotifications =
        notificationRepository.saveAll(notificationEntities);
    return notificationMapper.toNotifications(savedNotifications);
  }

  // Get all notifications by user id
  @Override
  public Page<Notification> getAllByUserId(UUID userId, Pageable pageable) {
    Specification<NotificationEntity> spec = NotificationSpecification.hasUserId(userId);
    Page<NotificationEntity> notificationEntities = notificationRepository.findAll(spec, pageable);
    return notificationEntities.map(notificationMapper::toNotification);
  }

  // mark a notification as read
  @Override
  public Notification markAsRead(UUID id) {
    NotificationEntity notificationsEntity =
        this.notificationRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        NotFoundException.NotFoundExceptionType.NOTIFICATION_NOT_FOUND,
                        id.toString()));
    notificationsEntity.setIsRead(true);
    NotificationEntity savedNotification = this.notificationRepository.save(notificationsEntity);
    return this.notificationMapper.toNotification(savedNotification);
  }

  // mark all notifications as read
  @Override
  @Transactional
  public boolean markAllAsRead(UUID userId) {
    notificationRepository.markAllAsReadByUserId(userId);
    return true;
  }

  // delete a notification by id
  @Override
  @Transactional
  public void deleteById(UUID id) {
    this.notificationRepository.deleteById(id);
  }

  // delete all notifications by user id
  @Override
  public void deleteAll(UUID userId) {
    this.notificationRepository.deleteAllByUserId(userId);
  }

  @Override
  public Long getUnreadNotificationsCount(UUID id) {
    return this.notificationRepository.countByUserIdAndIsReadFalse(id);
  }

  @Override
  public Optional<Notification> findByTypeAndDataAndUserId(
      NotificationType type, String jsonKey, String jsonValue, UUID userId) {
    return this.notificationRepository
        .findByTypeAndDataAndUserId(type.name(), jsonKey, jsonValue, userId)
        .map(notificationMapper::toNotification);
  }
}
