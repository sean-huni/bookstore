package xyz.lib.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.config
 * USER      : sean
 * DATE      : 19-Sat-Jan-2019
 * TIME      : 17:11
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/socket.io");
        stompEndpointRegistry.addEndpoint("/socket.io").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/message", "/chat", "/queue/", "/topic/");
        registry.setApplicationDestinationPrefixes("/app", "/chat");
    }

    @EventListener
    public void onSocketConnected(SessionConnectedEvent event) {
    }

    @EventListener
    public void onSocketDisconnected(SessionDisconnectEvent event) {
    }

}
