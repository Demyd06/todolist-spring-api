package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 15.07.2026
 * <p>
 * Time: 21:12
 * <p>
 * Project name: ToDoList
 */
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    private String status;
}
