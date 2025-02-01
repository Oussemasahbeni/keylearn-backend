package com.oussema.keylearn.notifications.infrastructure.repository;

import com.oussema.keylearn.notifications.infrastructure.entity.NotificationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository
    extends JpaRepository<NotificationEntity, UUID>, JpaSpecificationExecutor<NotificationEntity> {

  @Modifying
  @Query(
      "UPDATE NotificationEntity n SET n.isRead = true WHERE n.userId = :userId AND n.isRead = false")
  void markAllAsReadByUserId(@Param("userId") UUID userId);

  @Transactional
  @Query(
      value =
          "SELECT * FROM notifications n WHERE n.type = :type AND n.data->>:key = :value AND n.user_id = :userId",
      nativeQuery = true)
  Optional<NotificationEntity> findByTypeAndDataAndUserId(
      @Param("type") String type,
      @Param("key") String key,
      @Param("value") String value,
      @Param("userId") UUID userId);

  @Modifying
  @Query("DELETE FROM NotificationEntity n WHERE n.userId = :userId")
  void deleteAllByUserId(@Param("userId") UUID userId);

  Long countByUserIdAndIsReadFalse(UUID id);
}
