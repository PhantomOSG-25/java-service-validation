package com.michaelwood.validation.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {
    @Test
    void createsAndUpdatesValidContact() {
        Contact contact = new Contact(
                "C-100",
                "Michael",
                "Wood",
                "6155550100",
                "100 Main Street");

        contact.setFirstName("Mike");
        contact.setAddress("200 Oak Avenue");

        assertEquals("C-100", contact.getId());
        assertEquals("Mike", contact.getFirstName());
        assertEquals("200 Oak Avenue", contact.getAddress());
    }

    @Test
    void rejectsInvalidFieldsAtBoundaries() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", "Mike", "Wood", "6155550100", "Main"));
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C-1", "", "Wood", "6155550100", "Main"));
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C-1", "Mike", "Wood", "615-555-0100", "Main"));
    }
}
