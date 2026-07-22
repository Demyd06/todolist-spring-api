package com.example.todolist.dto;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 22.07.2026
 * <p>
 * Time: 13:17
 * <p>
 * Project name: ToDoList
 */
public record UserLoginResponse(
        String message,
        String token
) {
}
