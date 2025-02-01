package com.oussema.keylearn.notifications.domain.service;

import com.oussema.keylearn.notifications.domain.enums.NotificationType;
import com.oussema.keylearn.notifications.domain.model.Notification;
import com.oussema.keylearn.notifications.domain.port.input.NotificationUseCases;
import com.oussema.keylearn.notifications.domain.port.output.Notifications;
import com.oussema.keylearn.notifications.domain.port.output.WsNotification;
import com.oussema.keylearn.shared.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
public class NotificationService implements NotificationUseCases {

  private final Notifications notifications;
  private final WsNotification wsNotification;

  // Create a new notification
  @Override
  public Notification createNotification(Notification notification) {
    return notifications.create(notification);
  }

  @Override
  public void notifyUsersWithWsNotification(
      Map<String, Object> data, NotificationType type, List<UUID> usersToNotify) {

    if (data == null || type == null || usersToNotify == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    List<Notification> notifications =
        usersToNotify.stream()
            .distinct() // Remove duplicates
            .map(
                userId ->
                    Notification.builder()
                        .type(type)
                        .userId(userId)
                        .data(data)
                        .isRead(false)
                        .build())
            .collect(Collectors.toList());

    List<Notification> addedNotifications = this.notifications.createBulk(notifications);

    if (addedNotifications.isEmpty()) {
      return;
    }

    addedNotifications.forEach(
        notificationToSend ->
            wsNotification.send(notificationToSend.getUserId().toString(), notificationToSend));
  }

  public void notifyUsersWithNotificationSuppression(
      List<UUID> usersToNotify, UUID notificationId) {
    if (usersToNotify == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    Map<String, Object> data = Map.of("removeNotification", true, "notificationId", notificationId);

    usersToNotify.forEach(userId -> wsNotification.send(userId.toString(), data));
  }

  // Create a list of notifications
  @Override
  public void createBulk(List<Notification> notifications) {
    this.notifications.createBulk(notifications);
  }

  // Get all notifications by user id
  @Override
  public Page<Notification> getAllByUserId(UUID userId, Pageable pageable) {
    return notifications.getAllByUserId(userId, pageable);
  }

  // mark a notification as read
  @Override
  public Notification markAsRead(UUID id) {
    return notifications.markAsRead(id);
  }

  // mark all notifications as read
  @Override
  public boolean markAllAsRead(UUID userId) {
    return notifications.markAllAsRead(userId);
  }

  // delete a notification by id
  @Override
  public void deleteById(UUID id) {
    notifications.deleteById(id);
  }

  // delete all notifications by user id
  @Override
  public void deleteAll(UUID talentId) {
    notifications.deleteAll(talentId);
  }

  @Override
  public Long getUnreadNotificationsCount(UUID id) {
    return notifications.getUnreadNotificationsCount(id);
  }
}
