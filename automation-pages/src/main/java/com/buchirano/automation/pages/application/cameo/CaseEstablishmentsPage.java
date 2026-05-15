package com.buchirano.automation.pages.application.cameo;

import org.openqa.selenium.JavascriptExecutor;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;
import com.buchirano.automation.core.WebDriverSingleton;

/**
 * Page class encapsulating mapped element paths and interaction methods
 * for the Case Establishments list/search screen within CaMEO.
 *
 * <p>This class handles cemetery selection, first/subsequent interment type
 * selection, veteran details entry, and navigation to the next screen.</p>
 *
 * <p><b>Screen:</b> Case Establishments</p>
 * <p><b>Layer:</b> Page Object (Application — CaMEO)</p>
 */
public class CaseEstablishmentsPage extends BasePageClass {

    // ========================== Element Accessors ==========================

    private AutomatedObject getSearchCasesButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("text", "Search Cases");
        return getObject(search);
    }

    private AutomatedObject getSelectedCemeteryTextField() {
        Search search = getSearch();
        search.addCriteria("xpath",
                "//lightning-input-field[@data-id='cemeterySelectLookUp']/*//div/input[@Placeholder='Search Accounts...']");
        return getObject(search);
    }

    private AutomatedObject getFirstOrSubsequentButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("id", "combobox-button-277");
        return getObject(search);
    }

    private AutomatedObject getNextButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("title", "Go to next screen");
        return getObject(search);
    }

    private AutomatedObject getFirstNameTextField() {
        Search search = getSearch();
        search.addCriteria("tag", "input");
        search.addCriteria("name", "FirstName");
        return getObject(search);
    }

    private AutomatedObject getLastNameTextField() {
        Search search = getSearch();
        search.addCriteria("tag", "input");
        search.addCriteria("name", "LastName");
        return getObject(search);
    }

    private AutomatedObject getMilitaryStatusButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("name", "MBMS_Military_Status__c");
        return getObject(search);
    }

    private AutomatedObject getServiceEligibilityButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("name", "MBMS_Service_Eligibility_Indicator__c");
        return getObject(search);
    }

    private AutomatedObject getAddNewMilitaryServiceInformationButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("text", "Add new Military Service Information");
        return getObject(search);
    }

    // ========================== Interaction Methods ==========================

    /** Clicks the Search Cases button and waits for the DOM to stabilize. */
    public void clickSearchCases() { getSearchCasesButton().click(); waitForSalesforceLoad(); }

    /**
     * Sets the Selected Cemetery field by typing the cemetery text and selecting the matching result.
     *
     * @param selectedCemeterytext  text to type into the lookup field
     * @param selectedCemeterytitle title attribute of the dropdown option to select
     */
    public void setSelectedCemetery(String selectedCemeterytext, String selectedCemeterytitle) {
        pageScrollDown();
        getSelectedCemeteryTextField().sendKeys(selectedCemeterytext);
        getSelectedCemeteryTextField().click();
        Search search = getSearch();
        search.addCriteria("tag", "lightning-base-combobox-formatted-text");
        search.addCriteria("title", selectedCemeterytitle);
        getObject(search).click();
    }

    /** @return the current value of the Selected Cemetery field */
    public String readSelectedCemetery() { return getSelectedCemeteryTextField().readText(); }

    /** Clicks the First or Subsequent dropdown and waits for the DOM to stabilize. */
    public void clickFirstOrSubsequent() { getFirstOrSubsequentButton().click(); waitForSalesforceLoad(); }

    /**
     * Selects the "First" option from the First or Subsequent dropdown.
     *
     * @param first the value to select (typically "First")
     */
    public void selectFirst(String first) {
        pageScrollDown();
        getFirstOrSubsequentButton().click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        Search search = getSearch();
        search.addCriteria("tag", "span");
        search.addCriteria("title", "First");
        getObject(search).click();
    }

    /** @return the current value of the First or Subsequent dropdown */
    public String readFirst() { return getFirstOrSubsequentButton().readText(); }

    /**
     * Overrides the default scroll to use JavaScript scroll for this specific screen.
     */
    @Override
    public void pageScrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverSingleton.getWebDriver();
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        js.executeScript("window.scrollBy(0, 250)");
    }

    /** Clicks the Next button and waits for the DOM to stabilize. */
    public void clickNext() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        getNextButton().click();
        waitForSalesforceLoad();
    }

    /**
     * Sets the First Name field value.
     *
     * @param firstName value to set
     */
    public void setFirstName(String firstName) { getFirstNameTextField().setValue(firstName); }

    /** @return current value of the First Name field */
    public String readFirstName() { return getFirstNameTextField().readText(); }

    /**
     * Sets the Last Name field value.
     *
     * @param lastName value to set
     */
    public void setLastName(String lastName) { getLastNameTextField().setValue(lastName); }

    /** @return current value of the Last Name field */
    public String readLastName() { return getLastNameTextField().readText(); }

    /** Clicks the Military Status button and waits for the DOM to stabilize. */
    public void clickMilitaryStatus() { getMilitaryStatusButton().click(); waitForSalesforceLoad(); }

    /** @return current value of the Military Status dropdown */
    public String readMilitaryStatus() { return getMilitaryStatusButton().readText(); }

    /** Clicks the Service Eligibility button and waits for the DOM to stabilize. */
    public void clickServiceEligibility() { getServiceEligibilityButton().click(); waitForSalesforceLoad(); }

    /** @return current value of the Service Eligibility dropdown */
    public String readServiceEligibility() { return getServiceEligibilityButton().readText(); }

    /**
     * Clicks the Add New Military Service Information button and waits for the DOM to stabilize.
     */
    public void clickAddNewMilitaryServiceInformation() {
        getAddNewMilitaryServiceInformationButton().click();
        waitForSalesforceLoad();
    }
}
