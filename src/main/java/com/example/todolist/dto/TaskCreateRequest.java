package com.example.todolist.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 14.07.2026
 * <p>
 * Time: 21:50
 * <p>
 * Project name: ToDoList
 */
public record TaskCreateRequest(
        String title,
        @NotBlank(message = "Description is required")
        String description
) {
}
