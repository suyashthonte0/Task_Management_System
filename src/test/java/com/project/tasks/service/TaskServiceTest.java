package com.project.tasks.service;

import com.project.tasks.entity.Task;
import com.project.tasks.exception.TaskNotFoundException;
import com.project.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TaskServiceTest {

	@InjectMocks
	private TaskService taskService = new TaskServiceImpl();

	@Mock
	private TaskRepository taskRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveTask() {
		Task taskToSave = new Task(1, "Task 1", "Description 1", false, Task.Priority.LOW);
		Task savedTask = new Task(1, "Task 1", "Description 1", false, Task.Priority.LOW);

		Mockito.when(taskRepository.save(taskToSave)).thenReturn(savedTask);

		Task result = taskService.saveTask(taskToSave);

		assertEquals(savedTask, result);
	}

	@Test
	public void testRetrieveTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(1, "Task 1", "Description 1", false, Task.Priority.MEDIUM));
		tasks.add(new Task(2, "Task 2", "Description 2", true, Task.Priority.MEDIUM));

		Mockito.when(taskRepository.findAll()).thenReturn(tasks);

		List<Task> result = taskService.retrieveTasks();

		assertEquals(tasks, result);
	}

	@Test
	public void testRetrieveTaskById() {
		Task existingTask = new Task(1, "Task 1", "Description 1", false, Task.Priority.HIGH);

		Mockito.when(taskRepository.getReferenceById(1)).thenReturn(existingTask);

		Task result = taskService.retrieveTask("1");

		assertEquals(existingTask, result);
	}

	@Test
	public void testRetrieveTaskByIdNotFound() {
		Mockito.doThrow(new TaskNotFoundException("1")).when(taskRepository).getReferenceById(1);

		assertThrows(TaskNotFoundException.class, () -> taskService.retrieveTask("1"));
	}

	@Test
	public void testUpdateTask() {
		Task taskToUpdate = new Task(1, "Updated Task", "Updated Description", true, Task.Priority.LOW);
		Task updatedTask = new Task(1, "Updated Task", "Updated Description", true, Task.Priority.HIGH);

		Mockito.when(taskRepository.findById(1)).thenReturn(Optional.of(new Task(1, "Task 1", "Description 1", false, Task.Priority.LOW)));
		Mockito.when(taskRepository.save(taskToUpdate)).thenReturn(updatedTask);

		Task result = taskService.updateTask("1", taskToUpdate);

		assertEquals(updatedTask, result);
		assertEquals(updatedTask.getPriority(), result.getPriority());
	}

	@Test
	public void testUpdateTaskNotFound() {
		Task taskToUpdate = new Task(1, "Updated Task", "Updated Description", true, Task.Priority.LOW);

		Mockito.when(taskRepository.existsById(1)).thenReturn(false);

		assertThrows(TaskNotFoundException.class, () -> taskService.updateTask("1", taskToUpdate));
	}

	@Test
	public void testDeleteTask() {
		Mockito.when(taskRepository.existsById(1)).thenReturn(true);

		String result = taskService.deleteTask("1");

		assertEquals("Task Id : 1 deleted successfully.", result);
	}

	@Test
	public void testDeleteTaskNotFound() {
		Mockito.doThrow(new TaskNotFoundException("1")).when(taskRepository).deleteById(1);

		assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask("1"));
	}
}
