package com.buchirano.automation.enums;

/**
 * Enum representing Home of Record dropdown field options on the Claimant Details screen.
 */
public enum HomeOfRecord {
    NONE("--None--"),
    YES("Yes"),
    NO("No");

    private final String homeOfRecord;

    HomeOfRecord(String homeOfRecord) {
        this.homeOfRecord = homeOfRecord;
    }

    public String getText() {
        return homeOfRecord;
    }
}
