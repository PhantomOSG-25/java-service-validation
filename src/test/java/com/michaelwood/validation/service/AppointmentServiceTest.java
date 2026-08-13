package com.michaelwood.validation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.michaelwood.validation.model.Appointment;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {
    private static Appointment appointment(String id) {
        return new Appointment(id, LocalDate.now().plusDays(7), "Project review");
    }

    @Test
    void managesUniqueAppointments() {
        AppointmentService service = new AppointmentService();
        service.add(appointment("A-100"));

        assertEquals("A-100", service.get("A-100").getId());
        assertThrows(IllegalArgumentException.class, () ->
                service.add(appointment("A-100")));

        service.delete("A-100");
        assertThrows(NoSuchElementException.class, () -> service.get("A-100"));
    }
}
