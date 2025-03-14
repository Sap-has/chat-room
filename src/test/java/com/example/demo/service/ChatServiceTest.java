package com.example.demo.service;

import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ChatServiceTest {

    private final ChatMessageRepository chatMessageRepository = Mockito.mock(ChatMessageRepository.class);
    private final ChatService chatService = new ChatService(chatMessageRepository);

    @Test
    void saveMessage() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("Hello");
        chatMessage.setSender("testUser");
        chatMessage.setRoomCode("ROOM1");
        chatMessage.setType(ChatMessage.MessageType.CHAT);

        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

        ChatMessage savedMessage = chatService.saveMessage(chatMessage);

        assertNotNull(savedMessage);
        assertEquals("Hello", savedMessage.getContent());
        assertEquals("testUser", savedMessage.getSender());
        assertEquals("ROOM1", savedMessage.getRoomCode());
        assertNotNull(savedMessage.getTimestamp());
    }

    @Test
    void getMessagesByRoom() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("Hello");
        chatMessage.setSender("testUser");
        chatMessage.setRoomCode("ROOM1");
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setType(ChatMessage.MessageType.CHAT);

        when(chatMessageRepository.findByRoomCode("ROOM1")).thenReturn(Collections.singletonList(chatMessage));

        List<ChatMessage> messages = chatService.getMessagesByRoom("ROOM1");

        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertEquals("Hello", messages.get(0).getContent());
    }
}
