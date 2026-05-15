package com.buchirano.automation.enums;

/**
 * Enum representing Honors options used in availability and scheduling screens.
 */
public enum Honors {
    HONORS("Honors"),
    NON_HONORS("Non-Honors");

    private final String honors;

    Honors(String honors) {
        this.honors = honors;
    }

    public String getText() {
        return honors;
    }
}
