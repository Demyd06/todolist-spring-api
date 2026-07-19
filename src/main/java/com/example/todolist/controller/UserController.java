package com.example.todolist.controller;

import com.example.todolist.dto.TaskResponse;
import com.example.todolist.dto.UserCreateRequest;
import com.example.todolist.dto.UserResponse;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final TaskService taskService;

    @PostMapping("/users")
    public UserResponse registerUser(@Valid @RequestBody UserCreateRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/users/{id}/tasks")
    public List<TaskResponse> getTasksByUser(@PathVariable Long id){
        return taskService.getTaskByUserId(id);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
