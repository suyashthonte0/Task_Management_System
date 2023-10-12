package com.project.tasks.controller;

import com.project.tasks.entity.Task;
import com.project.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @GetMapping()
    public List<Task> retrieveTasks(){
        return taskService.retrieveTasks(task);
    }

    @GetMapping("/{taskId}")
    public List<Task> retrieveTasks(@PathVariable String taskId){
        return taskService.retrieveTasks(taskId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable String taskId){
        return taskService.updateTask(task);
    }

    @DeleteMapping("/deleteTask")
    public Task deleteTask(@RequestBody Task task){
        return taskService.deleteTask(task);
    }
}
