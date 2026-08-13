package com.michaelwood.validation.service;

import com.michaelwood.validation.model.Appointment;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/** In-memory appointment service with unique identifiers. */
public final class AppointmentService {
    private final Map<String, Appointment> appointments = new LinkedHashMap<>();

    public void add(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("appointment cannot be null");
        }
        if (appointments.putIfAbsent(appointment.getId(), appointment) != null) {
            throw new IllegalArgumentException("appointment id must be unique");
        }
    }

    public Appointment get(String id) {
        Appointment appointment = appointments.get(id);
        if (appointment == null) {
            throw new NoSuchElementException("appointment not found: " + id);
        }
        return appointment;
    }

    public void delete(String id) {
        if (appointments.remove(id) == null) {
            throw new NoSuchElementException("appointment not found: " + id);
        }
    }

    public List<Appointment> getAll() {
        return List.copyOf(appointments.values());
    }
}
