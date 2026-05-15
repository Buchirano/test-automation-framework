package com.buchirano.automation.enums;

/**
 * Enum representing Container Size dropdown field options on the Interment screen.
 */
public enum ContainerSize {
    NONE("--None--"),
    STANDARD("Standard Size Urn (10\"x10\"x8\")"),
    OTHER("Other");

    private final String containerSize;

    ContainerSize(String containerSize) {
        this.containerSize = containerSize;
    }

    public String getText() {
        return containerSize;
    }
}
