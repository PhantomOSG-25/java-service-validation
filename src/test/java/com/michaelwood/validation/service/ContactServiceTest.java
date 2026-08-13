package com.michaelwood.validation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.michaelwood.validation.model.Contact;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private static Contact contact(String id) {
        return new Contact(id, "Michael", "Wood", "6155550100", "100 Main Street");
    }

    @Test
    void addsRetrievesAndDeletesContact() {
        ContactService service = new ContactService();
        service.add(contact("C-100"));

        assertEquals("C-100", service.get("C-100").getId());
        assertEquals(1, service.getAll().size());

        service.delete("C-100");
        assertEquals(0, service.getAll().size());
    }

    @Test
    void rejectsDuplicateAndMissingIdentifiers() {
        ContactService service = new ContactService();
        service.add(contact("C-100"));

        assertThrows(IllegalArgumentException.class, () -> service.add(contact("C-100")));
        assertThrows(NoSuchElementException.class, () -> service.get("missing"));
    }
}
