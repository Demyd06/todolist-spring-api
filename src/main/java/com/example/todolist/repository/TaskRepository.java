package com.example.todolist.repository;

import com.example.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 15.07.2026
 * <p>
 * Time: 21:16
 * <p>
 * Project name: ToDoList
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
}
