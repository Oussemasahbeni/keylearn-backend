package com.oussema.keylearn.notifications.domain.port.output;

public interface WsNotification {

  void send(String userId, Object payload);
}
