package com.buchirano.automation.pages.application.cameo;

import com.buchirano.automation.enums.Gender;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class for the Applicant Search modal within NexusCM Case Management.
 *
 * <p><b>Modal:</b> Applicant Search</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class ApplicantSearchModal extends ModalPageClass {

    @Override
    public AutomatedObject getSearchButton() { return getButtonByDataId("searchButton"); }

    public AutomatedObject resultsFirstNameLabel() { return getElementBy("data-id", "firstNameLabel1"); }
    public AutomatedObject firstNameField() { return getInputByDataId("contactModalFirstName"); }
    public AutomatedObject middleNameField() { return getInputByDataId("contactModalMiddleName"); }
    public AutomatedObject lastNameField() { return getInputByDataId("contactModalLastName"); }
    public AutomatedObject ssnField() { return getInputByDataId("contactModalSSN"); }
    public AutomatedObject birthdateField() { return getInputByDataId("contactModalDOB"); }
    public AutomatedObject icnField() { return getInputByDataId("contactModalICN"); }
    public AutomatedObject edipiField() { return getInputByDataId("contactModalEDIPI"); }
    public AutomatedObject applicantIdField() { return getInputByDataId("contactModalId"); }
    public AutomatedObject genderField() { return getElementBy("data-id", "contactModalGender"); }
    public AutomatedObject phoneField() { return getInputByDataId("contactModalPhone"); }
    public AutomatedObject mothersMaidenNameField() { return getInputByDataId("contactModalMothersMaidenName"); }
    public AutomatedObject birthStateField() { return getElementBy("data-id", "contactModalBirthState"); }
    public AutomatedObject birthCityField() { return getInputByDataId("contactModalBirthCity"); }
    public AutomatedObject getApplicantIdField() { return getElementBy("data-id", "contactModalId"); }
    public AutomatedObject getNoRecordsFoundXpath() { return getElementByXPath("//*[@data-id='resultsMessage']//h2"); }

    public void enterContactSearchValues(String firstName, String middleName, String lastName,
            String ssn, String birthDate, String icn, String edipi, String gender, String phone,
            String mothersMaidenName, String birthCity, String birthState) {
        waitForVisibility("//*[@data-id='contactModalSSN']//input");
        firstNameField().sendKeys(firstName);
        middleNameField().sendKeys(middleName);
        lastNameField().sendKeys(lastName);
        ssnField().sendKeys(ssn);
        birthdateField().sendKeys(birthDate);
        icnField().sendKeys(icn);
        edipiField().sendKeys(edipi);
        selectDropdownOption(genderField(), gender);
        phoneField().sendKeys(phone);
        mothersMaidenNameField().sendKeys(mothersMaidenName);
        birthCityField().sendKeys(birthCity);
        if (!birthState.equals("--None--")) selectDropdownOption(birthStateField(), birthState);
    }

    public void enterContactSearchGender(Gender gender) { selectDropdownOption(genderField(), gender.getText()); }
}
