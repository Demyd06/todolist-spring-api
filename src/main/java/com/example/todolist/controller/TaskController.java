package com.example.todolist.controller;

import com.example.todolist.dto.TaskCreateRequest;
import com.example.todolist.dto.TaskResponse;
import com.example.todolist.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 14.07.2026
 * <p>
 * Time: 21:47
 * <p>
 * Project name: ToDoList
 */
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/tasks")
    public TaskResponse createTask(@Valid @RequestBody() TaskCreateRequest taskCreateRequest){
        return taskService.createTask(taskCreateRequest);
    }
}
