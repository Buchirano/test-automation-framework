package com.buchirano.automation.pages.application.cameo;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Organizations Details screen within the Case Establishment workflow.
 *
 * <p><b>Screen:</b> Case Establishment — Organizations Details</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class OrganizationsPage extends BasePageClass {

    public final String contactSelectCheckbox = "//*[@data-id='selected']//span[@class='slds-checkbox_faux']";
    public final String contactSelectFirstCheckbox = "(//*[@data-id='selected']//span[@class='slds-checkbox_faux'])[1]";
    public final String addOrChangeContactButton = "addOrganizationsContactRow";
    public final String searchOrganizationsButton = "searchOrgs";
    public final String zipDropdownValues = "//*[@role='listbox']//ul";

    private final OrganizationsSearchModal organizationsSearch = new OrganizationsSearchModal();

    public AutomatedObject getOrganizationName() { String path = "//*[@data-id='accountNameShown']//input"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject getOrganizationId() { scrollIntoView(getInputByDataId("orgId")); return getInputByDataId("orgId"); }
    public AutomatedObject getOrganizationAddressLineOne() { scrollIntoView(getInputByDataId("funeralAddressLineOne")); return getInputByDataId("funeralAddressLineOne"); }
    public AutomatedObject getOrganizationAddressLineTwo() { scrollIntoView(getInputByDataId("funeralAddressLineTwo")); return getInputByDataId("funeralAddressLineTwo"); }
    public AutomatedObject getOrganizationPhone() { String path = "(//*[@data-id='phoneNumber']//input)[1]"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject getOrganizationFax() { scrollIntoView(getInputByDataId("fax")); return getInputByDataId("fax"); }
    public AutomatedObject getFuneralZipCode() { scrollIntoView(getInputByDataId("funeralZipCode")); return getInputByDataId("funeralZipCode"); }
    public List<AutomatedObject> getContactFirstName() { return getInputsByDataId("firstName"); }
    public List<AutomatedObject> getContactLastName() { return getInputsByDataId("lastName"); }
    public List<AutomatedObject> getContactEmail() { return getInputsByDataId("emailShow"); }
    public AutomatedObject getContactPhoneNumber() { return getElementByXPath("(//*[@data-id='phoneNumber'])[2]"); }
    public AutomatedObject getAddOrChangeContactButton() { scrollIntoView(getButtonXPathByDataId("addContactinfo")); return getButtonByDataId("addContactinfo"); }
    public AutomatedObject getCreateOrganizationButton() { scrollIntoView("//button[@title='Create Organization']"); return getButtonByTitle("Create Organization"); }
    public List<AutomatedObject> getContactSelectCheckbox() { return getElementsByXPath(contactSelectCheckbox); }
    public AutomatedObject getSearchOrganizationsButton() { scrollIntoView(getButtonXPathByDataId(searchOrganizationsButton)); return getButtonByDataId(searchOrganizationsButton); }
    public AutomatedObject getDeleteRowButton(int rowNumber) { String path = "(//button[@title='Delete row'])[" + rowNumber + "]"; scrollIntoView(path); return getElementByXPath(path); }
    public String getNoRecordsFoundXpath() { return "//*[@data-id='resultsMessage']//h2"; }
    public AutomatedObject getZipLookupValues() { scrollIntoView(zipDropdownValues); return getElementByXPath(zipDropdownValues); }
    public AutomatedObject getZipLookupNoValues() { String path = "//*[@data-value='actionAdvancedSearch']"; scrollIntoView(path); return getElementByXPath(path); }
    public List<AutomatedObject> getGridRow(String searchText) { return getElementsByXPath("//td[.='" + searchText + "']/../td"); }
    public AutomatedObject getZipLookupModalXButton() { String path = "//*[@title='Close this window']"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject getGridNoResults() { String path = ".//div[@class='slds-text-heading--large noResultsTitle slds-p-bottom--large']"; scrollIntoView(path); return getElementByXPath(path); }

    public void enterOrganizationFields(String name, String phone, String fax,
            String addressLineOne, String addressLineTwo) {
        if (StringUtils.isNotBlank(name)) getOrganizationName().sendKeys(name);
        if (StringUtils.isNotBlank(addressLineOne)) getOrganizationAddressLineOne().sendKeys(addressLineOne);
        if (StringUtils.isNotBlank(addressLineTwo)) getOrganizationAddressLineTwo().sendKeys(addressLineTwo);
        if (StringUtils.isNotBlank(phone)) getOrganizationPhone().sendKeys(phone);
        if (StringUtils.isNotBlank(fax)) getOrganizationFax().sendKeys(fax);
        getFuneralZipCode().sendKeys("34653");
        String zipResultXpath = getElementXPathByTitle("New Port Richey, FL 34653 Pasco US");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(zipResultXpath)));
        action.sendKeys(Keys.ENTER).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(zipResultXpath))).click();
    }

    public void createNewContact(String firstName, String lastName, String email, String phoneNumber) {
        getAddOrChangeContactButton().click();
        waitForSalesforceLoad();
        List<AutomatedObject> firstNameFields = getContactFirstName();
        int idx = firstNameFields.size() - 1;
        String currentValue = firstNameFields.get(idx).getPropertyValue("value");
        assertTrue("New contact fields were not empty", currentValue.isEmpty());
        if (firstName != null) getContactFirstName().get(idx).sendKeys(firstName);
        if (lastName != null) getContactLastName().get(idx).sendKeys(lastName);
        if (email != null) getContactEmail().get(idx).sendKeys(email);
        if (phoneNumber != null) getContactPhoneNumber().sendKeys(phoneNumber);
    }

    public void selectNewContact() { getElementByXPath(contactSelectFirstCheckbox).click(); getButtonByTitle("Select").click(); waitForSalesforceLoad(); }

    public void selectNewContactWithEmail() {
        List<AutomatedObject> emailFields = getContactEmail();
        int indexToSelect = 0;
        for (int i = 0; i < emailFields.size(); i++) {
            if (isValidEmailAddress(emailFields.get(i).readText())) { indexToSelect = i; break; }
        }
        getContactSelectCheckbox().get(indexToSelect).click();
    }

    public void openSearchOrganizationsModal() { getSearchOrganizationsButton().click(); waitForSalesforceLoad(); }

    public void enterOrganizationSearchValues(String organizationType, String organizationName, String organizationId) {
        if (!StringUtils.isBlank(organizationType)) {
            organizationsSearch.getOrganizationTypeDropdownField().click();
            WebElement element = driver.findElement(By.xpath(".//*[@aria-label='Type']//span[@title = '" + organizationType + "']"));
            scrollIntoView(element);
            element.click();
        }
        if (!StringUtils.isBlank(organizationId)) organizationsSearch.getOrganizationIdField().sendKeys(organizationId);
        if (!StringUtils.isBlank(organizationName)) organizationsSearch.getOrganizationNameField().sendKeys(organizationName);
        waitForSalesforceLoad();
    }

    public void clearOrganizationsSearchFields() { organizationsSearch.getOrganizationIdField().clear(); organizationsSearch.getOrganizationNameField().clear(); }
    public String getOrganizationPhoneFromSearch(int index) { return organizationsSearch.getOrganizationPhoneResults().get(index).readText(); }
    public void enterSearchValue(String searchValue) { fillInputField(getFuneralZipCode(), searchValue); }

    public static boolean isValidEmailAddress(String email) {
        if (email == null) return false;
        return Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
    }

    public List<AutomatedObject> listOfAllFields() { return List.of(getOrganizationName(), getOrganizationId(), getOrganizationAddressLineOne(), getOrganizationPhone(), getFuneralZipCode()); }
    public List<AutomatedObject> listOfAllContactFields(int contactRow) { int idx = contactRow - 1; return List.of(getContactFirstName().get(idx), getContactLastName().get(idx), getContactEmail().get(idx), getContactPhoneNumber()); }
}
