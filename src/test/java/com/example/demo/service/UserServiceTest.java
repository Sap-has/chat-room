package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void createOrGetUser_NewUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        User user = userService.createOrGetUser("testUser");

        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void createOrGetUser_ExistingUser() {
        User existingUser = new User();
        existingUser.setUsername("testUser");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(existingUser));

        User user = userService.createOrGetUser("testUser");

        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
    }
}
