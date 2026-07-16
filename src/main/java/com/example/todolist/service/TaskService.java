package com.example.todolist.service;

import com.example.todolist.dto.TaskCreateRequest;
import com.example.todolist.dto.TaskResponse;
import com.example.todolist.entity.Task;
import com.example.todolist.exception.TaskNotFoundException;
import com.example.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TaskResponse> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                        .map(task -> new TaskResponse(
                            task.getId().toString(),
                            task.getTitle(),
                            task.getStatus()
                        ))
                .toList();
    }

    public String deleteTask(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return "Task with id: " + id + " deleted";
        }
        return "Task with id: " + id + " not found";
    }

    public TaskResponse updateTask(Long id, TaskCreateRequest taskCreateRequest){
        Task exitingTask = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task with id: " + id + " not found"));

        exitingTask.setTitle(taskCreateRequest.title());
        exitingTask.setDescription(taskCreateRequest.description());
        exitingTask.setStatus("UPDATED");

        taskRepository.save(exitingTask);
        return new TaskResponse(
                exitingTask.getId().toString(),
                exitingTask.getTitle(),
                exitingTask.getStatus()
        );
    }
}
