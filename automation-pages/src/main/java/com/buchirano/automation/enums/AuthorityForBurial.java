package com.buchirano.automation.enums;

/**
 * Enum representing Authority For Burial field options on the Interment screen.
 */
public enum AuthorityForBurial {
    NONE("--None--"),
    DISCHARGE_DOCUMENT("Discharge Document"),
    SHARE("Share"),
    RETIREMENT_ORDER("Retirement Order"),
    NPRC_VERIFIED("NPRC Verified"),
    PENDING("Pending"),
    OTHER("Other"),
    PREVIOUS_INTERMENT("Previous Interment");

    private final String authorityForBurial;

    AuthorityForBurial(String authorityForBurial) {
        this.authorityForBurial = authorityForBurial;
    }

    public String getText() {
        return authorityForBurial;
    }
}
