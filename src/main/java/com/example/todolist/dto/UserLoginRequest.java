package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 22.07.2026
 * <p>
 * Time: 13:11
 * <p>
 * Project name: ToDoList
 */
public record UserLoginRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {
}
