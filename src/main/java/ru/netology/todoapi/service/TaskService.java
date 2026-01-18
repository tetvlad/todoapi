package ru.netology.todoapi.service;

import ru.netology.todoapi.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>();
    private long nextId = 1;

    public List<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task create(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task update(long id, Task task) {
        if (!tasks.containsKey(id)) {
            throw new NoSuchElementException("Task not found");
        }
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public void delete(long id) {
        if (!tasks.containsKey(id)) {
            throw new NoSuchElementException("Task not found");
        }
        tasks.remove(id);
    }
}
