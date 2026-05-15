package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.buchirano.automation.enums.Gender;
import com.buchirano.automation.enums.HomeOfRecord;
import com.buchirano.automation.enums.MaritalStatus;
import com.buchirano.automation.enums.Suffix;
import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Claimant Details screen within the Case Establishment workflow.
 *
 * <p>This class supports entering claimant personal information, address,
 * demographic details, and managing the Urn/Plaque search modal.</p>
 *
 * <p><b>Screen:</b> Case Establishment — Claimant Details</p>
 * <p><b>Layer:</b> Page Object (Application — CaMEO)</p>
 */
public class ClaimantPage extends BasePageClass {

    public final String relationshipToVeteranElementSelector = "//*[@data-id='relationshipToVeteran']//button";
    public final String firstNameElementSelector = "//*[@data-id='decFirstName']//input";
    public final String middleNameElementSelector = "//*[@data-id='decMiddleName']//input";
    public final String lastNameElementSelector = "//*[@data-id='declastName']//input";
    public final String suffixElementSelector = "//*[@data-id='decSuffix']//button";
    public final String ssnElementSelector = "//*[@data-id='decedentSsnShow']//input";
    public final String maritalStatusElementSelector = "//*[@data-id='decMaritalStatus']//button";
    public final String genderElementSelector = "//*[@data-id='decGender']//button";
    public final String birthSexElementSelector = "//*[@data-id='birthSex']//button";
    public final String ethnicityElementSelector = "//*[@data-id='ethnicity']//button";
    public final String selfIdentifiedGenderElementSelector = "//*[@data-id='selfIdentifiedGender']//button";
    public final String selfIdentifiedGenderCommentsElementSelector = "//*[@data-id='selfIdentifiedGenderComments']//input";
    public final String raceElementSelector = "//*[@data-id='lookupInput']//input";
    public final String raceCommentsElementSelector = "//*[@data-id='raceComments']//input";
    public final String dateOfBirthElementSelector = "//*[@data-id='dateOfBirthField']//input";
    public final String dateOfDeathElementSelector = "//*[@data-id='dateOfDeathField']//input";
    public final String addressLineOneElementSelector = "//*[@data-id='decAddressLineOne']//input";
    public final String addressLineTwoElementSelector = "//*[@data-id='decAddressLineTwo']//input";
    public final String cityStateZipCodeCountryOneElementSelector = "//*[@data-id='decZipCode']//input";
    public final String homeOfRecordInServiceAreaElementSelector = "//*[@data-id='decDomesticServiceArea']//div/button";
    public final String foreignHomeOfRecordInServiceAreaElementSelector = "//*[@data-id='decForeignServiceArea']//div/button";
    public final String foreignAddressCity = "decOtherCityShow";
    public final String foreignAddressProvince = "decProvince";
    public final String foreignAddressZip = "decOtherPostalCodeShow";
    public final String foreignAddressCountry = "//*[@data-id='decOtherCountry']";
    public final String foreignAddressCheckbox = "decForeignAddress";
    public final String foreignAddressServiceAreaDropdown = "//*[@data-id='decForeignServiceArea']";
    public final String foreignAddressCountryValues = "//*[@data-id='decOtherCountry']//lightning-base-combobox-item";
    public final String zipDropdownValues = "//*[@role='listbox']//ul";
    public final String searchUrnOrPlaque = "//*[@data-id='searchUrnOrPlaque']";
    public final String searchButtonUrnOrPlaqueModal = "//*[@data-id='searchButton']";
    public final String searchUrnOrPlaqueResultsMessage = "//*[@data-id='resultsMessage']";
    public final String closeXButton = "//*[@data-id='xButton']";
    public final String raceSelectErrorMessage = "//label[.='Declined to Answer is not a valid option when other entries are selected.']";
    public final String requiredField = "//*[.='Complete this field.']";
    public final String selectedRaceValues = "//span[@class='slds-pill__label']";

    public final String cityStateZipCodeCountryDropdownResultElementSelector(String expectedOptionText) {
        return "//li[@role='presentation']//*[@aria-checked='false']//*[contains(text(), '"
                + expectedOptionText + "')]";
    }

    public final String removeSelectedRaceButton(String race) {
        return "//button[@title='Remove " + race + "']";
    }

    // ========================== Element Accessors ==========================

    public AutomatedObject getRelationshipToVeteranDropdown() { scrollIntoView(relationshipToVeteranElementSelector); return getElementByXPath(relationshipToVeteranElementSelector); }
    public AutomatedObject getFirstNameInput() { scrollIntoView(firstNameElementSelector); return getElementByXPath(firstNameElementSelector); }
    public AutomatedObject getMiddleNameInput() { scrollIntoView(middleNameElementSelector); return getElementByXPath(middleNameElementSelector); }
    public AutomatedObject getLastNameInput() { scrollIntoView(lastNameElementSelector); return getElementByXPath(lastNameElementSelector); }
    public AutomatedObject getMaidenNameInput() { scrollIntoView(getAutomatedObjectFromName("Maiden Name")); return getAutomatedObjectFromName("Maiden Name"); }
    public AutomatedObject getSuffixDropdown() { scrollIntoView(suffixElementSelector); return getElementByXPath(suffixElementSelector); }
    public AutomatedObject getSSNInput() { scrollIntoView(ssnElementSelector); return getElementByXPath(ssnElementSelector); }
    public AutomatedObject getGenderDropdown() { scrollIntoView(genderElementSelector); return getElementByXPath(genderElementSelector); }
    public AutomatedObject getMaritalStatusDropdown() { scrollIntoView(maritalStatusElementSelector); return getElementByXPath(maritalStatusElementSelector); }
    public AutomatedObject getDateOfBirthInput() { scrollIntoView(dateOfBirthElementSelector); return getElementByXPath(dateOfBirthElementSelector); }
    public AutomatedObject getDateOfDeathInput() { scrollIntoView(dateOfDeathElementSelector); return getElementByXPath(dateOfDeathElementSelector); }
    public AutomatedObject getAddressLineOneInput() { scrollIntoView(addressLineOneElementSelector); return getElementByXPath(addressLineOneElementSelector); }
    public AutomatedObject getAddressLineTwoInput() { scrollIntoView(addressLineTwoElementSelector); return getElementByXPath(addressLineTwoElementSelector); }
    public AutomatedObject getCityStateZipCodeCountrySearch() { scrollIntoView(cityStateZipCodeCountryOneElementSelector); return getElementByXPath(cityStateZipCodeCountryOneElementSelector); }
    public AutomatedObject getCityStateZipCodeCountryDropdownSearchResult(String expectedOptionText) { scrollIntoView(cityStateZipCodeCountryDropdownResultElementSelector(expectedOptionText)); return getElementByXPath(cityStateZipCodeCountryDropdownResultElementSelector(expectedOptionText)); }
    public AutomatedObject getHomeOfRecordInServiceAreaDropdown() { scrollIntoView(homeOfRecordInServiceAreaElementSelector); return getElementByXPath(homeOfRecordInServiceAreaElementSelector); }
    public AutomatedObject getForeignHomeOfRecordInServiceAreaDropdown() { scrollIntoView(foreignHomeOfRecordInServiceAreaElementSelector); return getElementByXPath(foreignHomeOfRecordInServiceAreaElementSelector); }
    public AutomatedObject getBirthSexInput() { scrollIntoView(birthSexElementSelector); return getElementByXPath(birthSexElementSelector); }
    public AutomatedObject getEthnicityInput() { scrollIntoView(ethnicityElementSelector); return getElementByXPath(ethnicityElementSelector); }
    public AutomatedObject getSelfIdentifiedGenderInput() { scrollIntoView(selfIdentifiedGenderElementSelector); return getElementByXPath(selfIdentifiedGenderElementSelector); }
    public AutomatedObject getSelfIdentifiedGenderCommentsInput() { scrollIntoView(selfIdentifiedGenderCommentsElementSelector); return getElementByXPath(selfIdentifiedGenderCommentsElementSelector); }
    public AutomatedObject getRaceInput() { scrollIntoView(raceElementSelector); return getElementByXPath(raceElementSelector); }
    public AutomatedObject getRaceCommentsInput() { scrollIntoView(raceCommentsElementSelector); return getElementByXPath(raceCommentsElementSelector); }
    public AutomatedObject getForeignAddressCity() { scrollIntoView(getInputXPathByDataId(foreignAddressCity)); return getInputByDataId(foreignAddressCity); }
    public AutomatedObject getForeignAddressProvince() { scrollIntoView(getInputXPathByDataId(foreignAddressProvince)); return getInputByDataId(foreignAddressProvince); }
    public AutomatedObject getForeignAddressZip() { scrollIntoView(getInputXPathByDataId(foreignAddressZip)); return getInputByDataId(foreignAddressZip); }
    public AutomatedObject getForeignAddressCountry() { scrollIntoView(foreignAddressCountry); return getElementByXPath(foreignAddressCountry); }
    public AutomatedObject getForeignAddressCheckbox() { scrollIntoView(getInputXPathByDataId(foreignAddressCheckbox)); return getInputByDataId(foreignAddressCheckbox); }
    public AutomatedObject getForeignAddressServiceAreaDropdown() { scrollIntoView(foreignAddressServiceAreaDropdown); return getElementByXPath(foreignAddressServiceAreaDropdown); }
    public List<AutomatedObject> getForeignAddressCountryValues() { return getElementsByXPath(foreignAddressCountryValues); }
    public AutomatedObject getSearchUrnOrPlaqueButton() { scrollIntoView(searchUrnOrPlaque); return getElementByXPath(searchUrnOrPlaque); }
    public AutomatedObject getSearchButtonUrnOrPlaqueModal() { scrollIntoView(searchButtonUrnOrPlaqueModal); return getElementByXPath(searchButtonUrnOrPlaqueModal); }
    public AutomatedObject getSearchUrnOrPlaqueModalResultsMessage() { scrollIntoView(searchUrnOrPlaqueResultsMessage); return getElementByXPath(searchUrnOrPlaqueResultsMessage); }
    public AutomatedObject getCloseXButton() { scrollIntoView(closeXButton); return getElementByXPath(closeXButton); }
    public AutomatedObject clearContactButton() { scrollIntoView(getButtonXPathByDataId("clearForm")); return getButtonByDataId("clearForm"); }
    public AutomatedObject modifyContactCheckbox() { scrollIntoView(getInputXPathByDataId("modifyContactCheckbox")); return getInputByDataId("modifyContactCheckbox"); }
    public AutomatedObject getRaceSelectErrorMessage() { scrollIntoView(raceSelectErrorMessage); return getElementByXPath(raceSelectErrorMessage); }
    public List<AutomatedObject> getRequiredFieldMessage() { return getElementsByXPath(requiredField); }
    public List<AutomatedObject> getSelectedRaceValues() { return getElementsByXPath(selectedRaceValues); }

    // ========================== Interaction Methods ==========================

    /**
     * Selects the Relationship to Veteran from the dropdown.
     *
     * @param relationshipToVeteran the relationship value to select; skipped if null
     */
    public void selectRelationshipToVeteran(String relationshipToVeteran) {
        if (relationshipToVeteran == null) return;
        waitForVisibility(relationshipToVeteranElementSelector);
        scrollIntoView(relationshipToVeteranElementSelector);
        getRelationshipToVeteranDropdown().click();
        WebElement element = driver.findElement(By.xpath(
                ".//*[@aria-label='Relationship to Veteran']//span[@title = '" + relationshipToVeteran + "']"));
        scrollIntoView(element);
        element.click();
    }

    /**
     * Enters claimant personal information fields.
     *
     * @param firstName     first name
     * @param middleName    middle name
     * @param lastName      last name
     * @param suffix        suffix; skipped if null or "--None--"
     * @param ssn           social security number
     * @param gender        gender dropdown value; skipped if null or empty
     * @param maritalStatus marital status dropdown value; skipped if null or "--None--"
     * @param phone         phone number
     * @param email         email address
     */
    public void enterPersonalInformation(String firstName, String middleName, String lastName,
            String suffix, String ssn, String gender, String maritalStatus,
            String phone, String email) {
        fillInputField(getFirstNameInput(), firstName);
        fillInputField(getMiddleNameInput(), middleName);
        fillInputField(getLastNameInput(), lastName);
        fillInputField(getMaidenNameInput(), "Maiden-Name");
        if (suffix != null && !suffix.equals("--None--")) selectDropdownGeneralOption(getSuffixDropdown(), suffix);
        fillInputField(getSSNInput(), ssn);
        if (gender != null && !gender.isEmpty()) selectDropdownOption(getGenderDropdown(), gender);
        if (maritalStatus != null && !maritalStatus.equals("--None--")) selectDropdownGeneralOption(getMaritalStatusDropdown(), maritalStatus);
        fillInputField(getAutomatedObjectFromName("Phone"), phone);
        fillInputField(getAutomatedObjectFromName("Email"), email);
    }

    /**
     * Enters claimant date of birth and date of death.
     *
     * @param dateOfBirth date of birth string
     * @param dateOfDeath date of death string
     */
    public void enterDates(String dateOfBirth, String dateOfDeath) {
        fillInputField(getDateOfBirthInput(), dateOfBirth);
        fillInputField(getDateOfDeathInput(), dateOfDeath);
    }

    /**
     * Enters only the date of death field.
     *
     * @param dateOfDeath date of death string
     */
    public void enterDateOfDeath(String dateOfDeath) { fillInputField(getDateOfDeathInput(), dateOfDeath); }

    /**
     * Enters claimant demographic fields.
     *
     * @param selfIdentifiedGender         self-identified gender value
     * @param selfIdentifiedGenderComments self-identified gender comments
     * @param ethnicity                    ethnicity value
     * @param race                         race value
     * @param raceComments                 race comments
     */
    public void enterDemographics(String selfIdentifiedGender, String selfIdentifiedGenderComments,
            String ethnicity, String race, String raceComments) {
        if (isPresent(selfIdentifiedGenderElementSelector)) {
            if (selfIdentifiedGender != null) enterSelfIdentifiedGender(selfIdentifiedGender);
            if (selfIdentifiedGenderComments != null) enterSelfIdentifiedGenderComment(selfIdentifiedGenderComments);
        }
        if (ethnicity != null) enterEthnicity(ethnicity);
        if (race != null) { removeSelectedRace(); enterRace(race); }
        if (raceComments != null) enterRaceComment(raceComments);
    }

    public void enterBirthSex(String birthSex) {
        getBirthSexInput().click();
        getElementByXPath(birthSexElementSelector + "/ancestor::lightning-input-field//span[@title = '" + birthSex + "']").click();
    }

    public void enterSelfIdentifiedGender(String selfIdentifiedGender) { selectDropdownGeneralOption(getSelfIdentifiedGenderInput(), selfIdentifiedGender); }
    public void enterSelfIdentifiedGenderComment(String comments) { fillInputField(getSelfIdentifiedGenderCommentsInput(), comments); }
    public void enterEthnicity(String ethnicity) { selectDropdownGeneralOption(getEthnicityInput(), ethnicity); }

    public void enterRace(String race) {
        getRaceInput().sendKeys(race);
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
    }

    public void enterRaceComment(String raceComments) { fillInputField(getRaceCommentsInput(), raceComments); }
    public void inputFirstName(String firstName) { fillInputField(getFirstNameInput(), firstName); }
    public void inputMiddleName(String middleName) { fillInputField(getMiddleNameInput(), middleName); }
    public void inputLastName(String lastName) { fillInputField(getLastNameInput(), lastName); }
    public void inputSSN(String ssn) { fillInputField(getSSNInput(), ssn); }
    public void inputSuffix(Suffix suffix) { selectDropdownGeneralOption(getSuffixDropdown(), suffix.getText()); }
    public String retreiveSuffixValue() { return getElementByXPath("(//*[@data-id='decSuffix']//*[@data-value])[1]").readText(); }
    public void inputGender(Gender gender) { selectDropdownOption(getGenderDropdown(), gender.getText()); }
    public void inputMaritalStatus(MaritalStatus status) { selectDropdownOption(getMaritalStatusDropdown(), status.getText()); }
    public void inputHomeOfRecord(HomeOfRecord homeOfRecord) { selectDropdownOption(getHomeOfRecordInServiceAreaDropdown(), homeOfRecord.getText()); }
    public void inputZipCodeValue(String searchValue) { fillInputField(getCityStateZipCodeCountrySearch(), searchValue); }

    public void selectZipCodeDropdownValue(String searchedValue) {
        waitForVisibility(cityStateZipCodeCountryDropdownResultElementSelector(searchedValue));
        getCityStateZipCodeCountryDropdownSearchResult(searchedValue).click();
        waitForSalesforceLoad();
    }

    public void clearZipCode() { getCityStateZipCodeCountrySearch().clear(); }
    public void zipCodeSearchShowAllValuesClick() { getElementByXPath("//*[@data-value='actionAdvancedSearch']").click(); }
    public void zipCodeSearchModalClose() { getElementByXPath("//*[@title='Close this window']").click(); }
    public void removeSelectedRace(String race) { getElementByXPath(removeSelectedRaceButton(race)).click(); }

    public void removeSelectedRace() {
        String path = "//button[contains(@title, 'Remove')]";
        if (isPresent(path)) getElementByXPath(path).click();
    }

    /**
     * Enters domestic home of record address fields.
     *
     * @param addressLineOne address line one
     * @param addressLineTwo address line two
     * @param zip            zip code to search
     * @param homeOfRecord   home of record value; skipped if null
     */
    public void enterDecedentHomeOfRecord(String addressLineOne, String addressLineTwo,
            String zip, String homeOfRecord) {
        fillInputField(getAddressLineOneInput(), addressLineOne);
        fillInputField(getAddressLineTwoInput(), addressLineTwo);
        if (zip != null) { fillInputField(getCityStateZipCodeCountrySearch(), zip); selectZipCodeDropdownValue(zip); }
        if (homeOfRecord != null) selectDropdownOption(getHomeOfRecordInServiceAreaDropdown(), homeOfRecord);
    }

    /**
     * Enters foreign home of record address fields.
     *
     * @param addressLineOne address line one
     * @param addressLineTwo address line two
     * @param city           foreign city
     * @param province       foreign province
     * @param zipCode        foreign zip code
     * @param country        foreign country; skipped if null or empty
     * @param homeOfRecord   home of record value; skipped if null or empty
     */
    public void enterForeignHomeOfRecord(String addressLineOne, String addressLineTwo,
            String city, String province, String zipCode, String country, String homeOfRecord) {
        fillInputField(getAddressLineOneInput(), addressLineOne);
        fillInputField(getAddressLineTwoInput(), addressLineTwo);
        fillInputField(getForeignAddressCity(), city);
        fillInputField(getForeignAddressProvince(), province);
        fillInputField(getForeignAddressZip(), zipCode);
        if (country != null && !country.isEmpty()) selectForeignAddressCountry(country);
        if (homeOfRecord != null && !homeOfRecord.isEmpty()) selectDropdownOption(getForeignHomeOfRecordInServiceAreaDropdown(), homeOfRecord);
    }

    public void checkUnclaimedRemainsBox() { getElementByXPath("decUnclaimedRemains").click(); }
    public void clickForeignAddressCheckbox() { getForeignAddressCheckbox().click(); waitForSalesforceLoad(); }
    public void clickForeignAddressCountryDropdown() { getForeignAddressCountry().click(); }
    public void selectForeignAddressCountry(String country) { selectDropdownOption(getForeignAddressCountry(), country); }

    /** Clicks the Search Urn or Plaque button, executes a search, and closes the modal. */
    public void clickSearchUrnOrPlaque() {
        getSearchUrnOrPlaqueButton().click();
        getSearchButtonUrnOrPlaqueModal().click();
        getElementByXPath(searchUrnOrPlaqueResultsMessage);
        getCloseXButton().click();
    }

    public void clickModifyContactCheckbox() {
        modifyContactCheckbox().click();
        waitForSalesforceLoad();
        if (!isPresent("//*[@data-id='modifyContactCheckbox' and @checked]")) {
            modifyContactCheckbox().click();
            waitForSalesforceLoad();
        }
    }

    public List<AutomatedObject> listOfAllFields() {
        Boolean isForeign = isCheckboxChecked(getForeignAddressCheckbox());
        if (isForeign) {
            return List.of(getRelationshipToVeteranDropdown(), getFirstNameInput(),
                    getMiddleNameInput(), getLastNameInput(), getGenderDropdown(),
                    getMaritalStatusDropdown(), getDateOfBirthInput(), getAddressLineOneInput(),
                    getAddressLineTwoInput(), getForeignAddressCity(), getForeignAddressProvince(),
                    getForeignAddressZip(), getForeignAddressCountry(),
                    getForeignHomeOfRecordInServiceAreaDropdown(), getEthnicityInput(),
                    getRaceInput(), getRaceCommentsInput(), getBirthSexInput());
        } else {
            return List.of(getRelationshipToVeteranDropdown(), getFirstNameInput(),
                    getMiddleNameInput(), getLastNameInput(), getGenderDropdown(),
                    getMaritalStatusDropdown(), getDateOfBirthInput(), getAddressLineOneInput(),
                    getAddressLineTwoInput(), getCityStateZipCodeCountrySearch(),
                    getHomeOfRecordInServiceAreaDropdown(), getEthnicityInput(),
                    getRaceInput(), getRaceCommentsInput(), getBirthSexInput());
        }
    }
}
