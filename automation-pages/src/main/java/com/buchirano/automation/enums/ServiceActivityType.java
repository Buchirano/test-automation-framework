package com.buchirano.automation.enums;

/**
 * Enum representing Service Activity Type options used across the PortalRM
 * Availability modal and the CaMEO Interment screen.
 */
public enum ServiceActivityType {
    NONE("--None--"),
    INTERMENT_FIRST_CASKET("Interment First Casket"),
    INTERMENT_FIRST_CREMAINS("Interment First Cremains"),
    INTERMENT_SUBSEQUENT_CASKET("Interment Subsequent Casket"),
    INTERMENT_SUBSEQUENT_CREMAINS("Interment Subsequent Cremains"),
    MEMORIAL_SERVICE_ONLY("Memorial Service Only"),
    DISINTERMENT("Disinterment"),
    REINTERMENT("Reinterment"),
    DIRECT_INTERMENT("Direct Interment");

    private final String serviceActivityType;

    ServiceActivityType(String serviceActivityType) {
        this.serviceActivityType = serviceActivityType;
    }

    public String getText() {
        return serviceActivityType;
    }
}
