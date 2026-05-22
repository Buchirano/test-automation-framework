package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class for the Summary Details screen within NexusCM Case Management.
 *
 * <p><b>Screen:</b> Case Establishment — Summary Details</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class SummaryPage extends LightningBasePageClass {

    public final String remarksInputElement = "(//*[@class='slds-textarea'])[1]";
    public final String transferToCemeterySelectCheckbox = "//*[@data-id='transferToCemeteryInput']//span[@class='slds-checkbox_faux']";
    public final String transferCaseConfirmationYesButton = getButtonXPathByDataId("yesButton");
    public final String saveButton = getButtonXPathByDataId("saveButtonEligibilityFlagEnabled");
    public final String caseNumberHyperlink = "//*[@data-id='caseId']";
    public final String transferToDropdown = "//*[@data-id='transferCaseTo']/descendant::button[2]";
    public final String transferButton = getButtonXPathByDataId("transferButton");
    public final String okButton = getButtonXPathByDataId("okButton");

    public AutomatedObject getRemarksInput() { scrollIntoView(remarksInputElement); return getElementByXPath(remarksInputElement); }
    public AutomatedObject getTransferToCemeteryCheckbox() { scrollIntoView(transferToCemeterySelectCheckbox); return getElementByXPath(transferToCemeterySelectCheckbox); }
    public AutomatedObject getTransferCaseConfirmationYesButton() { scrollIntoView(transferCaseConfirmationYesButton); return getElementByXPath(transferCaseConfirmationYesButton); }
    public AutomatedObject getSaveButton() { scrollIntoView(saveButton); return getElementByXPath(saveButton); }
    public AutomatedObject getCaseNumberHyperlink() { scrollIntoView(caseNumberHyperlink); return getElementByXPath(caseNumberHyperlink); }
    public AutomatedObject getTransferToDropdown() { return getElementByXPath(transferToDropdown); }
    public AutomatedObject getTransferButton() { scrollIntoView(transferButton); return getElementByXPath(transferButton); }
    public AutomatedObject getOkButton() { scrollIntoView(okButton); return getElementByXPath(okButton); }
    public List<AutomatedObject> getTableRow(String searchText) { return getElementsByXPath("//td[.='" + searchText + "']/../td"); }
    public String getEligibilityReviewStatusXpath() { return "//*[@data-id='headerCaseDetailsEligibilityReviewStatus']//lightning-formatted-text"; }

    public void addRemarksGuidance(String remarksGuidance) { getRemarksInput().sendKeys(remarksGuidance); getSaveButton().click(); }

    public void selectTransferCaseTo(String transferOption) { selectDropdownOptionJSClick(getTransferToDropdown(), transferOption); jsClick(getTransferButton()); }

    public Boolean performCaseTransferOperations(String transferOption) {
        selectTransferCaseTo(transferOption);
        getTransferCaseConfirmationYesButton().click();
        waitForSalesforceLoad();
        return true;
    }

    public String transferButtonClick() {
        if (getTransferToDropdown().readText().equals("--None--")) selectDropdownOption(getTransferToDropdown(), "CorePlatform Transfer");
        getTransferButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(getWebElementByXPath(transferCaseConfirmationYesButton))).click();
        return "Transfer button clicked";
    }

    public String confirmTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(getWebElementByXPath(transferCaseConfirmationYesButton))).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getWebElementByXPath(transferButton))));
        waitForToastMessage("Success");
        return "Case Transferred";
    }

    public void clickCaseNumberHyperlink() { getCaseNumberHyperlink().click(); }

    public Boolean isErrorMessagePresent(String expectedErrorMessage1, String expectedErrorMessage2) {
        Search e1 = getSearch();
        e1.addCriteria("xpath", ".//div[contains(text(),'" + expectedErrorMessage1 + "')]");
        Search e2 = getSearch();
        e2.addCriteria("xpath", ".//div[contains(text(),'" + expectedErrorMessage2 + "')]");
        return isObjectPresent(e1) && isObjectPresent(e2);
    }
}
