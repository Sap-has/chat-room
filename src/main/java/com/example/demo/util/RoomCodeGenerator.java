package com.example.demo.util;

import java.security.SecureRandom;

public class RoomCodeGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
        private static final int CODE_LENGTH = 5;
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
        public static String generateRoomCode() {
            StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}
