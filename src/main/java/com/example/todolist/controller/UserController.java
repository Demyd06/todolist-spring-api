package com.example.todolist.controller;

import com.example.todolist.dto.UserCreateRequest;
import com.example.todolist.dto.UserResponse;
import com.example.todolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 18.07.2026
 * <p>
 * Time: 18:37
 * <p>
 * Project name: ToDoList
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public UserResponse registerUser(@Valid @RequestBody UserCreateRequest request){
        return userService.createUser(request);
    }
}
