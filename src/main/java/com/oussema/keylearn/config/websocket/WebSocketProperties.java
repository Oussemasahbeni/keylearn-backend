package com.oussema.keylearn.config.websocket;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.websocket")
public class WebSocketProperties {

  private String endpoint;
  private String allowedOrigins;
  private String applicationDestinationPrefix;
  private String userDestinationPrefix;
  private String[] broker;
}
