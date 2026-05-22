package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class for the Applicant Details screen within NexusCM Case Management.
 *
 * <p><b>Screen:</b> Case Establishment — Applicant Details</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class ApplicantPage extends LightningBasePageClass {

    public final String firstNameElementSelector = "//*[@data-id='vetFirstName']//input";
    public final String middleNameElementSelector = "//*[@data-id='vetMiddleName']//input";
    public final String lastNameElementSelector = "//*[@data-id='vetLastName']//input";
    public final String suffixElementSelector = "//*[@data-id='vetSuffix']//button";
    public final String suffixSelectedOption = "//*[@data-id='rowSuffix']//button";
    public final String suffixPicklistValues = "//*[@data-id='rowSuffix']//lightning-base-combobox-item";
    public final String suffixPicklistValuesForCounting = "//*[@data-id='vetSuffix']//lightning-base-combobox-item";
    public final String aliasFirstNameElementSelector = "//*[@data-id='vetAliasFirstName']//input";
    public final String aliasMiddleNameElementSelector = "//*[@data-id='vetAliasMiddleName']//input";
    public final String aliasLastNameElementSelector = "//*[@data-id='vetAliasLastName']//input";
    public final String aliasSuffixElementSelector = "//*[@data-id='vetAliasSuffix']//button";
    public final String aliasSuffixPicklistValues = "//*[@data-id='vetAliasSuffix']//lightning-base-combobox-item";
    public final String ssnElementSelector = "//*[@data-id='vetSSShow']//input";
    public final String maritalStatusElementSelector = "//*[@data-id='vetMaritalStatus']//button";
    public final String maritalStatusPicklistValues = "//*[@data-id='vetMaritalStatus']//lightning-base-combobox-item";
    public final String serviceNumberFormElementSelector = "//*[@data-id='vetServiceNumber']//input";
    public final String claimNumberElementSelector = "//*[@data-id='vetClaimNumber']//input";
    public final String militaryStatusElementSelector = "//*[@data-id='vetMilitaryStatus']//button";
    public final String militaryStatusPicklistValues = "//*[@data-id='vetMilitaryStatus']//lightning-base-combobox-item";
    public final String serviceEligibilityElementSelector = "//*[@data-id='vetServiceEligibilityIndicator']//button";
    public final String serviceEligibilityPicklistValues = "//*[@data-id='vetServiceEligibilityIndicator']//lightning-base-combobox-item";
    public final String verifyingStationElementSelector = "//*[@data-id='vetVerifyingStation']//input";
    public final String verifyingStationResult = "//ul[@role='group']//lightning-base-combobox-item";
    public final String typeOfDischargeFormElementSelector = "//*[@data-id='vetTypeOfDischargeForm']//input";
    public final String characterOfServiceElementSelector = "//*[@data-id='vetCharacterOfService']//button";
    public final String characterOfServicePicklistValues = "//*[@data-id='vetCharacterOfService']//lightning-base-combobox-item";
    public final String valorSearchElementSelector = "//*[@data-id='lookupInput']//input";
    public final String warningModalText = "//*[@data-id='message']";
    public final String alertText = "//*[text()='Complete this field.']";
    public final String valorCodePickListValues = "//ul[@data-id='dropdown']";
    public final String warningText = "//*[@data-id='ErrorMessageBanner']";
    public final String applicantPreviousButton = "(//button[contains(.,'Previous')])[2]";

    public final String cityStateZipCodeCountryDropdownResultElementSelector(String expectedOptionText) {
        return "//li[@role='presentation']//*[@aria-checked='false']//*[contains(text(), '" + expectedOptionText + "')]";
    }

    private final ModalPageClass modal = new ModalPageClass();
    private final ApplicantSearchModal searchModal = new ApplicantSearchModal();

    public AutomatedObject getApplicantId() { return getAutomatedObjectFromName("ID"); }
    public AutomatedObject getFirstNameInput() { scrollIntoView(firstNameElementSelector); return getElementByXPath(firstNameElementSelector); }
    public AutomatedObject getMiddleNameInput() { scrollIntoView(middleNameElementSelector); return getElementByXPath(middleNameElementSelector); }
    public AutomatedObject getLastNameInput() { scrollIntoView(lastNameElementSelector); return getElementByXPath(lastNameElementSelector); }
    public AutomatedObject getSuffixDropdown() { scrollIntoView(suffixElementSelector); return getElementByXPath(suffixElementSelector); }
    public AutomatedObject getAliasFirstNameInput() { scrollIntoView(aliasFirstNameElementSelector); return getElementByXPath(aliasFirstNameElementSelector); }
    public AutomatedObject getAliasMiddleNameInput() { scrollIntoView(aliasMiddleNameElementSelector); return getElementByXPath(aliasMiddleNameElementSelector); }
    public AutomatedObject getAliasLastNameInput() { scrollIntoView(aliasLastNameElementSelector); return getElementByXPath(aliasLastNameElementSelector); }
    public AutomatedObject getAliasSuffixInput() { scrollIntoView(aliasSuffixElementSelector); return getElementByXPath(aliasSuffixElementSelector); }
    public AutomatedObject getSSNInput() { scrollIntoView(ssnElementSelector); return getElementByXPath(ssnElementSelector); }
    public AutomatedObject getMaritalStatusDropdown() { scrollIntoView(maritalStatusElementSelector); return getElementByXPath(maritalStatusElementSelector); }
    public AutomatedObject getZipCodeSearchField() { return getAutomatedObjectFromName("City, State, Zip Code, County, Country"); }
    public AutomatedObject getServiceNumberInput() { scrollIntoView(serviceNumberFormElementSelector); return getElementByXPath(serviceNumberFormElementSelector); }
    public AutomatedObject getClaimNumberInput() { scrollIntoView(claimNumberElementSelector); return getElementByXPath(claimNumberElementSelector); }
    public AutomatedObject getMilitaryStatusDropdown() { scrollIntoView(militaryStatusElementSelector); return getElementByXPath(militaryStatusElementSelector); }
    public AutomatedObject getServiceEligibilityDropdown() { scrollIntoView(serviceEligibilityElementSelector); return getElementByXPath(serviceEligibilityElementSelector); }
    public AutomatedObject getVerifyingStationSearch() { scrollIntoView(verifyingStationElementSelector); return getElementByXPath(verifyingStationElementSelector); }
    public AutomatedObject getTypeOfDischargeFormInput() { scrollIntoView(typeOfDischargeFormElementSelector); return getElementByXPath(typeOfDischargeFormElementSelector); }
    public AutomatedObject getCharacterOfServiceDropdown() { scrollIntoView(characterOfServiceElementSelector); return getElementByXPath(characterOfServiceElementSelector); }
    public AutomatedObject getValorSearch() { scrollIntoView(valorSearchElementSelector); return getElementByXPath(valorSearchElementSelector); }
    public AutomatedObject getWarningModalText() { scrollIntoView(warningModalText); return getElementByXPath(warningModalText); }
    public List<AutomatedObject> getAlertTexts() { return getElementsByXPath(alertText); }
    public AutomatedObject getAlertModalClose() { return getElementBy("class", "toastClose"); }
    public AutomatedObject getAlertModalText() { return getElementBy("class", "toastMessage"); }
    public AutomatedObject getWarningMessage() { scrollIntoView(warningText); return getElementByXPath(warningText); }
    public AutomatedObject modifyContactCheckbox() { scrollIntoView(getInputXPathByDataId("modifyContactCheckbox")); return getInputByDataId("modifyContactCheckbox"); }
    public AutomatedObject modifyContactFirstName() { scrollIntoView(getInputXPathByDataId("vetFirstNameModified")); return getInputByDataId("vetFirstNameModified"); }
    public AutomatedObject modifyContactMiddleName() { scrollIntoView(getInputXPathByDataId("vetMiddleNameModified")); return getInputByDataId("vetMiddleNameModified"); }
    public AutomatedObject modifyContactLastName() { scrollIntoView(getInputXPathByDataId("vetLastNameModified")); return getInputByDataId("vetLastNameModified"); }
    public AutomatedObject modifyContactPhoneNumber() { scrollIntoView(getInputXPathByDataId("vetPhoneModified")); return getInputByDataId("vetPhoneModified"); }
    public AutomatedObject modifyContactBirthdate() { scrollIntoView(getInputXPathByDataId("vetBirthDateModified")); return getInputByDataId("vetBirthDateModified"); }
    public AutomatedObject getCityStateZipCodeCountryDropdownSearchResult(String expectedOptionText) { scrollIntoView(cityStateZipCodeCountryDropdownResultElementSelector(expectedOptionText)); return getElementByXPath(cityStateZipCodeCountryDropdownResultElementSelector(expectedOptionText)); }
    public AutomatedObject applicantSearchButton() { scrollIntoView(getButtonXPathByDataId("searchContactButton")); return getButtonByDataId("searchContactButton"); }
    public AutomatedObject applicantClearContactButton() { scrollIntoView(getButtonXPathByDataId("clearForm")); return getButtonByDataId("clearForm"); }
    public AutomatedObject usePreviousDecedentCheckbox() { scrollIntoView(getInputXPathByDataId("previousDecdentCheckbox")); return getInputByDataId("previousDecdentCheckbox"); }

    public List<AutomatedObject> getSuffixDropdownOptions() { return getElementsByXPath(suffixPicklistValues); }
    public List<AutomatedObject> getSuffixDropdownOptionsForCounting() { return getElementsByXPath(suffixPicklistValuesForCounting); }
    public List<AutomatedObject> getAliasSuffixDropdownOptions() { return getElementsByXPath(aliasSuffixPicklistValues); }
    public List<AutomatedObject> getMaritalStatusDropdownOptions() { return getElementsByXPath(maritalStatusPicklistValues); }
    public List<AutomatedObject> getMilitaryStatusDropdownOptions() { return getElementsByXPath(militaryStatusPicklistValues); }
    public List<AutomatedObject> getServiceEligibilityDropdownOptions() { return getElementsByXPath(serviceEligibilityPicklistValues); }
    public List<AutomatedObject> getCharacterOfServiceDropdownOptions() { return getElementsByXPath(characterOfServicePicklistValues); }
    public List<AutomatedObject> getValorCodePickListItems() { return getElementsBy("li", valorCodePickListValues); }

    public void enterName(String firstName, String middleName, String lastName, String suffix) {
        fillInputField(getFirstNameInput(), firstName);
        fillInputField(getMiddleNameInput(), middleName);
        fillInputField(getLastNameInput(), lastName);
        pageScrollUp();
        if (suffix != null && !suffix.isBlank()) selectDropdownGeneralOption(getSuffixDropdown(), suffix);
    }

    public void enterAliasName(String aliasFirstName, String aliasMiddleName, String aliasLastName, String aliasSuffix) {
        fillInputField(getAliasFirstNameInput(), aliasFirstName);
        fillInputField(getAliasMiddleNameInput(), aliasMiddleName);
        fillInputField(getAliasLastNameInput(), aliasLastName);
        if (aliasSuffix != null && !aliasSuffix.isBlank()) selectDropdownGeneralOption(getAliasSuffixInput(), aliasSuffix);
    }

    public void enterPersonalInformation(String ssn, String maritalStatus, String serviceNumber,
            String claimNumber, String maidenName, String isDeceased, String dateOfDeath,
            String birthDate, String birthCity, String birthState, String phone, String email) {
        fillInputField(getSSNInput(), ssn);
        fillInputField(getServiceNumberInput(), serviceNumber);
        fillInputField(getClaimNumberInput(), claimNumber);
        if (maritalStatus != null && !maritalStatus.isEmpty()) selectDropdownOption(getMaritalStatusDropdown(), maritalStatus);
        fillInputField(getAutomatedObjectFromName("Maiden Name"), maidenName);
        if (isDeceased != null && !isDeceased.isEmpty()) selectDropdownOption(getAutomatedObjectFromName("Is Applicant Deceased?"), isDeceased);
        fillInputField(getAutomatedObjectFromName("Date of Death"), dateOfDeath);
        fillInputField(getAutomatedObjectFromName("Birthdate"), birthDate);
        fillInputField(getAutomatedObjectFromName("Birth_City"), birthCity);
        if (birthState != null && !birthState.isEmpty()) selectDropdownOption(getAutomatedObjectFromName("Birth_State"), birthState);
        fillInputField(getAutomatedObjectFromName("Phone"), phone);
        fillInputField(getAutomatedObjectFromName("Email"), email);
    }

    public void enterApplicantAddress(String addressLineOne, String addressLineTwo, String zipCode) {
        fillInputField(getAutomatedObjectFromName("Address Line One"), addressLineOne);
        fillInputField(getAutomatedObjectFromName("Address Line Two"), addressLineTwo);
        if (getZipCodeSearchField().getPropertyValue("value").isEmpty() && zipCode != null) {
            getZipCodeSearchField().sendKeys(zipCode);
            waitForSalesforceLoad();
            getCityStateZipCodeCountryDropdownSearchResult(zipCode).click();
        }
        waitForSalesforceLoad();
    }

    public void enterMilitaryInformation(String militaryStatus, String serviceEligibility,
            String verifyingStation, String dischargeForm, String characterOfService, String valorNonValorAward) {
        if (militaryStatus != null) selectDropdownOption(getMilitaryStatusDropdown(), militaryStatus.toUpperCase());
        if (serviceEligibility != null) selectDropdownGeneralOption(getServiceEligibilityDropdown(), serviceEligibility);
        if (getVerifyingStationSearch().getPropertyValue("value").isEmpty() && verifyingStation != null) {
            getElementByXPath(verifyingStationElementSelector).sendKeys(verifyingStation);
            safeSleep(1500);
            getElementBy("title", verifyingStation).click();
        }
        fillInputField(getTypeOfDischargeFormInput(), dischargeForm);
        if (characterOfService != null) selectDropdownOption(getCharacterOfServiceDropdown(), characterOfService);
        Search search = getSearch("xpath", "//*[@data-id='valorNonValorLookup']//lightning-layout-item");
        if (!isObjectPresent(search) && valorNonValorAward != null) {
            getValorSearch().click();
            getValorSearch().sendKeys(valorNonValorAward);
            safeSleep(1500);
            pressKey("Down Arrow", 1);
            pressKey("Enter", 1);
        }
    }

    public void enterAllApplicantDetails(String firstName, String middleName, String lastName,
            String suffix, String deceased, String militaryStatus) {
        String dateOfDeath = deceased.equalsIgnoreCase("yes") ? "May 5, 2025" : null;
        enterName(firstName, middleName, lastName, suffix);
        enterAliasName("Alias", "Automation", "Name", "THIRD");
        enterPersonalInformation(randomSSN(), "Married", "4444", "22", "Maiden", deceased, dateOfDeath, "Mar 1, 1960", "Sarasota", "Florida", "(888) 888-8888", "automation-test@email.com");
        enterApplicantAddress("24 North", "8 South", "29588");
        enterMilitaryInformation(militaryStatus, "Yes", "FOREST HILL CEMETERY - 123", "333 SEED FORM", "Honorable", "Purple Heart");
    }

    public void openSearchApplicantModal() { applicantSearchButton().click(); waitForSalesforceLoad(); }
    public void clickModalSearchButton() { searchModal.getSearchButton().click(); }

    public void enterApplicantSearchValues(String firstName, String middleName, String lastName,
            String ssn, String birthDate, String icn, String edipi, String gender, String phone,
            String mothersMaidenName, String birthCity, String birthState) {
        searchModal.firstNameField().sendKeys(firstName);
        searchModal.middleNameField().sendKeys(middleName);
        searchModal.lastNameField().sendKeys(lastName);
        searchModal.ssnField().sendKeys(ssn);
        searchModal.birthdateField().sendKeys(birthDate);
        searchModal.icnField().sendKeys(icn);
        searchModal.edipiField().sendKeys(edipi);
        selectDropdownOption(searchModal.genderField(), gender);
        searchModal.phoneField().sendKeys(phone);
        searchModal.mothersMaidenNameField().sendKeys(mothersMaidenName);
        searchModal.birthCityField().sendKeys(birthCity);
        if (!birthState.equals("--None--")) selectDropdownOption(searchModal.birthStateField(), birthState);
    }

    public void clearApplicantSearchFields() {
        getFirstNameInput().clear(); getMiddleNameInput().clear(); getLastNameInput().clear(); getSSNInput().clear();
        searchModal.birthdateField().clear(); selectDropdownOption(searchModal.genderField(), "--None--");
        searchModal.phoneField().clear(); searchModal.mothersMaidenNameField().clear();
        searchModal.birthCityField().clear(); selectDropdownOption(searchModal.birthStateField(), "--None--");
    }

    public void clickModifyContactCheckBoxIfUnselected() { if (!modifyContactCheckbox().isSelected()) modifyContactCheckbox().click(); }

    public void enterModifyContactFields(String firstName, String middleName, String lastName) {
        modifyContactFirstName().clear(); modifyContactMiddleName().clear(); modifyContactLastName().clear();
        modifyContactFirstName().sendKeys(firstName); modifyContactMiddleName().sendKeys(middleName); modifyContactLastName().sendKeys(lastName);
    }

    public void openApplicantSearchModalAndSelect(String firstName, String middleName, String lastName,
            String ssn, String birthDate, String icn, String edipi, String gender, String phone,
            String mothersMaidenName, String birthCity, String birthState) {
        openSearchApplicantModal();
        enterApplicantSearchValues(firstName, middleName, lastName, ssn, birthDate, icn, edipi, gender, phone, mothersMaidenName, birthCity, birthState);
        clickModalSearchButton();
        safeSleep(2500);
        modal.selectFirstEnabledResult();
    }

    public void clearContactButton() { applicantClearContactButton().click(); waitForSalesforceLoad(); }
    public void clearApplicantNameFields() { getFirstNameInput().clear(); getMiddleNameInput().clear(); getLastNameInput().clear(); }
    public void clearApplicantAliasNameFields() { getAliasFirstNameInput().clear(); getAliasMiddleNameInput().clear(); getAliasLastNameInput().clear(); }

    public void clickValorPickList() {
        getValorSearch().click();
        for (int i = 0; i < 80; i++) pressKey("Down Arrow", 1);
    }

    public void inputValorCodeField(String value) { getValorSearch().sendKeys(value); }
    public void clearValorCodeField() { getValorSearch().clear(); }
    public void clickUsePreviousDecedentApplicant() { usePreviousDecedentCheckbox().click(); }
    public void clickModifyContactCheckbox() { modifyContactCheckbox().click(); modifyContactCheckbox().click(); }

    public List<AutomatedObject> listOfAllFields() {
        return List.of(getFirstNameInput(), getMiddleNameInput(), getLastNameInput(), getSuffixDropdown(),
                getAliasFirstNameInput(), getAliasMiddleNameInput(), getAliasLastNameInput(), getAliasSuffixInput(),
                getSSNInput(), getServiceNumberInput(), getClaimNumberInput(), getMaritalStatusDropdown(),
                getAutomatedObjectFromName("Maiden Name"), getAutomatedObjectFromName("Is Applicant Deceased?"),
                getAutomatedObjectFromName("Birthdate"), getAutomatedObjectFromName("Birth_City"),
                getAutomatedObjectFromName("Birth_State"), getAutomatedObjectFromName("Phone"),
                getAutomatedObjectFromName("Email"), getAutomatedObjectFromName("Address Line One"),
                getAutomatedObjectFromName("Address Line Two"),
                getAutomatedObjectFromName("City, State, Zip Code, County, Country"),
                getMilitaryStatusDropdown(), getServiceEligibilityDropdown(), getVerifyingStationSearch(),
                getTypeOfDischargeFormInput(), getCharacterOfServiceDropdown(), getValorSearch());
    }

    private void safeSleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); e.printStackTrace(); }
    }
}
