package com.buchirano.automation.enums;

/**
 * Enum representing Liner Size dropdown field options on the Interment screen.
 */
public enum LinerSize {
    STANDARD("Standard"),
    OVERSIZE("Oversize"),
    INFANT("Infant"),
    OTHER("Other");

    private final String linerSize;

    LinerSize(String linerSize) {
        this.linerSize = linerSize;
    }

    public String getText() {
        return linerSize;
    }
}
