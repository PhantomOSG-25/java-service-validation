package com.michaelwood.validation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.michaelwood.validation.model.Task;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
    @Test
    void managesUniqueTasks() {
        TaskService service = new TaskService();
        Task task = new Task("T-100", "Review code", "Review service validation");
        service.add(task);

        service.get("T-100").setName("Run tests");

        assertEquals("Run tests", service.get("T-100").getName());
        assertThrows(IllegalArgumentException.class, () ->
                service.add(new Task("T-100", "Duplicate", "Duplicate id")));

        service.delete("T-100");
        assertThrows(NoSuchElementException.class, () -> service.get("T-100"));
    }
}
