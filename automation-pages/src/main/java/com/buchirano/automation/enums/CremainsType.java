package com.buchirano.automation.enums;

/**
 * Enum representing Cremains Type field options on the Interment screen.
 */
public enum CremainsType {
    NONE("--None--"),
    IN_GROUND("In-Ground"),
    COLUMBARIUM("Columbarium"),
    SCATTERED("Scattered"),
    OSSUARY("Ossuary"),
    OTHER("Other");

    private final String cremainsType;

    CremainsType(String cremainsType) {
        this.cremainsType = cremainsType;
    }

    public String getText() {
        return cremainsType;
    }
}
