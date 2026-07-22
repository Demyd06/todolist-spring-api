package com.example.todolist.service;

import com.example.todolist.dto.TaskCreateRequest;
import com.example.todolist.dto.TaskResponse;
import com.example.todolist.entity.Task;
import com.example.todolist.entity.User;
import com.example.todolist.exception.TaskNotFoundException;
import com.example.todolist.exception.UserNotFoundException;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final UserRepository userRepository;

    public TaskResponse createTask(TaskCreateRequest taskCreateRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found with this username"));

        Task newTask = new Task();
        newTask.setTitle(taskCreateRequest.title());
        newTask.setDescription(taskCreateRequest.description());
        newTask.setStatus("CREATED");
        newTask.setUser(currentUser);

        taskRepository.save(newTask);

        return new TaskResponse(
                newTask.getId().toString(),
                newTask.getTitle(),
                newTask.getStatus()
        );
    }

    public Page<TaskResponse> getAllTasks(Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found with this username"));

        Page<Task> tasks = taskRepository.findByUserId(user.getId(), pageable);
        return tasks.map(task -> new TaskResponse(
                            task.getId().toString(),
                            task.getTitle(),
                            task.getStatus()
                        ));
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

    public Page<TaskResponse> getTaskByUserId(Long userId, Pageable pageable){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found with this username"));
        return taskRepository.findByUserId(currentUser.getId(), pageable)
                .map(task -> new TaskResponse(
                        task.getId().toString(),
                        task.getTitle(),
                        task.getStatus()
                ));
    }
}
