package com.oussema.keylearn.config.websocket;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.messaging.context.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@Log4j2
@EnableConfigurationProperties(WebSocketProperties.class)
@AllArgsConstructor
@Order(
    Ordered.HIGHEST_PRECEDENCE
        + 99) // Ensure that this configuration is loaded before the security configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final WebSocketProperties webSocketProperties;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    log.info(
        "Configuring message broker with destination prefixes: {}",
        webSocketProperties.getApplicationDestinationPrefix());

    registry.enableSimpleBroker(webSocketProperties.getBroker());
    registry.setApplicationDestinationPrefixes(
        webSocketProperties.getApplicationDestinationPrefix());
    registry.setUserDestinationPrefix(webSocketProperties.getUserDestinationPrefix());
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    log.info("Registering STOMP endpoints at endpoint: {}", webSocketProperties.getEndpoint());
    stompEndpointRegistry
        .addEndpoint(webSocketProperties.getEndpoint())
        .setAllowedOrigins(webSocketProperties.getAllowedOrigins())
        .withSockJS();
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
  }

  //    @Override
  //    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
  //        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
  //        resolver.setDefaultMimeType(APPLICATION_JSON);
  //        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
  //        converter.setObjectMapper(new ObjectMapper();
  //        converter.setContentTypeResolver(resolver);
  //        messageConverters.add(converter);
  //        return false;
  //    }

}
