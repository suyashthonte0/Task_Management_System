package com.project.tasks.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.tasks.util.TaskSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@JsonSerialize(using = TaskSerializer.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean completed;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}