package com.buchirano.automation.enums;

/**
 * Enum representing the edit dropdown options on the Availability screen.
 */
public enum AvailabilityEditDropdown {
    EDIT("Edit"),
    DELETE("Delete");

    private final String availabilityEditDropdown;

    AvailabilityEditDropdown(String availabilityEditDropdown) {
        this.availabilityEditDropdown = availabilityEditDropdown;
    }

    public String getText() {
        return availabilityEditDropdown;
    }
}
