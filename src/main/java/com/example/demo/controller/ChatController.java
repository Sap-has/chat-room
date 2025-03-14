package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    
    private final ChatService chatService;
    private final UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @MessageMapping("/chat.sendMessage/{roomCode}")
    @SendTo("/topic/{roomCode}")
    public ChatMessage sendMessage(@Valid @Payload ChatMessage chatMessage, StompHeaderAccessor headerAccessor) {
        logger.info("Received message: {}", chatMessage);
        // Create or update user information
        userService.createOrGetUser(chatMessage.getSender());
        // Save the chat message
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{roomCode}")
    @SendTo("/topic/{roomCode}")
    public ChatMessage addUser(@Valid @Payload ChatMessage chatMessage, StompHeaderAccessor headerAccessor) {
        logger.info("User joined: {}", chatMessage.getSender());
        userService.createOrGetUser(chatMessage.getSender());
        return chatMessage;
    }
}
