package com.example.todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 18.07.2026
 * <p>
 * Time: 18:34
 * <p>
 * Project name: ToDoList
 */
public record UserCreateRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password

) {
}
