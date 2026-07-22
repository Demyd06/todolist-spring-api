package com.example.todolist.service;

import com.example.todolist.dto.UserCreateRequest;
import com.example.todolist.dto.UserResponse;
import com.example.todolist.entity.User;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.security.CustomUserDetails;
import com.example.todolist.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 18.07.2026
 * <p>
 * Time: 18:36
 * <p>
 * Project name: ToDoList
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponse createUser(UserCreateRequest request){
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());

        User savedUser = userRepository.save(newUser);
        UserDetails userDetails = new CustomUserDetails(savedUser);

        String token = jwtService.generateToken(userDetails);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                token);
    }

    public String deleteUser(Long userId){
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return "User with id: " + userId + " deleted";
        }
        return "User with id: " + userId + " not found";
    }
}
