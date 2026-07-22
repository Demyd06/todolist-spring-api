package com.example.todolist.dto;

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
public record UserResponse(
        Long id,
        String username,
        String email,
        String token
) {
}
