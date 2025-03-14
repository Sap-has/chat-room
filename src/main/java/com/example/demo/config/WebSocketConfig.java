package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import org.springframework.lang.NonNull;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
        // Enables a simple in-memory broker with a destination prefix for outgoing messages.
        config.enableSimpleBroker("/topic");
        // Prefix for filtering destinations targeted for application-level message handling (i.e., methods annotated with @MessageMapping).
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        // Registers the WebSocket endpoint that clients will use to connect to your server.
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:8080", "https://chatty-buddies.onrender.com/") // Allow all origins, adjust as needed for security
                .withSockJS();
    }
}