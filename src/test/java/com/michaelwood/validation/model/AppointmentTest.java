package com.michaelwood.validation.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class AppointmentTest {
    private static final LocalDate TODAY = LocalDate.of(2026, 8, 12);
    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2026-08-12T12:00:00Z"),
            ZoneOffset.UTC);

    @Test
    void acceptsBoundaryValuesAndUpdates() {
        Appointment appointment = new Appointment(
                "A123456789",
                TODAY,
                "d".repeat(50),
                FIXED_CLOCK);

        appointment.setDate(TODAY.plusDays(1));
        appointment.setDescription("Design review");

        assertAll(
                () -> assertEquals("A123456789", appointment.getId()),
                () -> assertEquals(TODAY.plusDays(1), appointment.getDate()),
                () -> assertEquals("Design review", appointment.getDescription()));
    }

    @Test
    void rejectsInvalidConstructorValues() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment(null, TODAY, "Review", FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("", TODAY, "Review", FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("12345678901", TODAY, "Review", FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("A-100", null, "Review", FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("A-100", TODAY.minusDays(1), "Review", FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("A-100", TODAY, "", FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("A-100", TODAY, "x".repeat(51), FIXED_CLOCK)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Appointment("A-100", TODAY, "Review", null)));
    }

    @Test
    void rejectsInvalidUpdatesWithoutChangingState() {
        Appointment appointment = new Appointment(
                "A-100",
                TODAY.plusDays(2),
                "Project review",
                FIXED_CLOCK);

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> appointment.setDate(TODAY.minusDays(1))),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> appointment.setDescription(null)),
                () -> assertEquals(TODAY.plusDays(2), appointment.getDate()),
                () -> assertEquals("Project review", appointment.getDescription()));
    }
}
