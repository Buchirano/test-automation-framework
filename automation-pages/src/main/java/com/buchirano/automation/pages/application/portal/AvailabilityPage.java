package com.buchirano.automation.pages.application.portal;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.buchirano.automation.enums.AvailabilityDays;
import com.buchirano.automation.enums.AvailabilityEditDropdown;
import com.buchirano.automation.enums.Honors;
import com.buchirano.automation.enums.Impact;
import com.buchirano.automation.enums.RemainsType;
import com.buchirano.automation.enums.ServiceActivityType;
import com.buchirano.automation.enums.ServiceInterval;
import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Availability tab within the ResourcePortal resource management screen.
 *
 * <p>This class manages the full lifecycle of availability records — creating,
 * editing, deleting, searching, and validating rows in the availability table.
 * It delegates modal interactions to {@link ModalClass} for clean separation
 * of concerns.</p>
 *
 * <p><b>Application:</b> ResourcePortal Resource Management</p>
 * <p><b>Screen:</b> Cemetery Details — Availability Tab</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class AvailabilityPage extends LightningBasePageClass {

    // ========================== XPath Constants ==========================

    private final String availabilityTab = "//a[@data-label='Availability']";
    private final String editFirstAvailability = "(//th[@role='rowheader']//child::button)[1]";
    private final String editAvailability = "//th[@role='rowheader']//child::button";
    private final String deleteAvailability = "(//*[.='" + AvailabilityEditDropdown.DELETE.getText() + "'])[1]";
    private final String firstServiceStart = "//*[@data-id='firstServiceStartTime']//child::lightning-base-combobox//child::input";
    private final String lastServiceStart = "//*[@data-id='lastServiceStartTime']//child::lightning-base-combobox//child::input";
    private final String availabilityRow = "//th[@role='rowheader']//child::button";
    private final String newAvailability = "//lightning-button[@data-id='new-availability']";
    private final String cloneDay = "//lightning-button[@data-id='cloneDay']";
    private final String cloneDayInput = "//*[@data-id='dayOfWeekLookup']//descendant::input";
    private final String descriptionColumn = "//span[@title='Description']";
    private final String firstServiceStartTimeColumn = "//span[@title='First Service Start Time']";
    private final String lastServiceStartTimeColumn = "//span[@title='Last Service Start Time']";
    private final String impactColumn = "//span[@title='Impact']";
    private final String capacityColumn = "//span[@title='Capacity']";
    private final String intervalColumn = "//span[@title='Interval']";
    private final String lastRevisionColumn = "//span[@title='Last Revision']";
    private final String lastModifiedByEmailAddressColumn = "//span[@title='Last Modified By Email Address']";
    private final String descriptionValue = "//td[@data-label='Description']";
    private final String firstServiceStartValue = "//td[@data-label='First Service Start Time']";
    private final String lastServiceStartValue = "//td[@data-label='Last Service Start Time']";
    private final String impactValue = "//td[@data-label='Impact']";
    private final String capacityValue = "//td[@data-label='Capacity']";
    private final String intervalValue = "//td[@data-label='Interval']";
    private final String remainsTypeValue = "//td[@data-label='Remains Type']";
    private final String serviceActivityValue = "//td[@data-label='Service Activity Type']";
    private final String locationTypeValue = "//td[@data-label='Location Type']";
    private final String honorsValue = "//td[@data-label='Honors']";
    private final String lastRevision = "//td[@data-label='Last Revision']";
    private final String modifiedEmail = "//td[@data-label='Last Modified By Email Address']";
    private final String availabilitySearch = "//input[@name='enter-search']";
    public String dayAvailabilityTitle = "//span[@class='slds-page-header__title slds-truncate']";

    // ========================== Element Accessors ==========================

    public AutomatedObject getAvailabilityTab() { return getElementByXPath(availabilityTab); }
    public AutomatedObject getFirstEditAvailability() { return getElementByXPath(editFirstAvailability); }
    public AutomatedObject getSpecificEditAvailability(int index) { return getElementsByXPath(editAvailability).get(index); }
    public AutomatedObject getFirstServiceStartTime() { return getElementByXPath(firstServiceStart); }
    public AutomatedObject getLastServiceStartTime() { return getElementByXPath(lastServiceStart); }

    public WebElement getFirstServiceStartTimeValue(String time) {
        List<WebElement> elements = driver.findElements(By.xpath("//lightning-base-combobox-item[.='" + time + "']"));
        scrollIntoView(elements.get(0));
        return elements.get(0);
    }

    public WebElement getLastServiceStartTimeValue(String time) {
        List<WebElement> elements = driver.findElements(By.xpath("//lightning-base-combobox-item[.='" + time + "']"));
        scrollIntoView(elements.get(1));
        return elements.get(1);
    }

    public AutomatedObject getDay(String day) { return getElementByXPath("//a[@data-label='" + day + "']"); }
    public AutomatedObject getNewAvailabilityButton() { return getElementByXPath(newAvailability); }

    public ArrayList<AutomatedObject> getAvailabilityRows() {
        Search search = new Search();
        search.addCriteria("XPath", availabilityRow);
        return getObjects(search);
    }

    public AutomatedObject getCloneDay() { return getElementByXPath(cloneDay); }
    public AutomatedObject getCloneDayInput() { return getElementByXPath(cloneDayInput); }
    public AutomatedObject getDescriptionColumn() { return getElementByXPath(descriptionColumn); }
    public AutomatedObject getFirstServiceStartTimeColumn() { return getElementByXPath(firstServiceStartTimeColumn); }
    public AutomatedObject getLastServiceStartTimeColumn() { return getElementByXPath(lastServiceStartTimeColumn); }
    public AutomatedObject getImpactColumn() { return getElementByXPath(impactColumn); }
    public AutomatedObject getCapacityColumn() { return getElementByXPath(capacityColumn); }
    public AutomatedObject getIntervalColumn() { return getElementByXPath(intervalColumn); }
    public AutomatedObject getLastRevisionColumn() { return getElementByXPath(lastRevisionColumn); }
    public AutomatedObject getLastModifiedByEmailAddressColumn() { return getElementByXPath(lastModifiedByEmailAddressColumn); }
    public AutomatedObject getDescriptionValue(int index) { return getElementsByXPath(descriptionValue).get(index); }
    public AutomatedObject getFirstServiceStartValue(int index) { return getElementsByXPath(firstServiceStartValue).get(index); }
    public AutomatedObject getLastServiceStartValue(int index) { return getElementsByXPath(lastServiceStartValue).get(index); }
    public AutomatedObject getImpactValue(int index) { return getElementsByXPath(impactValue).get(index); }
    public AutomatedObject getCapacityValue(int index) { return getElementsByXPath(capacityValue).get(index); }
    public AutomatedObject getIntervalValue(int index) { return getElementsByXPath(intervalValue).get(index); }
    public AutomatedObject getRemainsTypeValue(int index) { return getElementsByXPath(remainsTypeValue).get(index); }
    public AutomatedObject getServiceActivityValue(int index) { return getElementsByXPath(serviceActivityValue).get(index); }
    public AutomatedObject getLocationTypeValue(int index) { return getElementsByXPath(locationTypeValue).get(index); }
    public AutomatedObject getHonorsValue(int index) { return getElementsByXPath(honorsValue).get(index); }
    public AutomatedObject getLastRevisionValue(int index) { return getElementsByXPath(lastRevision).get(index); }
    public AutomatedObject getModifiedEmailValue(int index) { return getElementsByXPath(modifiedEmail).get(index); }
    public AutomatedObject getAvailabilitySearch() { return getElementByXPath(availabilitySearch); }
    public AutomatedObject getDayAvailabilityTitle() { return getElementByXPath(dayAvailabilityTitle); }

    // ========================== Interaction Methods ==========================

    private final ModalClass modal = new ModalClass();

    public void clickAvailabilityTab() { getAvailabilityTab().click(); waitForSalesforceLoad(); }
    public void clickFirstEditAvailability() { getFirstEditAvailability().click(); waitForSalesforceLoad(); }
    public void clickSpecificEditAvailability(int index) { getSpecificEditAvailability(index).click(); waitForSalesforceLoad(); }
    public void selectEditAvailability() { getElementByXPath("(//*[.='" + AvailabilityEditDropdown.EDIT.getText() + "'])[1]").click(); waitForSalesforceLoad(); }
    public void selectDeleteAvailability() { scrollIntoView(deleteAvailability); getElementByXPath(deleteAvailability).click(); waitForSalesforceLoad(); }
    public void cancelDeleteAvailability() { getButtonByTitle("No").click(); }
    public void acceptDeleteAvailability() { getElementByXPath("//button[contains(@title,'Yes')]").click(); waitForSalesforceLoad(); }

    public Integer selectDeleteAvailabilityAndCancel() {
        createNewGenericAvailabilityIfEmpty();
        Integer startingAvailability = getNumberOfRows();
        clickFirstEditAvailability();
        selectDeleteAvailability();
        cancelDeleteAvailability();
        return startingAvailability;
    }

    public void inputFirstServiceStart(String startTime) { getFirstServiceStartTime().click(); getFirstServiceStartTimeValue(startTime).click(); }
    public void inputLastServiceStart(String startTime) { getLastServiceStartTime().click(); getLastServiceStartTimeValue(startTime).click(); }
    public void selectDay(AvailabilityDays day) { getDay(day.getText()).click(); waitForSalesforceLoad(); }
    public void clickCloneDayInput() { getCloneDayInput().click(); waitForSalesforceLoad(); }

    public void selectCloneDay(AvailabilityDays day) {
        clickCloneDayInput();
        getElementByInnerText("span", day.getText()).click();
        waitForSalesforceLoad();
    }

    public void clickNewAvailability() { getNewAvailabilityButton().click(); waitForSalesforceLoad(); }
    public void clickCloneDay() { getCloneDay().click(); waitForSalesforceLoad(); }
    public int getNumberOfRows() { return getAvailabilityRows().size(); }

    public void deleteAllRows() {
        if (isAvailabilityPresent().equals("true")) {
            int rowCount = getNumberOfRows();
            for (int i = 0; i < rowCount; i++) {
                clickFirstEditAvailability();
                selectDeleteAvailability();
                waitForSalesforceLoad();
                acceptDeleteAvailability();
                waitForSalesforceLoad();
            }
        }
    }

    public void searchAvailability(String searchText) {
        getAvailabilitySearch().clear();
        getAvailabilitySearch().sendKeys(searchText);
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
    }

    public void clickOk() { getElementByInnerText("button", "Ok").click(); waitForSalesforceLoad(); }
    public void clickDescriptionHeader() { getElementByInnerText("span", "Description").click(); waitForSalesforceLoad(); }
    public void clickFirstServiceStartHeader() { getElementByInnerText("span", "First Service Start Time").click(); waitForSalesforceLoad(); }
    public void clickLastServiceStartHeader() { getElementByInnerText("span", "Last Service Start Time").click(); waitForSalesforceLoad(); }
    public void clickImpactHeader() { getElementByInnerText("span", "Impact").click(); waitForSalesforceLoad(); }
    public void clickCapacityHeader() { getElementByInnerText("span", "Capacity").click(); waitForSalesforceLoad(); }
    public void clickIntervalHeader() { getElementByInnerText("span", "Interval").click(); waitForSalesforceLoad(); }
    public void clickLastRevisionHeader() { getElementByXPath("(.//span[(text()='Last Revision')])[2]").click(); waitForSalesforceLoad(); }
    public void clickLastModifiedByEmailAddressHeader() { getElementByInnerText("span", "Last Modified By Email Address").click(); waitForSalesforceLoad(); }
    public String getFieldText(String text) { return getElementByXPath(".//*[contains(text(), '" + text + "')]").readText(); }

    public String getRowFieldText(Integer row, String text) {
        Integer rowIndex = row + 1;
        return getElementByXPath("(//tr[@class='slds-hint-parent'])[" + rowIndex + "]//*[contains(text(), '" + text + "')]").readText();
    }

    public String getTableFieldText(String column, String row) {
        return getElementByXPath("(//td[" + column + "])[" + row + "]").readText();
    }

    public void createNewAvailability(String description, Impact impact, ServiceInterval serviceInterval,
            String firstStartTime, String lastStartTime, RemainsType remainsType,
            ServiceActivityType serviceActivityType, String locationType, Honors honors) {
        clickNewAvailability();
        modal.inputDescription(description);
        modal.inputImpact(impact);
        if (impact != Impact.CLOSED) modal.inputCapacityText("2");
        if (impact != Impact.CAPACITY) modal.action.sendKeys(Keys.TAB.toString().repeat(2)).perform();
        modal.action.sendKeys(Keys.TAB.toString().repeat(2)).perform();
        inputFirstServiceStart(firstStartTime);
        inputLastServiceStart(lastStartTime);
        modal.action.sendKeys(Keys.TAB).perform();
        modal.inputServiceInterval(serviceInterval);
        if (impact != Impact.CLOSED) {
            modal.action.sendKeys(Keys.TAB.toString().repeat(4)).perform();
            modal.inputRemainsType(remainsType);
            modal.inputServiceActivityType(serviceActivityType);
            modal.inputLocationType(locationType);
            modal.inputHonors(honors);
        }
        modal.clickSaveButton();
    }

    public void createNewGenericAvailabilityIfEmpty() {
        if (isAvailabilityPresent().equals("false")) {
            createNewAvailability("General Entry", Impact.CAPACITY, ServiceInterval.THIRTY,
                    "7:30 AM", "11:30 AM", RemainsType.CASKET,
                    ServiceActivityType.INTERMENT_FIRST_CASKET, "Ossuary", Honors.HONORS);
        }
    }

    public String isAvailabilityPresent() {
        Search search = getSearch();
        search.addCriteria("xpath", availabilityRow);
        return Boolean.toString(isObjectPresent(search));
    }
}
