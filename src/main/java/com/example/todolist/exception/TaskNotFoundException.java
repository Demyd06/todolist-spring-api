package com.example.todolist.exception;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 16.07.2026
 * <p>
 * Time: 18:40
 * <p>
 * Project name: ToDoList
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
