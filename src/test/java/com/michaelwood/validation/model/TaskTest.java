package com.michaelwood.validation.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void acceptsBoundaryValuesAndUpdatesMutableFields() {
        Task task = new Task(
                "T123456789",
                "n".repeat(20),
                "d".repeat(50));

        task.setName("Run tests");
        task.setDescription("Run the complete unit test suite");

        assertAll(
                () -> assertEquals("T123456789", task.getId()),
                () -> assertEquals("Run tests", task.getName()),
                () -> assertEquals("Run the complete unit test suite", task.getDescription()));
    }

    @Test
    void rejectsInvalidConstructorValues() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Task(null, "Name", "Description")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Task("", "Name", "Description")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Task("12345678901", "Name", "Description")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Task("T-1", "x".repeat(21), "Description")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Task("T-1", "Name", "x".repeat(51))));
    }

    @Test
    void rejectsInvalidUpdatesWithoutChangingState() {
        Task task = new Task("T-100", "Review code", "Review validation rules");

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> task.setName("")),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> task.setDescription(null)),
                () -> assertEquals("Review code", task.getName()),
                () -> assertEquals("Review validation rules", task.getDescription()));
    }
}
