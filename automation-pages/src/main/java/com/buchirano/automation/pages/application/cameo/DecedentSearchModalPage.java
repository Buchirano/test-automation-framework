package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating mapped element paths and interaction methods
 * for the Decedent Search modal within NexusCM Case Management.
 *
 * <p><b>Modal:</b> Decedent / Previous Decedent Search</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class DecedentSearchModalPage extends BasePageClass {

    public String decedentGraveSection = "//*[@data-id='Section']//lightning-formatted-text";
    public String decedentGraveRow = "//*[@data-id='Row']//lightning-formatted-text";
    public String decedentGraveSite = "//*[@data-id='Site']//lightning-formatted-text";
    public String reservationName = "//*[@data-id='Name']//lightning-formatted-text";
    public String reservationCemeteryName = "//*[@data-id='Cemetery Name']//lightning-formatted-text";
    public String reservationSection = "//*[@data-id='Section']//lightning-formatted-text";
    public String reservationRow = "//*[@data-id='Row']//lightning-formatted-text";
    public String reservationSite = "//*[@data-id='Site']//lightning-formatted-text";
    public String reservationStatus = "//*[@data-id='Status']//lightning-formatted-text";
    public String previousDecedentSearchResultContainer = "//c-cameo_search-result-details";
    public String customBossIcon = "//lightning-badge[.='SALESFORCE & BOSS']";
    public String decedentResultsTab = "//*[@data-label='Decedent Results']";
    public String previousDecedentResultsTab = "(//*[@data-label='Previous Decedent/Claimant Results'])[1]";
    public String decedentClaimantsButton = "//*[@data-key='chevronright']";
    public String convertButton = "//*[@data-id='convertButton']";

    public AutomatedObject getSearchButton() { return getElementBy("data-id", "searchButton"); }
    public AutomatedObject getSelectPreviousDecedentButton() { return getElementBy("data-id", "SELECT: Previous Decedent/Claimant"); }
    public AutomatedObject getFirstNameField() { return getElementBy("data-id", "cemeteryPageModalFirstName"); }
    public AutomatedObject getMiddleNameField() { return getElementBy("data-id", "cemeteryPageModalMiddleName"); }
    public AutomatedObject getLastNameField() { return getElementBy("data-id", "cemeteryPageModalLastName"); }
    public AutomatedObject getSsnField() { return getElementBy("data-id", "cemeteryPageModalSSN"); }
    public AutomatedObject getBirthdateField() { return getElementBy("data-id", "cemeteryPageModalDOB"); }
    public AutomatedObject getDeathdateField() { return getElementBy("data-id", "cemeteryPageModalDOD"); }
    public AutomatedObject getServiceNumberField() { return getElementBy("data-id", "cemeteryPageModalServiceNumber"); }
    public AutomatedObject getDecedentIdField() { return getElementBy("data-id", "cemeteryPageModalDecedentId"); }
    public AutomatedObject getDecedentGraveSectionField() { return getElementByXPath(decedentGraveSection); }
    public AutomatedObject getDecedentGraveSiteField() { return getElementByXPath(decedentGraveSite); }
    public AutomatedObject getDecedentGraveRowField() { return getElementByXPath(decedentGraveRow); }
    public AutomatedObject getReservationName() { return getElementByXPath(reservationName); }
    public AutomatedObject getReservationCemeteryName() { return getElementsByXPath(reservationCemeteryName).get(0); }
    public AutomatedObject getReservationSection() { return getElementsByXPath(reservationSection).get(1); }
    public AutomatedObject getReservationRow() { return getElementsByXPath(reservationRow).get(1); }
    public AutomatedObject getReservationSite() { return getElementsByXPath(reservationSite).get(1); }
    public AutomatedObject getReservationStatus() { return getElementByXPath(reservationStatus); }
    public AutomatedObject getPreviousDecedentName() { return getElementBy("data-id", "firstIntermentWith"); }
    public List<AutomatedObject> getCustomBossIcon() { return getElementsByXPath(customBossIcon); }

    public AutomatedObject getPreviousDecedentResultsTab() {
        scrollIntoView(previousDecedentResultsTab);
        return getElementByXPath(previousDecedentResultsTab);
    }

    public AutomatedObject getClaimantResultsTab() {
        scrollIntoView("//*[@title='Claimant Results']");
        return getElementBy("title", "Claimant Results");
    }

    public AutomatedObject getDecedentClaimantAccordion() { return getElementByXPath(decedentClaimantsButton); }
    public AutomatedObject getConvertButton() { return getElementByXPath(convertButton); }

    private AutomatedObject getYesButton() {
        scrollIntoView("//*[@data-id='yesButton']");
        return getElementByXPath("//*[@data-id='yesButton']");
    }

    public void clickPreviousDecedentResultsTab() { getPreviousDecedentResultsTab().click(); waitForSalesforceLoad(); }
    public void clickClaimantResultsTab() { getClaimantResultsTab().click(); waitForSalesforceLoad(); }
    public void clickDecedentClaimantAccordian() { pageScrollDown(); getDecedentClaimantAccordion().click(); waitForSalesforceLoad(); }
    public void clickConvertButton() { pageScrollDown(); getConvertButton().click(); waitForSalesforceLoad(); }
    public void clickYesButton() { getYesButton().click(); waitForSalesforceLoad(); }
}
