package com.buchirano.automation.enums;

/**
 * Enum representing Impact type options used in the Availability and Schedule modals.
 */
public enum Impact {
    NONE("--None--"),
    CAPACITY("Capacity"),
    CLOSED("Closed");

    private final String impact;

    Impact(String impact) {
        this.impact = impact;
    }

    public String getText() {
        return impact;
    }
}
