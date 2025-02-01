//package com.oussema.keylearn.config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitmqConfig {
//
//  @Value("${spring.rabbitmq.host}")
//  private String host;
//
//  @Value("${spring.rabbitmq.port}")
//  private int port;
//
//  @Value("${spring.rabbitmq.username}")
//  private String username;
//
//  @Value("${spring.rabbitmq.password}")
//  private String password;
//
//  public static final String NOTIFICATION_QUEUE = "notifications-queue";
//
//  @Bean
//  public Queue notificationsQueue() {
//    return new Queue(NOTIFICATION_QUEUE, true);
//  }
//
//  @Bean
//  public CachingConnectionFactory connectionFactory() {
//    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
//    cachingConnectionFactory.setPort(port);
//    cachingConnectionFactory.setUsername(username);
//    cachingConnectionFactory.setPassword(password);
//    return cachingConnectionFactory;
//  }
//
//  @Bean
//  public MessageConverter jsonMessageConverter() {
//    return new Jackson2JsonMessageConverter();
//  }
//
//  @Bean
//  public RabbitTemplate rabbitTemplate() {
//    RabbitTemplate template = new RabbitTemplate(connectionFactory());
//    template.setMessageConverter(jsonMessageConverter());
//    return template;
//  }
//}
