package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.model.ChatMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    // Method to broadcast a chat message to all subscribers.
    @MessageMapping("/chat.sendMessage/{roomCode}")
    @SendTo("/topic/{roomCode}")
    public ChatMessage sendMessage(@Valid @Payload ChatMessage chatMessage, StompHeaderAccessor headerAccessor) {
        logger.info("Received message: {}", chatMessage);
        return chatMessage;
    }

    // Method to handle new user joining.
    @MessageMapping("/chat.addUser/{roomCode}")
    @SendTo("/topic/{roomCode}")
    public ChatMessage addUser(@Valid @Payload ChatMessage chatMessage, StompHeaderAccessor headerAccessor) {
        logger.info("User joined: {}", chatMessage.getSender());
        return chatMessage;
    }
}