package com.project.tasks.listener;

import com.project.tasks.controller.TasksController;
import com.project.tasks.entity.Task;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TaskMessageListener {

    @Autowired
    private TasksController tasksController;

    @RabbitListener(queues = "task-queue")
    public void receiveMessage(@Payload String message) {
        String[] messageParts = message.split(",");

        Task task = new Task();
        task.setTitle(messageParts[0]);
        task.setDescription(messageParts[1]);
        task.setCompleted(Boolean.parseBoolean(messageParts[2]));
        task.setPriority(Task.Priority.valueOf(messageParts[3].toUpperCase()));
        
        Task savedTask = tasksController.saveTask(task);

        System.out.println("Received message from task-queue: " + message);
        System.out.println("Created task with ID: " + savedTask.getId());
    }
}
