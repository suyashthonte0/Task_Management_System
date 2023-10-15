package com.project.tasks.service;

import com.project.tasks.entity.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    List<Task> retrieveTasks();
    Task updateTask(String taskId, Task task);
    Task retrieveTask(String taskId);
    String deleteTask(String taskId);
}
