package com.example.todolist.service;

import com.example.todolist.dto.TaskCreateRequest;
import com.example.todolist.dto.TaskResponse;
import com.example.todolist.entity.Task;
import com.example.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 14.07.2026
 * <p>
 * Time: 21:49
 * <p>
 * Project name: ToDoList
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponse createTask(TaskCreateRequest taskCreateRequest){
        Task task = new Task();
        task.setTitle(taskCreateRequest.title());
        task.setDescription(taskCreateRequest.description());
        task.setStatus("CREATED");

        Task saveTask = taskRepository.save(task);
        return new TaskResponse(
                saveTask.getId().toString(),
                saveTask.getTitle(),
                saveTask.getStatus()
        );
    }
}
