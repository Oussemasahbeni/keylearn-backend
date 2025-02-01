package com.oussema.keylearn.notifications.infrastructure.controller;

import com.oussema.keylearn.notifications.domain.model.Notification;
import com.oussema.keylearn.notifications.domain.port.input.NotificationUseCases;
import com.oussema.keylearn.notifications.infrastructure.dto.response.NotificationDto;
import com.oussema.keylearn.notifications.infrastructure.mapper.NotificationMapper;
import com.oussema.keylearn.shared.pagination.CustomPage;
import com.oussema.keylearn.shared.pagination.PageMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.oussema.keylearn.shared.pagination.PaginationUtils.createPageable;
import static com.oussema.keylearn.utils.AuthUtils.getCurrentAuthenticatedUserId;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(
    name = "Notifications",
    description =
        "Operations related to managing notifications, including marking as read, deleting, and retrieving notifications.")
@RequiredArgsConstructor
public class NotificationsController {

  private final NotificationUseCases notificationsUseCases;
  private final NotificationMapper notificationMapper;

  @Operation(
      summary = "Get all notifications",
      description = "Get all notifications for the current user.")
  @GetMapping("/all")
  public ResponseEntity<CustomPage<NotificationDto>> getAllNotifications(
      @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page) {
    UUID id = getCurrentAuthenticatedUserId();
    Pageable pageable = createPageable(page, size, "createdAt", "DESC");
    Page<Notification> notifications = this.notificationsUseCases.getAllByUserId(id, pageable);
    Page<NotificationDto> notificationDtos = notifications.map(notificationMapper::toDto);
    return new ResponseEntity<>(PageMapper.toCustomPage(notificationDtos), HttpStatus.OK);
  }

  @GetMapping("/unread-count")
  public ResponseEntity<Long> getUnreadNotificationsCount() {
    UUID id = getCurrentAuthenticatedUserId();
    return new ResponseEntity<>(
        this.notificationsUseCases.getUnreadNotificationsCount(id), HttpStatus.OK);
  }

  @Operation(
      summary = "Mark notification as read",
      description = "Mark a specific notification as read by its ID.")
  @PatchMapping("/{notificationId}")
  public ResponseEntity<Notification> markAsRead(@PathVariable UUID notificationId) {
    Notification updatedNotification = this.notificationsUseCases.markAsRead(notificationId);
    return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
  }

  @Operation(
      summary = "Mark all notifications as read",
      description = "Mark all notifications for the current user as read.")
  @PatchMapping("/all")
  public ResponseEntity<Boolean> markAllAsRead() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String id = authentication.getName();
    return new ResponseEntity<>(
        this.notificationsUseCases.markAllAsRead(UUID.fromString(id)), HttpStatus.OK);
  }

  @Operation(
      summary = "Delete notification",
      description = "Delete a specific notification by its ID.")
  @DeleteMapping("/{notificationId}")
  public ResponseEntity<Void> deleteNotification(@PathVariable UUID notificationId) {
    this.notificationsUseCases.deleteById(notificationId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Operation(
      summary = "Delete all notifications",
      description = "Delete all notifications for the current user.")
  @DeleteMapping("/all")
  public ResponseEntity<Void> deleteAllNotifications() {
    UUID id = getCurrentAuthenticatedUserId();
    this.notificationsUseCases.deleteAll(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
