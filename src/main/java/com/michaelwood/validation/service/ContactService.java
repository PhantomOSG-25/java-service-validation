package com.michaelwood.validation.service;

import com.michaelwood.validation.model.Contact;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/** In-memory contact service with unique identifiers. */
public final class ContactService {
    private final Map<String, Contact> contacts = new LinkedHashMap<>();

    public void add(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        if (contacts.putIfAbsent(contact.getId(), contact) != null) {
            throw new IllegalArgumentException("contact id must be unique");
        }
    }

    public Contact get(String id) {
        Contact contact = contacts.get(id);
        if (contact == null) {
            throw new NoSuchElementException("contact not found: " + id);
        }
        return contact;
    }

    public void delete(String id) {
        if (contacts.remove(id) == null) {
            throw new NoSuchElementException("contact not found: " + id);
        }
    }

    public List<Contact> getAll() {
        return List.copyOf(contacts.values());
    }
}
