package com.buchirano.automation.enums;

/**
 * Enum representing Service Interval options used in the ResourcePortal
 * Availability and Schedule modals.
 */
public enum ServiceInterval {
    NONE("--None--"),
    FIFTEEN("15-minutes"),
    THIRTY("30-minutes"),
    FORTYFIVE("45-minutes"),
    HOUR("60-minutes");

    private final String serviceInterval;

    ServiceInterval(String serviceInterval) {
        this.serviceInterval = serviceInterval;
    }

    public String getText() {
        return serviceInterval;
    }
}
