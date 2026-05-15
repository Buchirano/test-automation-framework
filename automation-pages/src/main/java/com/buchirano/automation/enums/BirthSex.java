package com.buchirano.automation.enums;

/**
 * Enum representing Birth Sex field options used across Case Establishment screens.
 */
public enum BirthSex {
    NONE("--None--"),
    MALE("Male"),
    FEMALE("Female"),
    DECLINED_TO_ANSWER("Declined to Answer");

    private final String birthSex;

    BirthSex(String birthSex) {
        this.birthSex = birthSex;
    }

    public String getText() {
        return birthSex;
    }
}
