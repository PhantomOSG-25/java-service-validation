package com.michaelwood.validation.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.michaelwood.validation.model.Task;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
    private static Task task(String id) {
        return new Task(id, "Review code", "Review service validation");
    }

    @Test
    void supportsCreateReadUpdateDeleteWorkflow() {
        TaskService service = new TaskService();
        service.add(task("T-100"));

        service.updateName("T-100", "Run tests");
        service.updateDescription("T-100", "Run the complete unit test suite");

        assertAll(
                () -> assertEquals("Run tests", service.get("T-100").getName()),
                () -> assertEquals(
                        "Run the complete unit test suite",
                        service.get("T-100").getDescription()),
                () -> assertEquals(1, service.getAll().size()));

        service.delete("T-100");

        assertAll(
                () -> assertEquals(0, service.getAll().size()),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.get("T-100")));
    }

    @Test
    void rejectsNullDuplicatesAndMissingIdentifiers() {
        TaskService service = new TaskService();
        service.add(task("T-100"));

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.add(null)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.add(task("T-100"))),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.updateName("missing", "Run tests")),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.delete("missing")),
                () -> assertThrows(
                        UnsupportedOperationException.class,
                        () -> service.getAll().clear()));
    }
}
