package com.buchirano.automation.enums;

/**
 * Enum storing XPath expressions for elements frequently used to verify
 * page load state and field availability across the CaMEO application.
 */
public enum VerificationElement {

    START_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Start')]"),
    VETERAN_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Veteran')]"),
    MILITARY_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Military')]"),
    CLAIMANT_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Claimant')]"),
    ORGANIZATIONS_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Organizations')]"),
    PERSONAL_REPRESENTATIVE_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Personal Representative')]"),
    ADDITIONAL_CONTACTS_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Additional Contacts')]"),
    INTERMENT_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Interment')]"),
    SCHEDULING_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Scheduling')]"),
    SUMMARY_CHEVRON("//a[@aria-selected='true']//span[contains(text(),'Summary')]"),
    RECENT_REPORTS("//a[@title='Recent' and (@aria-selected='true' or @aria-current='page')]"),
    All_REPORTS("//a[@title='All Reports' and (@aria-selected='true' or @aria-current='page')]"),
    MBMS_CASE_DETAILS_TAB("//a[@title='MBMS Case Details' and @aria-current='page']"),
    CASE_ESTABLISHMENT_TAB("//a[@title='Case Establishment' and @aria-current='page']"),
    HOME_TAB("//a[@title='Home' and @aria-current='page']");

    private final String verificationElement;

    VerificationElement(String verificationElement) {
        this.verificationElement = verificationElement;
    }

    public String getXpathValue() {
        return verificationElement;
    }
}
