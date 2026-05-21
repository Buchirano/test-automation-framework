package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class for the Organization Search modal within NexusCM Case Management.
 *
 * <p><b>Modal:</b> Organization Search</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class OrganizationsSearchModal extends ModalPageClass {

    public AutomatedObject getOrganizationTypeDropdownField() { return getElementByXPath("//*[@data-id='orgModalType']//div/button"); }
    public AutomatedObject getOrganizationIdField() { return getInputByDataId("orgModalId"); }
    public AutomatedObject getOrganizationNameField() { return getInputByDataId("orgModalName"); }
    public List<AutomatedObject> getOrganizationPhoneResults() { return getElementsByXPath("//*[@data-label='Phone Number']//lightning-base-formatted-text"); }

    public Boolean verifySearchResultPresent(String expectedResult) { return getElementByXPath("//td[@data-cell-value='" + expectedResult + "']").isDisplayed(); }

    public String verifyAllResultsOfExpectedType(String expectedOrganizationType) {
        List<AutomatedObject> results = getElementsByXPath("//tr[@data-row-number]//td[@data-label='Type' and @data-cell-value]");
        long unexpected = results.stream().filter(r -> !r.getPropertyValue("data-cell-value").equalsIgnoreCase(expectedOrganizationType)).count();
        return unexpected > 0 ? unexpected + " results did not match the expected Organization Type: " + expectedOrganizationType + "." : "true";
    }

    @Override
    public void selectFirstEnabledResult() {
        String path = "(//*[@name='selectRecord' and @aria-disabled='false'])[1]";
        waitForVisibility(path);
        scrollIntoView(path);
        getElementByXPath(path).click();
        waitForSalesforceLoad();
    }
}
