package com.buchirano.automation.pages.application.cameo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class for the Start screen of NexusCM Case Management.
 *
 * <p><b>Screen:</b> Case Establishment — Start</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class StartPage extends LightningBasePageClass {

    public String cemeteryDropdownElementSelector = "//*[@data-id='cemeterySelectLookUp']//input";
    public String desiredCemeteryDropdown = "//*[@title='Desired Cemetery']//input";
    public String cemeteryFirstSubsequentElementSelector = "//*[@data-id='firstOrSubsequent']";
    public String cemeteryFirstValueElementSelector = "//*[@title='First']";
    public String cemeteryRegulationsButton = "//*/text()[normalize-space(.)='Cemetery Regulations']/parent::*";
    public String cemeterySpecialGuidanceButton = "//*[@data-id='specialGuidance']//button";
    public String createNewCaseButton = "//button[@title='Create a New Case']";
    public String caseTypeField = "//button[@name='MBMS_Case_Type__c']";
    public String receivedBy = "//button[@name='ReceivedBy__c']";
    public String origin = "//button[@name='MBMS_Origin__c']";
    public String receivedDate = "//*[@data-id='receivedDate']//input";
    public String originatingCmpPacketID = "//*[@data-id='originatingCMPPacketIdShow']//input";
    public String appTrackingNumber = "//*[@data-id='vagovTrackingNumberShow']//input";
    public String isApplicationSignedCheckbox = "//input[@name='MBMS_Is_Application_Signed__c']";
    public String veteranID = "//*[@data-id='cemeteryPageModalVeteranId']//input";
    public String searchModalButton = "//*[@title='Search']";

    private final DecedentSearchModalPage decedentSearch = new DecedentSearchModalPage();
    private final ModalPageClass modal = new ModalPageClass();
    private final String salesforceBossIcon = "SALESFORCE & CORE";

    private String cemeteryOption(String cemeteryName) { return "//lightning-base-combobox-formatted-text[@title = '" + cemeteryName + "']"; }

    public AutomatedObject getCemeteryDropdownElementSelector() { scrollIntoView(cemeteryDropdownElementSelector); return getElementByXPath(cemeteryDropdownElementSelector); }
    public AutomatedObject getDesiredCemeteryDropdown() { scrollIntoView(desiredCemeteryDropdown); return getElementByXPath(desiredCemeteryDropdown); }
    public AutomatedObject getCemeteryFirstSubsequentElementSelector() { scrollIntoView(cemeteryFirstSubsequentElementSelector); return getElementByXPath(cemeteryFirstSubsequentElementSelector); }
    public AutomatedObject getCemeteryRegulationsButton() { scrollIntoView(cemeteryRegulationsButton); return getElementByXPath(cemeteryRegulationsButton); }
    public AutomatedObject getCemeterySpecialGuidanceButton() { scrollIntoView(cemeterySpecialGuidanceButton); return getElementByXPath(cemeterySpecialGuidanceButton); }
    public AutomatedObject getCreateNewCaseButton() { scrollIntoView(createNewCaseButton); return getElementByXPath(createNewCaseButton); }
    public AutomatedObject getSearchDecedentButton() { scrollIntoView("//*[@data-id='searchDecedent']"); return getElementBy("data-id", "searchDecedent"); }
    public AutomatedObject getSearchPreviousDecedentButton() { scrollIntoView(getElementXPathByTitle("Search Previous Decedent/Claimant Cases")); return getElementBy("title", "Search Previous Decedent/Claimant Cases"); }
    public AutomatedObject getSearchCasesButton() { scrollIntoView("//*[@data-id='searchCase']"); return getElementBy("data-id", "searchCase"); }
    public AutomatedObject getCaseTypeField() { return getElementByXPath(caseTypeField); }
    public AutomatedObject getReceivedBy() { return getElementByXPath(receivedBy); }
    public AutomatedObject getOrigin() { return getElementByXPath(origin); }
    public AutomatedObject getReceivedDate() { return getElementByXPath(receivedDate); }
    public AutomatedObject getOriginatingCmpPacketID() { return getElementByXPath(originatingCmpPacketID); }
    public AutomatedObject getAppTrackingNumber() { return getElementByXPath(appTrackingNumber); }
    public AutomatedObject getIsApplicationSignedCheckbox() { return getElementByXPath(isApplicationSignedCheckbox); }
    public AutomatedObject getVeteranID() { return getElementByXPath(veteranID); }
    public AutomatedObject getSearchModalButton() { scrollIntoView("//*[@title='Search']"); return getElementBy("title", "Search"); }

    public void selectCemetery(String cemetery) {
        pageScrollToBottom();
        getCemeteryDropdownElementSelector().click();
        getCemeteryDropdownElementSelector().sendKeys(cemetery);
        waitForSalesforceLoad();
        String optionXpath = cemeteryOption(cemetery);
        List<AutomatedObject> options = new ArrayList<>();
        options.add(getElementByXPath(optionXpath));
        if (!isElementPresent(options).equalsIgnoreCase("true")) getCemeteryDropdownElementSelector().click();
        getElementByXPath(optionXpath).click();
        waitForSalesforceLoad();
    }

    public void selectDesiredCemetery(String cemetery) { getDesiredCemeteryDropdown().sendKeys(cemetery); waitForSalesforceLoad(); getElementByXPath(cemeteryOption(cemetery)).click(); }

    public void clickSpecialGuidance(String cemetery) { waitForNewWindowAfterClick(getCemeterySpecialGuidanceButton(), true); }

    public void clickCemeteryRegulations() {
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//button[@title='Cemetery Regulations']"), "aria-disabled", "false"));
        getCemeteryRegulationsButton().click();
    }

    public void selectIntermentType(String intermentType) {
        pageScrollDown();
        getCemeteryFirstSubsequentElementSelector().click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        Search search = getSearch();
        search.addCriteria("tag", "span");
        search.addCriteria("title", intermentType);
        getObject(search).click();
        waitForSalesforceLoad();
    }

    public void selectCaseType(String caseType) { getCaseTypeField().click(); waitForSalesforceLoad(); Search s = getSearch(); s.addCriteria("tag", "span"); s.addCriteria("title", caseType); getObject(s).click(); waitForSalesforceLoad(); }
    public void selectReceivedBy(String receivedByOption) { getReceivedBy().click(); waitForSalesforceLoad(); Search s = getSearch(); s.addCriteria("tag", "span"); s.addCriteria("title", receivedByOption); getObject(s).click(); waitForSalesforceLoad(); }
    public void selectOrigin(String originOption) { getOrigin().click(); waitForSalesforceLoad(); Search s = getSearch(); s.addCriteria("tag", "span"); s.addCriteria("title", originOption); getObject(s).click(); waitForSalesforceLoad(); }
    public void inputReceivedDate(String date) { waitForSalesforceLoad(); getReceivedDate().clear(); getReceivedDate().sendKeys(date); waitForSalesforceLoad(); }
    public String getYesterdaysDate() { return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("MMM dd, yyyy")); }
    public void inputOriginatingPacketID(String id) { waitForSalesforceLoad(); getOriginatingCmpPacketID().clear(); getOriginatingCmpPacketID().sendKeys(id); waitForSalesforceLoad(); }
    public void inputTrackingNumber(String trackingNumber) { waitForSalesforceLoad(); getAppTrackingNumber().clear(); getAppTrackingNumber().sendKeys(trackingNumber); waitForSalesforceLoad(); }
    public void clickIsApplicationSigned() { waitForSalesforceLoad(); getIsApplicationSignedCheckbox().click(); waitForSalesforceLoad(); }
    public void openDecedentSearchModal() { getSearchPreviousDecedentButton().click(); waitForSalesforceLoad(); }
    public void clickModalSearchButton() { decedentSearch.getSearchButton().click(); waitForSalesforceLoad(); }
    public void clickSelectPreviousDecedent() { decedentSearch.getSelectPreviousDecedentButton().click(); waitForSalesforceLoad(); }
    public void clickSearchCasesButton() { getSearchCasesButton().click(); waitForSalesforceLoad(); }
    public void clickCreateNewCaseButton() { waitForSalesforceLoad(); getCreateNewCaseButton().click(); waitForSalesforceLoad(); }
    public void closeModal() { modal.getCloseModalButton().click(); }

    public void enterDecedentSearchValues(String firstName, String middleName, String lastName,
            String ssn, String birthDate, String deathDate, String serviceNumber, String id) {
        decedentSearch.getFirstNameField().sendKeys(firstName);
        decedentSearch.getMiddleNameField().sendKeys(middleName);
        decedentSearch.getLastNameField().sendKeys(lastName);
        decedentSearch.getSsnField().sendKeys(ssn);
        decedentSearch.getBirthdateField().sendKeys(birthDate);
        decedentSearch.getDeathdateField().sendKeys(deathDate);
        decedentSearch.getServiceNumberField().sendKeys(serviceNumber);
        decedentSearch.getDecedentIdField().sendKeys(id);
    }

    public void clearDecedentSearchFields() {
        decedentSearch.getFirstNameField().clear(); decedentSearch.getMiddleNameField().clear();
        decedentSearch.getLastNameField().clear(); decedentSearch.getSsnField().clear();
        decedentSearch.getBirthdateField().clear(); decedentSearch.getDeathdateField().clear();
        decedentSearch.getServiceNumberField().clear(); decedentSearch.getDecedentIdField().clear();
    }

    public void selectFirstEnabledResult() { getFirstEnabledElement(modal.getSelectContactButtons()).click(); }

    public void searchDecedentCaseAndSelect(String firstName, String middleName, String lastName,
            String ssn, String birthDate, String deathDate, String serviceNumber, String id) {
        openDecedentSearchModal();
        enterDecedentSearchValues(firstName, middleName, lastName, ssn, birthDate, deathDate, serviceNumber, id);
        clickModalSearchButton();
        waitForVisibility(decedentSearch.previousDecedentResultsTab);
        decedentSearch.getPreviousDecedentResultsTab().click();
        waitForVisibility(modal.customSelectButton);
        selectFirstEnabledResult();
        clickSelectPreviousDecedent();
    }

    public void verifyCaseMigration(String decedentID) {
        openDecedentSearchModal();
        decedentSearch.getDecedentIdField().sendKeys(decedentID);
        clickModalSearchButton();
        getElementByXPath(decedentSearch.decedentResultsTab).isEnabled();
        decedentSearch.getPreviousDecedentResultsTab().click();
        waitForVisibility(modal.customSelectButton);
        waitForVisibility(decedentSearch.customBossIcon);
        String actualText = getElementByXPath(decedentSearch.customBossIcon).toString();
        assertEquals(salesforceBossIcon, actualText);
    }

    public void inputVeteranID(String id) { waitForSalesforceLoad(); getVeteranID().clear(); getVeteranID().sendKeys(id); waitForSalesforceLoad(); }
    public void clickSearchModalButton() { getSearchModalButton().click(); waitForSalesforceLoad(); }

    public List<AutomatedObject> listOfCreateNewCaseFields() {
        return List.of(getCaseTypeField(), getReceivedBy(), getOrigin(), getReceivedDate(),
                getOriginatingCmpPacketID(), getAppTrackingNumber(), getCemeteryDropdownElementSelector(),
                getDesiredCemeteryDropdown(), getCemeteryFirstSubsequentElementSelector());
    }

    public List<AutomatedObject> listOfPreNeedEnabledFields() {
        return List.of(getCaseTypeField(), getOrigin(), getReceivedDate(),
                getOriginatingCmpPacketID(), getAppTrackingNumber(), getDesiredCemeteryDropdown());
    }
}
