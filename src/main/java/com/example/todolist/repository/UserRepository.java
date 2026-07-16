package com.example.todolist.repository;

import com.example.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 16.07.2026
 * <p>
 * Time: 21:27
 * <p>
 * Project name: ToDoList
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
