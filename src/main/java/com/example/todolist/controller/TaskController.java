package com.example.todolist.controller;

import com.example.todolist.dto.TaskCreateRequest;
import com.example.todolist.dto.TaskResponse;
import com.example.todolist.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TaskResponse createTask(@Valid @RequestBody() TaskCreateRequest request){
        return taskService.createTask(request);
    }

    @GetMapping("/tasks")
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskCreateRequest request){
        return taskService.updateTask(id, request);
    }
}
