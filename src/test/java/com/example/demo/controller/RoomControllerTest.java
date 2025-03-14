package com.example.demo.controller;

import com.example.demo.util.RoomCodeGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(RoomController.class)
@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generateRoomCode() throws Exception {
        String expectedRoomCode = "TESTCODE";
        try (MockedStatic<RoomCodeGenerator> mockedStatic = mockStatic(RoomCodeGenerator.class)) {
            mockedStatic.when(RoomCodeGenerator::generateRoomCode).thenReturn(expectedRoomCode);

            mockMvc.perform(get("/api/roomcode"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(expectedRoomCode));
        }
    }
}
