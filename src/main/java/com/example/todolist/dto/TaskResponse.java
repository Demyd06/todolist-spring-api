package com.example.todolist.dto;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 14.07.2026
 * <p>
 * Time: 22:04
 * <p>
 * Project name: ToDoList
 */
public record TaskResponse(
        String taskId,
        String title,
        String status
) {
}
