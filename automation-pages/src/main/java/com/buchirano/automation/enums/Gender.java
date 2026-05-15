package com.buchirano.automation.enums;

/**
 * Enum representing Gender dropdown field options used across Case Establishment screens.
 */
public enum Gender {
    NONE("--None--"),
    MALE("Male"),
    FEMALE("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getText() {
        return gender;
    }
}
