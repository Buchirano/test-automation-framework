package com.buchirano.automation.enums;

/**
 * Enum representing Remains Type options used across Interment,
 * Availability, and Schedule screens.
 */
public enum RemainsType {
    CASKET("Casket"),
    CASKET_OVERSIZED("Casket Oversized"),
    CREMAINS("Cremains"),
    NO_REMAINS("No Remains");

    private final String remainsType;

    RemainsType(String remainsType) {
        this.remainsType = remainsType;
    }

    public String getText() {
        return remainsType;
    }
}
