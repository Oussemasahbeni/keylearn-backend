package com.oussema.keylearn.notifications.domain.port.output;

import com.oussema.keylearn.notifications.domain.enums.NotificationType;
import com.oussema.keylearn.notifications.domain.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Notifications {
  /**
   * Create a new notification
   *
   * @param notification
   * @return
   */
  Notification create(Notification notification);

  /**
   * Create a list of notifications
   *
   * @param notifications
   * @return
   */
  List<Notification> createBulk(List<Notification> notifications);

  /**
   * Get all notifications by user id
   *
   * @param userId
   * @return
   */
  Page<Notification> getAllByUserId(UUID userId, Pageable pageable);

  /**
   * Get all notifications by talent id
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

  /**
   * Delete all notifications by talent id
   *
   * @param talentId
   */
  @Transactional
  void deleteAll(UUID talentId);

  Long getUnreadNotificationsCount(UUID id);

  /**
   * Finds a notification by type, a key-value pair in its JSON data, and user ID.
   *
   * @param type the notification type
   * @param jsonKey the key in the JSON data
   * @param jsonValue the value associated with the jsonKey
   * @param userId the user ID
   * @return an Optional containing the found Notification, or an empty Optional if not found
   *     <p>The data field in the notifications table is stored as JSON. This method uses a native
   *     SQL query to search for a notification where the type matches, the JSON data contains the
   *     specified key-value pair, and the user ID matches. The query uses the PostgreSQL JSONB
   *     operator `->>` to extract the value.
   */
  Optional<Notification> findByTypeAndDataAndUserId(
      NotificationType type, String jsonKey, String jsonValue, UUID userId);
}
