package com.michaelwood.validation.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class AppointmentTest {
    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2026-08-12T12:00:00Z"),
            ZoneOffset.UTC);

    @Test
    void acceptsTodayAndFutureDates() {
        Appointment appointment = new Appointment(
                "A-100",
                LocalDate.of(2026, 8, 12),
                "Project review",
                FIXED_CLOCK);

        appointment.setDate(LocalDate.of(2026, 9, 1));

        assertEquals("A-100", appointment.getId());
        assertEquals(LocalDate.of(2026, 9, 1), appointment.getDate());
    }

    @Test
    void rejectsPastDatesAndInvalidDescriptions() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "A-100",
                        LocalDate.of(2026, 8, 11),
                        "Past appointment",
                        FIXED_CLOCK));
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "A-100",
                        LocalDate.of(2026, 8, 13),
                        "x".repeat(51),
                        FIXED_CLOCK));
    }
}
