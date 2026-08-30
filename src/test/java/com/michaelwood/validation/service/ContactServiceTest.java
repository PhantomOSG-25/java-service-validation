package com.michaelwood.validation.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.michaelwood.validation.model.Contact;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private static Contact contact(String id) {
        return new Contact(id, "Alex", "Morgan", "5555550100", "100 Main Street");
    }

    @Test
    void supportsCreateReadUpdateDeleteWorkflow() {
        ContactService service = new ContactService();
        service.add(contact("C-100"));

        service.updateFirstName("C-100", "Taylor");
        service.updateLastName("C-100", "Jordan");
        service.updatePhoneNumber("C-100", "5555550101");
        service.updateAddress("C-100", "200 Oak Avenue");

        Contact updated = service.get("C-100");
        assertAll(
                () -> assertEquals("Taylor", updated.getFirstName()),
                () -> assertEquals("Jordan", updated.getLastName()),
                () -> assertEquals("5555550101", updated.getPhoneNumber()),
                () -> assertEquals("200 Oak Avenue", updated.getAddress()),
                () -> assertEquals(1, service.getAll().size()));

        service.delete("C-100");

        assertAll(
                () -> assertEquals(0, service.getAll().size()),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.get("C-100")));
    }

    @Test
    void rejectsNullDuplicatesAndMissingIdentifiers() {
        ContactService service = new ContactService();
        service.add(contact("C-100"));

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.add(null)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.add(contact("C-100"))),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.updateFirstName("missing", "Taylor")),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> service.delete("missing")),
                () -> assertThrows(
                        UnsupportedOperationException.class,
                        () -> service.getAll().clear()));
    }

    @Test
    void invalidUpdateDoesNotReplaceExistingValue() {
        ContactService service = new ContactService();
        service.add(contact("C-100"));

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> service.updatePhoneNumber("C-100", "invalid")),
                () -> assertEquals(
                        "5555550100",
                        service.get("C-100").getPhoneNumber()));
    }
}
