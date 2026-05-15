package com.buchirano.automation.enums;

/**
 * Enum representing general Yes/No/Unknown dropdown field responses
 * used across multiple Case Establishment screens.
 */
public enum GeneralResponse {
    NONE("--None--"),
    YES("Yes"),
    NO("No"),
    UNKNOWN("Unknown");

    private final String generalResponse;

    GeneralResponse(String generalResponse) {
        this.generalResponse = generalResponse;
    }

    public String getText() {
        return generalResponse;
    }
}
