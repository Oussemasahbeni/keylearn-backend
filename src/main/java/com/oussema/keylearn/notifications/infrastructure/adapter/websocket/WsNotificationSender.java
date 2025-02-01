package com.oussema.keylearn.notifications.infrastructure.adapter.websocket;

import com.oussema.keylearn.notifications.domain.port.output.WsNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class WsNotificationSender implements WsNotification {
  private final SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void send(String userId, Object payload) {
    log.info("Sending message to user: {}", userId);
    simpMessagingTemplate.convertAndSendToUser(userId, "/notifications", payload);
  }
}
