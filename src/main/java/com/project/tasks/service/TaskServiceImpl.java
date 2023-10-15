package com.project.tasks.service;

import com.project.tasks.entity.Task;
import com.project.tasks.exception.TaskNotFoundException;
import com.project.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task saveTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while saving the task: " + ex.getMessage());
        }
    }

    @Override
    public List<Task> retrieveTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task retrieveTask(String taskId) {
        try {
            return taskRepository.getReferenceById(Integer.parseInt(taskId));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid task ID format: " + taskId);
        } catch (EntityNotFoundException ex) {
            throw new TaskNotFoundException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public Task updateTask(String taskId, Task task) {
        try {
            int taskIdInt = Integer.parseInt(taskId);
            Task existingTask = taskRepository.findById(taskIdInt)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

            if (task.getTitle() != null) {
                existingTask.setTitle(task.getTitle());
            }

            if (task.getDescription() != null) {
                existingTask.setDescription(task.getDescription());
            }

            if (task.getCompleted() != null) {
                existingTask.setCompleted(task.getCompleted());
            }

            if (task.getPriority() != null) {
                existingTask.setPriority(task.getPriority());
            }

            return taskRepository.save(existingTask);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid task ID format: " + taskId);
        }
    }

    @Override
    public String deleteTask(String taskId) {
        try {
            taskRepository.deleteById(Integer.parseInt(taskId));
            return "Task Id : " + taskId + " deleted successfully.";
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid task ID format: " + taskId);
        } catch (EntityNotFoundException ex) {
            throw new TaskNotFoundException("Task not found with ID: " + taskId);
        }
    }
}
