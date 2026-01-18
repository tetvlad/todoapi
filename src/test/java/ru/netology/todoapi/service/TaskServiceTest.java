package ru.netology.todoapi.service;

import org.junit.jupiter.api.Test;
import ru.netology.todoapi.model.Task;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private final TaskService service = new TaskService();

    @Test
    void shouldCreateTask() {
        Task task = new Task();
        task.setTitle("Test");

        Task result = service.create(task);

        assertEquals(1, result.getId());
        assertEquals("Test", result.getTitle());
        assertFalse(result.isCompleted());
    }

    @Test
    void shouldUpdateTask() {
        Task task = new Task();
        task.setTitle("Old");
        Task created = service.create(task);

        Task update = new Task();
        update.setTitle("New");

        Task result = service.update(created.getId(), update);

        assertEquals("New", result.getTitle());
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task();
        task.setTitle("To delete");
        Task created = service.create(task);

        service.delete(created.getId());

        assertThrows(
                NoSuchElementException.class,
                () -> service.delete(created.getId())
        );
    }

    @Test
    void shouldThrowWhenUpdateNotFound() {
        Task task = new Task();
        task.setTitle("X");

        assertThrows(
                NoSuchElementException.class,
                () -> service.update(13, task)
        );
    }
}