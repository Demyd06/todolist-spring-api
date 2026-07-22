package com.example.todolist.service;

import com.example.todolist.dto.UserCreateRequest;
import com.example.todolist.dto.UserLoginRequest;
import com.example.todolist.dto.UserLoginResponse;
import com.example.todolist.dto.UserResponse;
import com.example.todolist.entity.User;
import com.example.todolist.exception.UserNotFoundException;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 22.07.2026
 * <p>
 * Time: 14:13
 * <p>
 * Project name: ToDoList
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService; // <--- Забули про нього раніше, а він потрібен для токена!

    @InjectMocks
    private UserService userService;

    private UserCreateRequest request;
    private User savedUser;

    @BeforeEach
    void setUp() {
        request = new UserCreateRequest("demyd", "demyd@example.com", "password123");

        savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("demyd");
        savedUser.setEmail("demyd@example.com");
        savedUser.setPassword("encoded_password_hash");
    }

    @Test
    @DisplayName("Should create user successfully")
    void shouldCreateUserSuccessfully() {
        when(passwordEncoder.encode(request.password())).thenReturn("encoded_password_hash");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any())).thenReturn("mock-jwt-token");

        UserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("demyd", response.username());
        assertEquals("demyd@example.com", response.email());
        assertEquals("mock-jwt-token", response.token());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should delete user successfully")
    void shouldDeleteUserSuccessfully() {
        when(userRepository.existsById(1L)).thenReturn(true);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should not delete user when not found")
    void shouldNotDeleteUserWhenNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);
        userService.deleteUser(1L);
        verify(userRepository, never()).deleteById(1L);
    }

    @Test
    void shouldLoginUserSuccessfully() {
        UserLoginRequest loginRequest = new UserLoginRequest("demyd", "password123");
        when(userRepository.findByUsername(loginRequest.username())).thenReturn(Optional.of(savedUser));
        when(passwordEncoder.matches(loginRequest.password(), savedUser.getPassword())).thenReturn(true);
        when(jwtService.generateToken(any())).thenReturn("mock-jwt-token");

        UserLoginResponse response = userService.loginUser(loginRequest);

        assertNotNull(response);
        assertEquals("Login successful", response.message());
        assertEquals("mock-jwt-token", response.token());

        verify(userRepository, times(1)).findByUsername(loginRequest.username());
        verify(passwordEncoder, times(1)).matches(loginRequest.password(), savedUser.getPassword());
        verify(jwtService, times(1)).generateToken(any());
    }
}