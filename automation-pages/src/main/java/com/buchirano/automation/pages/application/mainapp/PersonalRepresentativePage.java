package com.buchirano.automation.pages.application.mainapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class for the Personal Representative Details screen within CaseManagementApp.
 *
 * <p><b>Screen:</b> Case Establishment — Personal Representative Details</p>
 * <p><b>Layer:</b> Page Object (Application — CaseManagementApp)</p>
 */
public class PersonalRepresentativePage extends LightningBasePageClass {

    public final String personalRepresentativeIsPrimaryCheckbox = "//*[@data-id='persRepIsVetInput']";
    public final String suffixDropDown = "//*[@data-id='suffix']//button";
    public final String relationshipDropDown = "//*[@data-id='RelationshipToDecedent']//button";
    public final String relationshipValues = relationshipDropDown + "/ancestor::lightning-input-field//lightning-base-combobox-item";
    public final String suffixDropdownValues = suffixDropDown + "/ancestor::lightning-input-field//lightning-base-combobox-item";

    public AutomatedObject modifyContactCheckbox() { scrollIntoView(getInputXPathByDataId("modifyContactCheckbox")); return getInputByDataId("modifyContactCheckbox"); }
    public AutomatedObject getPersonalRepresentativeIsPrimaryCheckbox() { scrollIntoView(personalRepresentativeIsPrimaryCheckbox); return getElementByXPath(personalRepresentativeIsPrimaryCheckbox); }
    public AutomatedObject getFirstName() { scrollIntoView(getInputXPathByDataId("firstName")); return getInputByDataId("firstName"); }
    public AutomatedObject getMiddleName() { scrollIntoView(getInputXPathByDataId("middleName")); return getInputByDataId("middleName"); }
    public AutomatedObject getLastName() { scrollIntoView(getInputXPathByDataId("lastName")); return getInputByDataId("lastName"); }
    public AutomatedObject getSuffixDropDown() { scrollIntoView(suffixDropDown); return getElementByXPath(suffixDropDown); }
    public AutomatedObject getRelationshipDropDown() { scrollIntoView(relationshipDropDown); return getElementByXPath(relationshipDropDown); }
    public AutomatedObject getTaxId() { scrollIntoView(getInputXPathByDataId("nokSsnShow")); return getInputByDataId("nokSsnShow"); }
    public AutomatedObject getPhoneNumber() { scrollIntoView(getInputXPathByDataId("phoneNumber")); return getInputByDataId("phoneNumber"); }
    public AutomatedObject getEmail() { scrollIntoView(getInputXPathByDataId("emailShow")); return getInputByDataId("emailShow"); }
    public AutomatedObject getAddressLineOne() { scrollIntoView(getInputXPathByDataId("addressLineOne")); return getInputByDataId("addressLineOne"); }
    public AutomatedObject getAddressLineTwo() { scrollIntoView(getInputXPathByDataId("addressLineTwo")); return getInputByDataId("addressLineTwo"); }
    public AutomatedObject getZipCode() { scrollIntoView(getInputXPathByDataId("zipCode")); return getInputByDataId("zipCode"); }
    public AutomatedObject getModifiedContactFirstName() { scrollIntoView(getInputXPathByDataId("nokFirstNameModified")); return getInputByDataId("nokFirstNameModified"); }
    public AutomatedObject getModifiedContactMiddleName() { scrollIntoView(getInputXPathByDataId("nokMiddleNameModified")); return getInputByDataId("nokMiddleNameModified"); }
    public AutomatedObject getModifiedContactLastName() { scrollIntoView(getInputXPathByDataId("nokLastNameModified")); return getInputByDataId("nokLastNameModified"); }
    public AutomatedObject getModifiedContactPhoneNumber() { scrollIntoView(getInputXPathByDataId("nokPhoneModified")); return getInputByDataId("nokPhoneModified"); }
    public AutomatedObject getModifiedContactBirthdate() { scrollIntoView(getInputXPathByDataId("nokBirthDateModified")); return getInputByDataId("nokBirthDateModified"); }
    public List<AutomatedObject> getRelationshipPicklistValues() { return getElementsByXPath(relationshipValues); }
    public List<AutomatedObject> getSuffixPicklistValues() { return getElementsByXPath(suffixDropdownValues); }
    public AutomatedObject getZipLookupNoValues() { String path = "//*[@data-value='actionAdvancedSearch']"; scrollIntoView(path); return getElementByXPath(path); }
    public List<AutomatedObject> getGridRow(String searchText) { return getElementsByXPath("//td[.='" + searchText + "']/../td"); }
    public AutomatedObject getZipLookupModalXButton() { String path = "//*[@title='Close this window']"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject getGridNoResults() { String path = ".//div[@class='slds-text-heading--large noResultsTitle slds-p-bottom--large']"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject clearContactButton() { scrollIntoView(getButtonXPathByDataId("clearForm")); return getButtonByDataId("clearForm"); }
    public AutomatedObject isPrimaryApplicantCheckBox() { return getElementByXPath("//input[@name='MBMS_PersonalRepresentativeIsVeteran__c']"); }

    public void enterNameFields(String firstName, String middleName, String lastName, String suffix) {
        fillInputField(getFirstName(), firstName);
        fillInputField(getMiddleName(), middleName);
        fillInputField(getLastName(), lastName);
        if (suffix != null) selectDropdownGeneralOption(getSuffixDropDown(), suffix);
    }

    public void enterAdditionalFields(String relation, String taxId, String phoneNumber, String email) {
        selectRelationshipToClaimant(relation);
        fillInputField(getPhoneNumber(), phoneNumber);
        fillInputField(getTaxId(), taxId);
        fillInputField(getEmail(), email);
    }

    public void enterAddressFields(String addressOne, String addressTwo) {
        fillInputField(getAddressLineOne(), addressOne);
        fillInputField(getAddressLineTwo(), addressTwo);
    }

    public void clickModifyContactCheckbox() { modifyContactCheckbox().click(); modifyContactCheckbox().click(); }
    public void clickIsPrimaryApplicantCheckBox() { isPrimaryApplicantCheckBox().click(); waitForSalesforceLoad(); }
    public void modifyContactFirstnameIsEnabled(boolean isEnabled) { assertEquals(checkDataFieldStatus(getModifiedContactFirstName()), isEnabled); }
    public void modifyContactMiddleNameIsEnabled(boolean isEnabled) { assertEquals(checkDataFieldStatus(getModifiedContactMiddleName()), isEnabled); }
    public void modifyContactLastNameIsEnabled(boolean isEnabled) { assertEquals(checkDataFieldStatus(getModifiedContactLastName()), isEnabled); }
    public void modifyContactPhoneNumberIsEnabled(boolean isEnabled) { assertEquals(checkDataFieldStatus(getModifiedContactPhoneNumber()), isEnabled); }
    public void modifyContactBirthdateIsEnabled(boolean isEnabled) { assertEquals(checkDataFieldStatus(getModifiedContactBirthdate()), isEnabled); }

    public void enterModifyContactValues(String firstName, String middleName, String lastName) {
        getModifiedContactFirstName().clear(); getModifiedContactMiddleName().clear(); getModifiedContactLastName().clear();
        getModifiedContactFirstName().sendKeys(firstName); getModifiedContactMiddleName().sendKeys(middleName); getModifiedContactLastName().sendKeys(lastName);
    }

    public void selectRelationshipToClaimant(String relationshipToClaimant) {
        if (relationshipToClaimant == null) return;
        getRelationshipDropDown().click();
        WebElement element = driver.findElement(By.xpath(".//*[@aria-label='Relationship to Claimant']//span[@title = '" + relationshipToClaimant + "']"));
        scrollIntoView(element);
        element.click();
    }

    private boolean checkDataFieldStatus(AutomatedObject element) { return element.getPropertyValue("readOnly") == null; }

    public List<AutomatedObject> listOfAllFields() {
        return List.of(getFirstName(), getMiddleName(), getLastName(), getSuffixDropDown(),
                getRelationshipDropDown(), getTaxId(), getPhoneNumber(), getEmail(),
                getAddressLineOne(), getAddressLineTwo(), getZipCode());
    }
}
