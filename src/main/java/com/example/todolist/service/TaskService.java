package com.example.todolist.service;

import com.example.todolist.dto.TaskCreateRequest;
import com.example.todolist.dto.TaskResponse;
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
public class TaskService {
    public TaskResponse createTask(TaskCreateRequest taskCreateRequest){
        return new TaskResponse("TaskID-1", taskCreateRequest.title(), "CREATED");
    }
}
