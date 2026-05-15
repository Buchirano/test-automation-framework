package com.buchirano.automation.enums;

/**
 * Enum representing recurrence Occurrence type options used in the Schedule modal.
 */
public enum Occurrence {
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    private final String occurrence;

    Occurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getText() {
        return occurrence;
    }
}
