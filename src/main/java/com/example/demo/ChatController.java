package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    // Method to broadcast a chat message to all subscribers.
    @MessageMapping("/chat.sendMessage/{roomCode}")
    @SendTo("/topic/{roomCode}")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    // Method to handle new user joining.
    @MessageMapping("/chat.addUser/{roomCode}")
    @SendTo("/topic/{roomCode}")
    public ChatMessage addUser(ChatMessage chatMessage) {
        return chatMessage;
    }
}
