package xyz.lib.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
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

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String STOMP_ENDPOINT = "/stomp-endpoint";
    private static final String SIMPLE_BROKER[] = {"/topic"};
    private static final String DEST_PREFIX = "/room";

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint(STOMP_ENDPOINT)
                .setAllowedOrigins("http://localhost:4201").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(SIMPLE_BROKER);
        registry.setApplicationDestinationPrefixes(DEST_PREFIX, "connect", "disconnect");
    }

    @EventListener
    public void onSocketConnected(SessionConnectedEvent event) {
        // Intercepted in AOP for logging.
    }

    @EventListener
    public void onSocketDisconnected(SessionDisconnectEvent event) {
        // Intercepted in AOP for logging.
    }
}
