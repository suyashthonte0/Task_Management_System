package com.project.tasks.controller;

import com.project.tasks.entity.Task;
import com.project.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping()
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    public List<Task> retrieveTasks() {
        return taskService.retrieveTasks();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{taskId}")
    public Task retrieveTask(@PathVariable String taskId) {
        return taskService.retrieveTask(taskId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable String taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId) {
        return taskService.deleteTask(taskId);
    }
}
