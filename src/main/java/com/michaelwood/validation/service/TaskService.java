package com.michaelwood.validation.service;

import com.michaelwood.validation.model.Task;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/** In-memory task service with unique identifiers. */
public final class TaskService {
    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null");
        }
        if (tasks.putIfAbsent(task.getId(), task) != null) {
            throw new IllegalArgumentException("task id must be unique");
        }
    }

    public Task get(String id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new NoSuchElementException("task not found: " + id);
        }
        return task;
    }

    public void delete(String id) {
        if (tasks.remove(id) == null) {
            throw new NoSuchElementException("task not found: " + id);
        }
    }

    public List<Task> getAll() {
        return List.copyOf(tasks.values());
    }
}
