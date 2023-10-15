package com.project.tasks.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Boolean completed;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}