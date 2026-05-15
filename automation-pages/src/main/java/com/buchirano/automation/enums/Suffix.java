package com.buchirano.automation.enums;

/**
 * Enum representing Suffix field options used across name fields in
 * Case Establishment screens.
 */
public enum Suffix {
    NONE("--None--"),
    JUNIOR("JUNIOR"),
    SENIOR("SENIOR"),
    SECOND("SECOND"),
    THIRD("THIRD"),
    FOURTH("FOURTH"),
    FIFTH("FIFTH");

    private final String suffix;

    Suffix(String suffix) {
        this.suffix = suffix;
    }

    public String getText() {
        return suffix;
    }
}
