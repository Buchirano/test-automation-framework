package com.buchirano.automation.enums;

/**
 * Enum representing Liner Type dropdown field options on the Interment screen.
 */
public enum LinerType {
    GOVERNMENT_LINER("Government Liner"),
    NO_LINER_REQUESTED("No Liner Requested"),
    PRIVATE_VAULT("Private Vault"),
    OTHER("Other");

    private final String linerType;

    LinerType(String linerType) {
        this.linerType = linerType;
    }

    public String getText() {
        return linerType;
    }
}
