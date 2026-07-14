package com.example.todolist.dto;

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
        String description
) {
}
