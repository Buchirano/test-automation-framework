package com.buchirano.automation.enums;

/**
 * Enum representing Timeslot Type options used in the ResourcePortal Schedule modal.
 */
public enum TimeslotType {
    SINGLE_INSTANCE("Single Instance"),
    RECURRING("Recurring");

    private final String timeslotType;

    TimeslotType(String timeslotType) {
        this.timeslotType = timeslotType;
    }

    public String getText() {
        return timeslotType;
    }
}
