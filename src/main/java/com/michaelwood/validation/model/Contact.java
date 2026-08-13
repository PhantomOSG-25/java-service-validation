package com.michaelwood.validation.model;

/** A contact record with requirement-based field validation. */
public final class Contact {
    private final String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Contact(
            String id,
            String firstName,
            String lastName,
            String phoneNumber,
            String address) {
        this.id = requireText(id, 10, "id");
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = requireText(firstName, 10, "firstName");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = requireText(lastName, 10, "lastName");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("phoneNumber must contain exactly 10 digits");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = requireText(address, 30, "address");
    }

    private static String requireText(String value, int maxLength, String field) {
        if (value == null || value.isBlank() || value.length() > maxLength) {
            throw new IllegalArgumentException(
                    field + " must contain 1 to " + maxLength + " characters");
        }
        return value;
    }
}
