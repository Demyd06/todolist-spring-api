package com.example.todolist.exception;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 22.07.2026
 * <p>
 * Time: 13:30
 * <p>
 * Project name: ToDoList
 */
public class UserInvalidPasswordException extends RuntimeException {
    public UserInvalidPasswordException(String message) {
        super(message);
    }
}
