package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.buchirano.automation.enums.ContactType;
import com.buchirano.automation.enums.Relationship;
import com.buchirano.automation.enums.Suffix;
import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Additional Contacts Details screen within the Case Establishment workflow.
 *
 * <p>This class supports creating, editing, and deleting additional contact records,
 * managing contact type selections, and retrieving populated field values from the
 * Additional Contacts screen.</p>
 *
 * <p><b>Screen:</b> Case Establishment — Additional Contacts</p>
 * <p><b>Layer:</b> Page Object (Application — CaMEO)</p>
 */
public class AdditionalContactsPage extends BasePageClass {

    // ========================== XPath Constants ==========================

    /** XPath for the currently selected Suffix option button. */
    public final String suffixSelectedOption = "//*[@data-id='rowSuffix']//button";

    /** XPath for all Relationship to Veteran picklist option items. */
    public final String relationshipToVeteranPicklistValues =
            "//*[@data-id='rowRelationshipToVeteran']//lightning-base-combobox-item";

    /** XPath for all Suffix picklist option items. */
    public final String suffixPicklistValues =
            "//*[@data-id='rowSuffix']//lightning-base-combobox-item";

    /** XPath for the error toast message text element. */
    public final String errorToastMessageText =
            "(.//*[normalize-space(text()) and normalize-space(.)='Error'])[1]/following::span[1]";

    /** XPath for the First Name required field error message. */
    public final String firstNameErrorMessageText =
            "//*/text()[normalize-space(.)='Complete this field.']/parent::*";

    /** XPath for the Last Name required field error message. */
    public final String lastNameErrorMessageText =
            "//lightning-layout-item[3]/slot/lightning-input-field/lightning-input/div[2]";

    /** XPath for the SSN required field error message. */
    public final String ssnErrorMessageText = "//lightning-input/div[2]";

    /** XPath for the warning modal message for invalid name characters. */
    public final String warningMessageText = "//c-cameo_modal/section/div/div/div[2]/div";

    /** XPath for the contact search First Name error message. */
    public final String contactSearchFirstNameErrorMessage =
            "//*/text()[normalize-space(.)='Complete this field.']/parent::*";

    /** XPath for the contact search Last Name error message. */
    public final String contactSearchLastNameErrorMessage =
            "//lightning-layout-item[3]/slot/lightning-input/div[2]";

    /** XPath for the three-field search requirement error message. */
    public final String contactSearchThirdFieldErrorMessage =
            "//*/text()[normalize-space(.)='You must complete three fields to conduct a search']/parent::*";

    /** XPath for the Search Contacts button. */
    public final String searchContactsButton = getButtonXPathByDataId("personEligibleContactSearch");

    /** XPath for the Create New Contact button. */
    public final String createContactButton = getButtonXPathByDataId("addPersonEligible");

    /** XPath for the First Name input field. */
    public final String firstName = getInputXPathByDataId("rowFirstName");

    /** XPath for the Middle Name input field. */
    public final String middleName = getInputXPathByDataId("rowMiddleName");

    /** XPath for the Last Name input field. */
    public final String lastName = getInputXPathByDataId("rowLastName");

    /** XPath for the Suffix dropdown button. */
    public final String suffixDropDown = "//*[@data-id='rowSuffix']//button";

    /** XPath for the Birthday input field. */
    public final String birthday = getInputXPathByDataId("rowBirthdate");

    /** XPath for the Relationship to Veteran dropdown button. */
    public final String relationshipToVeteranDropDown =
            "//*[@data-id='rowRelationshipToVeteran']//div/button";

    /** XPath for the Is Veteran checkbox. */
    public final String isVeteranCheckbox = getInputXPathByDataId("rowIsVeteran");

    /** XPath for the Set Aside checkbox. */
    public final String setAsideCheckbox = getInputXPathByDataId("rowSetAside");

    /** XPath for the Delete Row button. */
    public final String deleteRowButton = getButtonXPathByDataId("rowDeleteButton");

    /** XPath for the contact row identifier element. */
    public final String contactRow = getInputXPathByDataId("rowRecordTypeId");

    /** XPath for the SSN input field. */
    public final String ssn = getInputXPathByDataId("visibleSSN");

    // ========================== Element Accessors ==========================

    /**
     * Returns the Search Contacts button, scrolling it into view first.
     *
     * @return {@link AutomatedObject} for the Search Contacts button
     */
    public AutomatedObject searchContactsButton() {
        scrollIntoView(searchContactsButton);
        return getElementByXPath(searchContactsButton);
    }

    /**
     * Returns the Create New Contact button, scrolling it into view first.
     *
     * @return {@link AutomatedObject} for the Create New Contact button
     */
    public AutomatedObject getCreateNewContactButton() {
        scrollIntoView(createContactButton);
        return getElementByXPath(createContactButton);
    }

    /** @return list of First Name input fields across all contact rows */
    public List<AutomatedObject> getFirstName() { return getElementsByXPath(firstName); }

    /** @return list of Middle Name input fields across all contact rows */
    public List<AutomatedObject> getMiddleName() { return getElementsByXPath(middleName); }

    /** @return list of Last Name input fields across all contact rows */
    public List<AutomatedObject> getLastName() { return getElementsByXPath(lastName); }

    /** @return list of Suffix dropdown buttons across all contact rows */
    public List<AutomatedObject> getSuffixDropDown() { return getElementsByXPath(suffixDropDown); }

    /** @return list of Birthday input fields across all contact rows */
    public List<AutomatedObject> getBirthday() { return getElementsByXPath(birthday); }

    /** @return list of Relationship to Veteran dropdown buttons across all contact rows */
    public List<AutomatedObject> getRelationshipToVeteranDropDown() {
        return getElementsByXPath(relationshipToVeteranDropDown);
    }

    /** @return list of Is Veteran checkboxes across all contact rows */
    public List<AutomatedObject> getIsVeteranCheckbox() {
        return getElementsByXPath(isVeteranCheckbox);
    }

    /** @return list of Set Aside checkboxes across all contact rows */
    public List<AutomatedObject> getSetAsideCheckbox() {
        return getElementsByXPath(setAsideCheckbox);
    }

    /**
     * Returns the Delete Row button for the specified row number.
     *
     * @param rowNumber the 1-based row number to target
     * @return {@link AutomatedObject} for the delete button on the specified row
     */
    public AutomatedObject getDeleteRowButton(int rowNumber) {
        String deleteButton = "(" + deleteRowButton + ")[" + rowNumber + "]";
        scrollIntoView(deleteButton);
        return getElementByXPath(deleteButton);
    }

    /** @return list of all contact row identifier elements */
    public List<AutomatedObject> getPersonsEligibleRows() { return getElementsByXPath(contactRow); }

    /** @return list of SSN input fields across all contact rows */
    public List<AutomatedObject> getSSN() { return getElementsByXPath(ssn); }

    /** @return list of all Suffix dropdown option items */
    public List<AutomatedObject> getSuffixDropdownOptions() {
        return getElementsByXPath(suffixPicklistValues);
    }

    /** @return list of all Relationship to Veteran dropdown option items */
    public List<AutomatedObject> getRelationshipToVeteranDropdownOptions() {
        return getElementsByXPath(relationshipToVeteranPicklistValues);
    }

    /**
     * Returns the span element beneath a picklist item that holds its display value.
     *
     * @param element the picklist line item WebElement
     * @return the span child element containing the picklist value text
     */
    public WebElement getPicklistValue(WebElement element) {
        WebElement picklistValue = element.findElement(By.xpath(".//span[@class='slds-truncate']"));
        scrollIntoView(picklistValue);
        return picklistValue;
    }

    /**
     * Returns the currently selected Suffix option button.
     *
     * @return {@link AutomatedObject} for the selected Suffix option
     */
    public AutomatedObject getSuffixSelectedOption() {
        scrollIntoView(suffixSelectedOption);
        return getElementByXPath(suffixSelectedOption);
    }

    /**
     * Returns the Available list element for the specified Additional Contact Type
     * on the given contact row.
     *
     * @param additionalContactType the contact type display string to locate
     * @param rowNumber             the 1-based contact row number
     * @return {@link AutomatedObject} for the available contact type option
     */
    public AutomatedObject getAvailableAdditionalContactType(String additionalContactType, int rowNumber) {
        String path = "(.//div[text()='Additional Contact Type']//..//span[text()='Available'])["
                + rowNumber + "]//..//span[text()='" + additionalContactType + "']";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the Selected list element for the specified Additional Contact Type
     * on the given contact row.
     *
     * @param additionalContactType the {@link ContactType} enum value to locate
     * @param rowNumber             the 1-based contact row number
     * @return {@link AutomatedObject} for the selected contact type option
     */
    public AutomatedObject getSelectedAdditionalContactType(ContactType additionalContactType,
            int rowNumber) {
        String path = "(.//div[text()='Additional Contact Type']//..//span[text()='Selected'])["
                + rowNumber + "]//..//span[text()='" + additionalContactType.getText() + "']";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    // ========================== State ==========================

    /** Tracks the index of the current new contact row being populated. */
    public Integer indexToUse = null;

    // ========================== Interaction Methods ==========================

    /**
     * Clicks the Create New Contact button and waits for the DOM to stabilize.
     */
    public void clickCreateNewContactButton() {
        getCreateNewContactButton().click();
        waitForSalesforceLoad();
    }

    /**
     * Clicks the Search Contacts button and waits for the DOM to stabilize.
     */
    public void clickSearchContactsButton() {
        searchContactsButton().click();
        waitForSalesforceLoad();
    }

    /**
     * Enters name fields for a new contact row. Suffix is skipped if null.
     *
     * @param firstName  first name to enter
     * @param middleName middle name to enter
     * @param lastName   last name to enter
     * @param suffix     suffix to select; skipped if null
     */
    public void enterNewContactName(String firstName, String middleName,
            String lastName, String suffix) {
        setIndexToUse();
        fillInputField(getFirstName().get(indexToUse), firstName);
        fillInputField(getMiddleName().get(indexToUse), middleName);
        fillInputField(getLastName().get(indexToUse), lastName);
        if (suffix != null) {
            selectDropdownGeneralOption(getSuffixDropDown().get(indexToUse), suffix);
        }
    }

    /**
     * Enters name fields only (no suffix) for a new contact row.
     *
     * @param firstName  first name to enter
     * @param middleName middle name to enter
     * @param lastName   last name to enter
     */
    public void enterNewContactNameOnly(String firstName, String middleName, String lastName) {
        setIndexToUse();
        fillInputField(getFirstName().get(indexToUse), firstName);
        fillInputField(getMiddleName().get(indexToUse), middleName);
        fillInputField(getLastName().get(indexToUse), lastName);
    }

    /**
     * Enters the SSN value into the current contact row's SSN field.
     *
     * @param ssn the SSN string to enter
     */
    public void enterSsn(String ssn) { getSSN().get(indexToUse).sendKeys(ssn); }

    /** Clears the SSN field on the current contact row. */
    public void clearSsn() { getSSN().get(indexToUse).clear(); }

    /** Clears all name fields across all contact rows. */
    public void clearNewContactName() {
        getFirstName().clear();
        getLastName().clear();
        getMiddleName().clear();
    }

    /**
     * Enters additional contact information including birthdate, relationship,
     * and optional checkbox selections.
     *
     * @param birthDate    birth date string to enter
     * @param relationship relationship to veteran display string; skipped if null
     * @param isVeteran    if true, checks the Is Veteran checkbox
     * @param setAside     if true, checks the Set Aside checkbox
     */
    public void enterNewContactAdditionalInfo(String birthDate, String relationship,
            boolean isVeteran, boolean setAside) {
        setIndexToUse();
        fillInputField(getBirthday().get(indexToUse), birthDate);
        if (relationship != null) {
            getRelationshipToVeteranDropDown().get(indexToUse).click();
            WebElement element = driver.findElement(
                    By.xpath("//span[@title = '" + relationship + "']"));
            scrollIntoView(element);
            element.click();
        }
        if (isVeteran) { getIsVeteranCheckbox().get(indexToUse).click(); waitForSalesforceLoad(); }
        if (setAside) { getSetAsideCheckbox().get(indexToUse).click(); waitForSalesforceLoad(); }
    }

    /**
     * Determines and sets the index of the new empty contact row to populate.
     *
     * <p>Finds the last First Name field and verifies it is empty before
     * proceeding. Throws if the field is unexpectedly populated.</p>
     *
     * @throws RuntimeException if the new contact row fields are not empty
     */
    public void setIndexToUse() {
        if (indexToUse == null) {
            List<AutomatedObject> firstNameFields = getFirstName();
            indexToUse = firstNameFields.size() - 1;
            String currentValue = firstNameFields.get(indexToUse).getPropertyValue("value");
            if (!currentValue.isEmpty()) {
                throw new RuntimeException("New Additional Contact fields were not empty");
            }
        }
    }

    /**
     * Enters all non-blank contact details into the current new contact row.
     * Only populates fields whose corresponding parameter is non-blank or non-null.
     *
     * @param firstName             first name
     * @param middleName            middle name
     * @param lastName              last name
     * @param ssn                   social security number
     * @param birthDate             birth date
     * @param suffix                {@link Suffix} enum value; skipped if null
     * @param relationshipToVeteran {@link Relationship} enum value; skipped if null
     */
    public void enterNewContact(String firstName, String middleName, String lastName,
            String ssn, String birthDate, Suffix suffix, Relationship relationshipToVeteran) {
        setIndexToUse();
        if (!StringUtils.isBlank(firstName)) fillInputField(getFirstName().get(indexToUse), firstName);
        if (!StringUtils.isBlank(middleName)) fillInputField(getMiddleName().get(indexToUse), middleName);
        if (!StringUtils.isBlank(lastName)) fillInputField(getLastName().get(indexToUse), lastName);
        if (!StringUtils.isBlank(ssn)) fillInputField(getSSN().get(indexToUse), ssn);
        if (!StringUtils.isBlank(birthDate)) fillInputField(getBirthday().get(indexToUse), birthDate);
        if (suffix != null) selectDropdownOptionIfEmpty(getSuffixDropDown().get(indexToUse), suffix.getText());
        if (relationshipToVeteran != null) selectDropdownOption(
                getRelationshipToVeteranDropDown().get(indexToUse), relationshipToVeteran.getText());
    }

    /**
     * Clicks the Delete Row button for the specified row and waits for the DOM to stabilize.
     *
     * @param rowNumber the 1-based row number to delete
     */
    public void clickDeleteRow(int rowNumber) {
        getDeleteRowButton(rowNumber).click();
        waitForSalesforceLoad();
    }

    /**
     * Retrieves the full name of the first auto-populated contact as a single string.
     *
     * @return the full name string, or "First name field empty" if the field is blank
     */
    public String retrieveContactFullName() {
        List<AutomatedObject> firstNames = getFirstName();
        List<AutomatedObject> middleNames = getMiddleName();
        List<AutomatedObject> lastNames = getLastName();
        if (!firstNames.get(0).readText().isEmpty()) {
            return firstNames.get(0).getPropertyValue("value") + " "
                    + middleNames.get(0).getPropertyValue("value") + " "
                    + lastNames.get(0).getPropertyValue("value");
        }
        return "First name field empty";
    }

    /** @return the SSN value from the first contact row */
    public String retrieveSSN() { return getSSN().get(0).getPropertyValue("value"); }

    /** @return the Relationship to Veteran value from the first contact row */
    public String retrieveRelationshipToVeteran() {
        return getRelationshipToVeteranDropDown().get(0).getPropertyValue("value");
    }

    /** Clicks the Suffix dropdown on the current new contact row. */
    public void clickSuffixField() {
        setIndexToUse();
        getSuffixDropDown().get(indexToUse).click();
    }

    /** Clicks the Relationship to Veteran dropdown on the current new contact row. */
    public void clickRelationshipToVeteranField() {
        setIndexToUse();
        getRelationshipToVeteranDropDown().get(indexToUse).click();
    }

    /**
     * Selects a contact type by moving it from the Available list to the Selected list.
     * Clicks the move button twice to handle cases where the first click is not registered.
     *
     * @param additionalContactType the contact type display string to select
     * @param rowNumber             the 1-based contact row to operate on
     */
    public void selectContactType(String additionalContactType, int rowNumber) {
        getAvailableAdditionalContactType(additionalContactType, rowNumber).click();
        getElementByXPath("(//button[@title='Move selection to Selected'])[" + rowNumber + "]").click();
        getElementByXPath("(//button[@title='Move selection to Selected'])[" + rowNumber + "]").click();
        waitForSalesforceLoad();
    }

    /**
     * Moves a selected contact type back to the Available list.
     * Clicks the move button twice to handle cases where the first click is not registered.
     *
     * @param additionalContactType the {@link ContactType} enum value to deselect
     * @param rowNumber             the 1-based contact row to operate on
     */
    public void deselectContactType(ContactType additionalContactType, int rowNumber) {
        jsClick(getSelectedAdditionalContactType(additionalContactType, rowNumber));
        waitForSalesforceLoad();
        getElementByXPath(
                "(//button[@title='Move selection to Available'])[" + (rowNumber * 2) + "]").click();
        getElementByXPath(
                "(//button[@title='Move selection to Available'])[" + (rowNumber * 2) + "]").click();
        waitForSalesforceLoad();
    }

    /**
     * Types into the Interment Location field and selects the matching dropdown result.
     *
     * @param location the interment location string to search for and select
     */
    public void enterIntermentLocation(String location) {
        getAutomatedObjectFromName("Interment Location").sendKeys(location);
        waitForSalesforceLoad();
        Search search = getSearch();
        search.addCriteria("tag", "lightning-base-combobox-formatted-text");
        search.addCriteria("title", location);
        getObject(search).click();
    }

    /**
     * Returns all primary interactive fields for the specified contact row.
     *
     * @param contactRow the 1-based contact row number
     * @return unmodifiable list of all fields for that contact row
     */
    public List<AutomatedObject> listOfAllFields(int contactRow) {
        int idx = contactRow - 1;
        return List.of(
                getFirstName().get(idx),
                getMiddleName().get(idx),
                getLastName().get(idx),
                getSuffixDropDown().get(idx),
                getRelationshipToVeteranDropDown().get(idx)
        );
    }
}
