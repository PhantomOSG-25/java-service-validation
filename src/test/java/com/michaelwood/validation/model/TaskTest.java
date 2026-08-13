package com.michaelwood.validation.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void createsAndUpdatesValidTask() {
        Task task = new Task("T-100", "Review code", "Review the validation service");

        task.setName("Run tests");
        task.setDescription("Run the complete unit test suite");

        assertEquals("T-100", task.getId());
        assertEquals("Run tests", task.getName());
        assertEquals("Run the complete unit test suite", task.getDescription());
    }

    @Test
    void rejectsNullEmptyAndOversizedValues() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(null, "Name", "Description"));
        assertThrows(IllegalArgumentException.class, () ->
                new Task("T-1", "", "Description"));
        assertThrows(IllegalArgumentException.class, () ->
                new Task("T-1", "Name", "x".repeat(51)));
    }
}
