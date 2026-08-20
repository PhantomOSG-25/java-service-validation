package com.michaelwood.validation.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.michaelwood.validation.model.Appointment;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {
    private static final LocalDate TODAY = LocalDate.of(2026, 8, 12);
    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2026-08-12T12:00:00Z"),
            ZoneOffset.UTC);

    private static Appointment appointment(String id) {
        return new Appointment(id, TODAY.plusDays(7), "Project review", FIXED_CLOCK);
    }

    @Test
    void supportsCreateReadUpdateDeleteWorkflow() {
        AppointmentService service = new AppointmentService();
        service.add(appointment("A-100"));

        service.updateDate("A-100", TODAY.plusDays(14));
        service.updateDescription("A-100", "Release review");

        assertAll(
                () -> assertEquals(TODAY.plusDays(14), service.get("A-100").getDate()),
                () -> assertEquals("Release review", service.get("A-100").getDescription()),
                () -> assertEquals(1, service.getAll().size()));

        service.delete("A-100");

        assertAll(
                () -> assertEquals(0, service.getAll().size()),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.get("A-100")));
    }

    @Test
    void rejectsNullDuplicatesAndMissingIdentifiers() {
        AppointmentService service = new AppointmentService();
        service.add(appointment("A-100"));

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.add(null)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.add(appointment("A-100"))),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.updateDescription("missing", "Review")),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.delete("missing")),
                () -> assertThrows(
                        UnsupportedOperationException.class,
                        () -> service.getAll().clear()));
    }
}
