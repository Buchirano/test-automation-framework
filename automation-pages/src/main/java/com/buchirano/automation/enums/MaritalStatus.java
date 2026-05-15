package com.buchirano.automation.enums;

/**
 * Enum representing Marital Status dropdown field options used across
 * Claimant and Veteran Details screens.
 */
public enum MaritalStatus {
    NONE("--None--"),
    MARRIED("Married"),
    DIVORCED("Divorced"),
    NEVER_MARRIED("Never Married"),
    UNKNOWN("Unknown"),
    WIDOWED("Widowed"),
    OTHER("Other");

    private final String maritalStatus;

    MaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getText() {
        return maritalStatus;
    }
}
