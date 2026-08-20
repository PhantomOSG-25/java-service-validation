package com.michaelwood.validation.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {
    @Test
    void acceptsBoundaryValuesAndUpdatesEveryMutableField() {
        Contact contact = new Contact(
                "C123456789",
                "f".repeat(10),
                "l".repeat(10),
                "5555550100",
                "a".repeat(30));

        contact.setFirstName("Alex");
        contact.setLastName("Morgan");
        contact.setPhoneNumber("5555550101");
        contact.setAddress("200 Oak Avenue");

        assertAll(
                () -> assertEquals("C123456789", contact.getId()),
                () -> assertEquals("Alex", contact.getFirstName()),
                () -> assertEquals("Morgan", contact.getLastName()),
                () -> assertEquals("5555550101", contact.getPhoneNumber()),
                () -> assertEquals("200 Oak Avenue", contact.getAddress()));
    }

    @Test
    void rejectsInvalidConstructorValues() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact(null, "Alex", "Morgan", "5555550100", "Main")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("", "Alex", "Morgan", "5555550100", "Main")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("12345678901", "Alex", "Morgan", "5555550100", "Main")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("C-1", "", "Morgan", "5555550100", "Main")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("C-1", "Alex", "x".repeat(11), "5555550100", "Main")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("C-1", "Alex", "Morgan", "555-555-0100", "Main")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("C-1", "Alex", "Morgan", "5555550100", "x".repeat(31))));
    }

    @Test
    void rejectsInvalidSetterValuesWithoutChangingState() {
        Contact contact = new Contact(
                "C-100",
                "Alex",
                "Morgan",
                "5555550100",
                "100 Main Street");

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> contact.setFirstName(null)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> contact.setLastName("x".repeat(11))),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> contact.setPhoneNumber("abcdefghij")),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> contact.setAddress("")),
                () -> assertEquals("Alex", contact.getFirstName()),
                () -> assertEquals("Morgan", contact.getLastName()),
                () -> assertEquals("5555550100", contact.getPhoneNumber()),
                () -> assertEquals("100 Main Street", contact.getAddress()));
    }
}
