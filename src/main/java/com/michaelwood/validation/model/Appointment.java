package com.michaelwood.validation.model;

import java.time.Clock;
import java.time.LocalDate;

/** An appointment that cannot be scheduled in the past. */
public final class Appointment {
    private final String id;
    private LocalDate date;
    private String description;
    private final Clock clock;

    public Appointment(String id, LocalDate date, String description) {
        this(id, date, description, Clock.systemDefaultZone());
    }

    public Appointment(String id, LocalDate date, String description, Clock clock) {
        this.id = requireText(id, 10, "id");
        if (clock == null) {
            throw new IllegalArgumentException("clock cannot be null");
        }
        this.clock = clock;
        setDate(date);
        setDescription(description);
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null || date.isBefore(LocalDate.now(clock))) {
            throw new IllegalArgumentException("date cannot be null or in the past");
        }
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = requireText(description, 50, "description");
    }

    private static String requireText(String value, int maxLength, String field) {
        if (value == null || value.isBlank() || value.length() > maxLength) {
            throw new IllegalArgumentException(
                    field + " must contain 1 to " + maxLength + " characters");
        }
        return value;
    }
}
