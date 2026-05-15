package com.buchirano.automation.enums;

/**
 * Enum representing Additional Contact Type field options on the Additional Contacts Details screen.
 */
public enum ContactType {
    PERSONS_ELIGIBLE("Persons Eligible"),
    PERSONS_BURIED("Persons Buried");

    private final String contactType;

    ContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getText() {
        return contactType;
    }
}
