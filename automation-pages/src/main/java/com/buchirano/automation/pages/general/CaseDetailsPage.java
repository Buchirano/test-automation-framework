package com.buchirano.automation.pages.general;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * <b>Name:</b> CaseDetailsPage.java
 * <p>
 * <b>Description:</b> Page class for the Case Details screen. Contains mapped
 * element references and interaction methods for the Case Details dashboard,
 * including card navigation, field editing, dropdown selection, letter status
 * management, and case record verification.
 * <p>
 * Extends BasePageClass to inherit all common WebDriver interaction methods,
 * wait strategies, and scroll utilities.
 * <p>
 *
 * @author buchirano
 * @version 1.0
 */
public class CaseDetailsPage extends BasePageClass {

    // ─────────────────────────────────────────────
    //  Private Get Methods — Element References
    // ─────────────────────────────────────────────

    /**
     * Returns a reference to the List View dropdown on the Case Details page.
     *
     * @return WebElement reference to the Select a List View dropdown
     */
    private WebElement getSelectAListViewDropDown() {
        return getElementByXPath("//button[contains(@title,'Select a List View')]");
    }

    /**
     * Returns a reference to the Letter Sent dropdown field.
     *
     * @return WebElement reference to the Letter Sent dropdown
     */
    private WebElement getLetterSentDropDown() {
        return getElementFromName("Letter Sent");
    }

    /**
     * Returns a reference to the Letter Status dropdown field.
     *
     * @return WebElement reference to the Letter Status dropdown
     */
    private WebElement getLetterStatusDropDown() {
        return getElementFromName("Letter Status");
    }

    /**
     * Returns a reference to the inline edit pencil icon for a specified field.
     *
     * @param fieldName The visible field name label on screen
     * @return WebElement reference to the edit pencil icon
     */
    private WebElement getEditPencil(String fieldName) {
        return getElementByXPath(
                "//*[text()='" + fieldName + "']/ancestor::div//button[@title='Edit " + fieldName + "']");
    }

    /**
     * Returns a reference to the Letter Generated Date input field.
     * Only available after the edit pencil has been clicked.
     *
     * @return WebElement reference to the Letter Generated Date field
     */
    private WebElement getLetterGeneratedDateField() {
        return getElementFromName("Letter Generated Date");
    }

    /**
     * Returns a reference to the Eligibility Review Status dropdown button.
     *
     * @return WebElement reference to the Eligibility Review Status field
     */
    private WebElement getEligibilityReviewStatusField() {
        return getElementByXPath("//button[@aria-label = 'Eligibility Review Status']");
    }

    // ─────────────────────────────────────────────
    //  Public Get Methods — Cards & Navigation
    // ─────────────────────────────────────────────

    /**
     * Returns a reference to the Case History card tab.
     *
     * @return WebElement for the Case History card anchor
     */
    public WebElement getCaseHistoryCard() {
        return getElementByXPath("//li[@data-label = 'Case History']//a");
    }

    /**
     * Returns a reference to the Additional Case Details card tab.
     *
     * @return WebElement for the Additional Case Details card anchor
     */
    public WebElement getAdditionalCaseDetailsCard() {
        return getElementByXPath("//li[@data-label = 'Additional Case Details']//a");
    }

    /**
     * Returns a reference to the Additional Contacts card tab.
     *
     * @return WebElement for the Additional Contacts card anchor
     */
    public WebElement getAdditionalContactsCard() {
        return getElementByXPath("//li[@data-label = 'Additional Contacts']//a");
    }

    /**
     * Returns a reference to the Automated Case Data card tab.
     *
     * @return WebElement for the Automated Case Data card anchor
     */
    public WebElement getAutomatedCaseDataCard() {
        return getElementByXPath("//li[@data-label = 'Automated Case Data']//a");
    }

    /**
     * Returns a reference to the Veteran card tab.
     *
     * @return WebElement for the Veteran card anchor
     */
    public WebElement getVeteranCard() {
        return getElementByXPath("//li[@data-label = 'Veteran']//a");
    }

    /**
     * Returns a reference to the More Tabs dropdown button adjacent to the given card tab.
     *
     * @param visibleCardTab The name of the visible card tab beside the More button
     * @return WebElement for the More Tabs dropdown button
     */
    public WebElement getMoreDropdown(String visibleCardTab) {
        return getElementByXPath(
                "//li[@title='" + visibleCardTab + "']/parent::*//button[@title='More Tabs']");
    }

    // ─────────────────────────────────────────────
    //  XPath Builders — Case Record Locators
    // ─────────────────────────────────────────────

    /**
     * Builds an XPath to locate a case number link in an open report
     * based on another identifier in the same data row.
     *
     * @param caseIdentifier A value in the same row as the case number
     *                       (e.g., claimant name, MBMS case name)
     * @return String XPath for the case number link in the report
     */
    public String caseNumberXpathByIdentifier(String caseIdentifier) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[.='" + caseIdentifier + "']")));
        return "//div[.='" + caseIdentifier + "']/ancestor::tr//th//a";
    }

    /**
     * Returns the case number link WebElement in an open report,
     * found using another identifier value in the same data row.
     *
     * @param caseIdentifier A value in the same row as the case number
     * @return WebElement for the case number link
     */
    public WebElement getAssociatedCaseNumber(String caseIdentifier) {
        String xPath = caseNumberXpathByIdentifier(caseIdentifier);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        scrollIntoView(xPath);
        return driver.findElement(By.xpath(xPath));
    }

    // ─────────────────────────────────────────────
    //  Verification Field Helpers
    // ─────────────────────────────────────────────

    /**
     * Returns the WebElement for a field containing the expected value,
     * scoped to the specified card on the Case Details page.
     * Uses lightning-formatted-text for value matching.
     *
     * @param card                Card name as displayed on Case Details (e.g., "Eligibility Determination")
     * @param fieldName           Field label as displayed (e.g., "Eligibility Review Status")
     * @param expectedDataInField Text expected in the field (e.g., "Assigned")
     * @return WebElement for the matched field value element
     */
    public WebElement getExpectedFieldData(String card, String fieldName, String expectedDataInField) {
        return getElementByXPath("//li[@title='" + card + "']/ancestor::*//span[text() = '" + fieldName
                + "']/ancestor::div//lightning-formatted-text[text() = '" + expectedDataInField + "']");
    }

    /**
     * Returns the WebElement for a field containing the expected value,
     * scoped to the specified card. Uses span tag for value matching.
     *
     * @param card                Card name as displayed on Case Details
     * @param fieldName           Field label as displayed
     * @param expectedDataInField Text expected in the field
     * @return WebElement for the matched span value element
     */
    public WebElement getExpectedFieldDataSpan(String card, String fieldName, String expectedDataInField) {
        return getElementByXPath("//li[@title='" + card + "']/ancestor::*//span[text() = '" + fieldName
                + "']/ancestor::div//span[text() = '" + expectedDataInField + "']");
    }

    // ─────────────────────────────────────────────
    //  Click Methods
    // ─────────────────────────────────────────────

    /**
     * Clicks the Additional Case Details card tab and waits for the page to load.
     */
    public void clickAdditionalCaseDetailsCard() {
        jsClick(getAdditionalCaseDetailsCard());
        waitForAppLoad();
    }

    /**
     * Clicks the Additional Contacts card tab and waits for the page to load.
     */
    public void clickAdditionalContactsCard() {
        jsClick(getAdditionalContactsCard());
        waitForAppLoad();
    }

    /**
     * Clicks the Automated Case Data card tab and waits for the page to load.
     */
    public void clickAutomatedCaseDataCard() {
        jsClick(getAutomatedCaseDataCard());
        waitForAppLoad();
    }

    /**
     * Clicks the Veteran card tab and waits for the page to load.
     */
    public void clickVeteranCard() {
        jsClick(getVeteranCard());
        waitForAppLoad();
    }

    /**
     * Clicks the inline edit pencil icon for the specified field.
     *
     * @param fieldName The visible field name label on screen
     */
    public void clickEditPencil(String fieldName) {
        getEditPencil(fieldName).click();
        waitForAppLoad();
    }

    /**
     * Clicks the Save button on the inline edit form and waits for save to complete.
     */
    public void clickSave() {
        getElementByXPath("//button[@name= 'SaveEdit']").click();
        waitForAppLoad();
        sleep(5);
    }

    /**
     * Clicks the case number link in an open report based on another identifier
     * in the same data row.
     *
     * @param caseIdentifier An identifying value in the same row as the case number
     * @return boolean true if the case was visible in the report before clicking
     */
    public boolean clickAssociatedCaseNumber(String caseIdentifier) {
        boolean caseVisible = getAssociatedCaseNumber(caseIdentifier).isDisplayed();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(caseNumberXpathByIdentifier(caseIdentifier)))).click();
        waitForAppLoad();
        return caseVisible;
    }

    /**
     * Clicks a hidden card by opening the More Tabs dropdown and selecting it.
     *
     * @param visibleCardTab  The visible card tab name with the More option
     * @param cardTabToSelect The hidden card name to select from the dropdown
     */
    public void selectOptionFromMoreDropdown(String visibleCardTab, String cardTabToSelect) {
        getMoreDropdown(visibleCardTab).click();
        waitForAppLoad();
        getElementByXPath("//li[@data-overflow]//span[text() = '" + cardTabToSelect + "']").click();
        waitForAppLoad();
    }

    // ─────────────────────────────────────────────
    //  Select Methods — Dropdowns
    // ─────────────────────────────────────────────

    /**
     * Selects the specified option from the Letter Sent dropdown.
     *
     * @param yesOrNo The option to select: "Yes" or "No"
     */
    public void selectLetterSentOption(String yesOrNo) {
        selectInCardDropdownOption(getLetterSentDropDown(), yesOrNo);
    }

    /**
     * Selects the specified option from the Letter Status dropdown.
     *
     * @param status The status option to select (e.g., "Finalized")
     */
    public void selectLetterStatusOption(String status) {
        selectInCardDropdownOption(getLetterStatusDropDown(), status);
    }

    /**
     * Selects the specified List View from the List View dropdown.
     *
     * @param listViewToSelect The list view option to select
     */
    public void selectListView(String listViewToSelect) {
        selectDropdownOption(getSelectAListViewDropDown(), listViewToSelect);
    }

    /**
     * Selects a dropdown option scoped to its parent card container.
     * Prevents selecting the wrong option when the same text appears elsewhere on screen.
     *
     * @param dropDownElement The dropdown trigger WebElement
     * @param optionText      The option text to select
     */
    public void selectInCardDropdownOption(WebElement dropDownElement, String optionText) {
        if (optionText != null) {
            jsClick(dropDownElement);
            waitForAppLoad();
            String optionXPath = getXpathFromElement(dropDownElement)
                    .concat("/ancestor::flexipage-field//span[@title = '" + optionText + "']");
            WebElement optionToSelect = getElementByXPath(optionXPath);
            scrollIntoView(optionToSelect);
            jsClick(optionToSelect);
            waitForAppLoad();
        }
    }

    // ─────────────────────────────────────────────
    //  Enter / Set Methods
    // ─────────────────────────────────────────────

    /**
     * Clears the Letter Generated Date field and enters the specified date.
     * Presses Enter to confirm the entry.
     *
     * @param date The date string to enter (e.g., "05/01/2026")
     */
    public void enterLetterGeneratedDate(String date) {
        getLetterGeneratedDateField().clear();
        getLetterGeneratedDateField().sendKeys(date);
        action.sendKeys(Keys.ENTER).perform();
        waitForAppLoad();
    }

    /**
     * Clicks the Eligibility Review Status dropdown and selects the specified option.
     *
     * @param dropdownOption The option to select from the Eligibility Review Status dropdown
     */
    public void enterEligibilityReviewStatus(String dropdownOption) {
        jsClick(getEligibilityReviewStatusField());
        waitForAppLoad();
        jsClick(getElementByXPath(
                "//lightning-base-combobox-item[@data-value= '" + dropdownOption + "']"));
        waitForAppLoad();
    }

    // ─────────────────────────────────────────────
    //  Read Methods
    // ─────────────────────────────────────────────

    /**
     * Returns today's date formatted as MM/dd/YYYY.
     *
     * @return String today's date (e.g., "05/01/2026")
     */
    public String returnTodaysDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        return date.format(dtf);
    }

    // ─────────────────────────────────────────────
    //  Verification Lists
    // ─────────────────────────────────────────────

    /**
     * Returns a list of expected field data WebElements on the Additional Case Details card.
     * Navigates to the card and scrolls to relevant sections before building the list.
     *
     * @return List of WebElements representing expected field values on the card
     */
    public List<WebElement> listOfAdditionalCaseDetailsDataToVerify() {
        clickAdditionalCaseDetailsCard();
        scrollIntoView("//span[@title = 'Additional Case Details']");
        waitForAppLoad();
        scrollIntoView("//span[@title = 'Document Case Identifiers (DCIs)']");
        waitForAppLoad();
        return List.of(
                getExpectedFieldData("Additional Case Details", "Origin", "Web"),
                getExpectedFieldData("Additional Case Details", "Originating Packet ID", "111"));
    }

    /**
     * Returns a list of expected field data WebElements on the Additional Contacts card.
     * Navigates to the card before building the list.
     *
     * @return List of WebElements representing expected field values on the card
     */
    public List<WebElement> listOfAdditionalContactsDataToVerify() {
        jsClick(getElementByXPath("//li[@data-label = 'Additional Contacts']//a"));
        waitForAppLoad();
        return List.of(
                getExpectedFieldDataSpan("Additional Contacts", "Full Name",
                        "AutomationRepFirst AutomationRepMiddle AutomationRepLast JR"),
                getExpectedFieldDataSpan("Additional Contacts", "Birthdate", "3/1/1990"),
                getExpectedFieldDataSpan("Additional Contacts", "Relationship to Veteran", "ADULT DEPENDENT SON"),
                getExpectedFieldDataSpan("Additional Contacts", "Additional Contact Type", "Persons Buried"),
                getExpectedFieldDataSpan("Additional Contacts", "Interment Location",
                        "TEST AUTOMATION CEM TWO - 998"));
    }

    /**
     * Returns a list of expected field data WebElements on the Veteran card.
     * Navigates to the card before building the list.
     *
     * @return List of WebElements representing expected field values on the Veteran card
     */
    public List<WebElement> listOfVeteranDetailsDataToVerify() {
        clickVeteranCard();
        return List.of(
                getExpectedFieldData("Veteran", "Full Name", "Automation Test Veteran"),
                getExpectedFieldData("Veteran", "Alias First Name", "Alias"),
                getExpectedFieldData("Veteran", "Alias Middle Name", "Automation"),
                getExpectedFieldData("Veteran", "Alias Last Name", "Name"),
                getExpectedFieldData("Veteran", "Alias Suffix", "THIRD"));
    }

    /**
     * Checks if the specified card is currently visible on the Case Details page.
     *
     * @param cardXPath XPath of the card element to check
     * @return boolean true if the card is present and visible
     */
    public boolean isCardVisible(String cardXPath) {
        return isPresent(cardXPath);
    }
}
