package com.project.tasks.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class TaskEntity {

    @Id
    @Generated
    private int id;
    private String title;
    private String description;
    private boolean completed;
}