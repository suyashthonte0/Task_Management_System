package com.project.tasks.service;

import com.project.tasks.entity.Task;
import com.project.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
}
