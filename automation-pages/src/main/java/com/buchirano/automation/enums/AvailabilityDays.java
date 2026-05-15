package com.buchirano.automation.enums;

/**
 * Enum representing days of the week for availability scheduling.
 */
public enum AvailabilityDays {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String availabilityDays;

    AvailabilityDays(String availabilityDays) {
        this.availabilityDays = availabilityDays;
    }

    public String getText() {
        return availabilityDays;
    }
}
