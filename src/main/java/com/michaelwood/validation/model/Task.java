package com.michaelwood.validation.model;

/** A task record with an immutable identifier and validated content. */
public final class Task {
    private final String id;
    private String name;
    private String description;

    public Task(String id, String name, String description) {
        this.id = requireText(id, 10, "id");
        setName(name);
        setDescription(description);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireText(name, 20, "name");
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
