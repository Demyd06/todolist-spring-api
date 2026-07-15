package com.example.todolist.dto;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 14.07.2026
 * <p>
 * Time: 21:51
 * <p>
 * Project name: ToDoList
 */
public record TaskResponse(
        String taskID,
        String title,
        String status
) {
}
