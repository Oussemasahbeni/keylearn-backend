package com.oussema.keylearn.notifications.domain.port.input;

import com.oussema.keylearn.notifications.domain.enums.NotificationType;
import com.oussema.keylearn.notifications.domain.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface NotificationUseCases {
  /**
   * Create a new notification
   *
   * @param notification
   * @return
   */
  Notification createNotification(Notification notification);

  void notifyUsersWithWsNotification(
      Map<String, Object> data, NotificationType type, List<UUID> usersToNotify);

  /**
   * Create a list of notifications
   *
   * @param notifications
   */
  void createBulk(List<Notification> notifications);

  /**
   * Get all notifications by user id
   *
   * @param userId
   * @return
   */
  Page<Notification> getAllByUserId(UUID userId, Pageable pageable);

  /**
   * Mark a notification as read
   *
   * @param id
   * @return
   */
  Notification markAsRead(UUID id);

  /**
   * Mark all notifications as read
   *
   * @param userId
   * @return
   */
  boolean markAllAsRead(UUID userId);

  /**
   * Delete a notification by id
   *
   * @param id
   */
  void deleteById(UUID id);

  /** Delete all notifications */
  @Transactional
  void deleteAll(UUID userId);

  Long getUnreadNotificationsCount(UUID id);
}
