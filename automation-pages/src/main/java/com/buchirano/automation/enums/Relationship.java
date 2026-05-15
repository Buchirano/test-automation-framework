package com.buchirano.automation.enums;

/**
 * Enum representing Relationship field options used in Relationship to Veteran
 * and Relationship to Claimant dropdowns.
 */
public enum Relationship {
    NONE("--None--"),
    ADULT_DEPENDENT_DAUGHTER("ADULT DEPENDENT DAUGHTER"),
    ADULT_DEPENDENT_SON("ADULT DEPENDENT SON"),
    ADULT_DEPENDENT_STEPDAUGHTER("ADULT DEPENDENT STEPDAUGHTER"),
    ADULT_DEPENDENT_STEPSON("ADULT DEPENDENT STEPSON"),
    CIVILIAN("CIVILIAN"),
    DAUGHTER_MINOR_CHILD("DAUGHTER (MINOR CHILD)"),
    FATHER("FATHER"),
    FOREIGN_VETERAN("FOREIGN VETERAN"),
    HUSBAND("HUSBAND"),
    MOTHER("MOTHER"),
    NOT_RELATED("NOT RELATED"),
    OTHER_RELATIVE("OTHER RELATIVE"),
    SON_MINOR_CHILD("SON (MINOR CHILD)"),
    STEPDAUGHTER_MINOR_CHILD("STEPDAUGHTER (MINOR CHILD)"),
    STEPSON_MINOR_CHILD("STEPSON (MINOR CHILD)"),
    SISTER("SISTER"),
    BROTHER("BROTHER"),
    UNKNOWN("UNKNOWN"),
    VETERAN_SELF("VETERAN (SELF)"),
    WIFE("WIFE");

    private final String relationship;

    Relationship(String relationship) {
        this.relationship = relationship;
    }

    public String getText() {
        return relationship;
    }
}
