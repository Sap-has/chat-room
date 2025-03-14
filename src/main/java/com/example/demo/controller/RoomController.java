package com.example.demo.controller;

import com.example.demo.util.RoomCodeGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    @GetMapping("/api/roomcode")
    public String generateRoomCode() {
        return RoomCodeGenerator.generateRoomCode();
    }
}
