package com.buchirano.automation.pages.application.cerrt;

import java.util.List;

import org.openqa.selenium.Keys;

import com.buchirano.automation.enums.Honors;
import com.buchirano.automation.enums.Impact;
import com.buchirano.automation.enums.RemainsType;
import com.buchirano.automation.enums.ServiceActivityType;
import com.buchirano.automation.enums.ServiceInterval;
import com.buchirano.automation.enums.TimeslotType;
import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the shared Availability and Schedule modal within the PortalRM application.
 *
 * <p><b>Application:</b> PortalRM Resource Management</p>
 * <p><b>Layer:</b> Page Object (Application — Modal)</p>
 */
public class ModalClass extends BasePageClass {

    private final String timeslotType = "//*[@data-id='timeslotType']//child::lightning-base-combobox//child::button";
    private final String description = "//*[@data-id='description']//child::textarea";
    private final String impact = "//*[@data-id='changes']//child::lightning-base-combobox//child::button";
    private final String capacity = "//*[@data-id='capacity']//child::input";
    private final String serviceInterval = "//*[@data-id='serviceInterval']//child::lightning-base-combobox//child::button";
    private final String cancelButton = "//button[@title='Cancel']";
    private final String saveButton = "//button[@title='Save']";
    private final String requiredFieldsError = "//*[text()='Complete this field.']";
    private final String cemeteryServiceRequiredFields = "//*[.='You must select at least one choice from this set.']";

    public AutomatedObject getTimeslotType() { return getElementByXPath(timeslotType); }
    public AutomatedObject getDescription() { return getElementByXPath(description); }
    public AutomatedObject getImpact() { return getElementByXPath(impact); }
    public AutomatedObject getCapacity() { return getElementByXPath(capacity); }
    public AutomatedObject getServiceInterval() { return getElementByXPath(serviceInterval); }
    public AutomatedObject getServiceOption(String serviceOption) { return getElementByXPath("//label//span[(text()='" + serviceOption + "')]"); }
    public AutomatedObject getHonorsOption(String honorsOption) { return getElementByXPath("//label//span[(text()='" + honorsOption + "')]"); }
    public AutomatedObject getCancelButton() { return getElementByXPath(cancelButton); }
    public AutomatedObject getSaveButton() { return getElementByXPath(saveButton); }
    public List<AutomatedObject> getRequiredFields() { return getElementsByXPath(requiredFieldsError); }
    public List<AutomatedObject> getCemeteryServiceRequiredFields() { return getElementsByXPath(cemeteryServiceRequiredFields); }

    public void inputRemainsType(RemainsType remainsType) { getServiceOption(remainsType.getText()).click(); }
    public void inputServiceActivityType(ServiceActivityType serviceActivityType) { getServiceOption(serviceActivityType.getText()).click(); }
    public void inputLocationType(String locationType) { getServiceOption(locationType).click(); }
    public void inputHonors(Honors honors) { getHonorsOption(honors.getText()).click(); }

    public void inputTimeslotType(TimeslotType timeslotType) {
        getTimeslotType().click();
        getElementByXPath("//*[@data-id= 'timeslotType']//..//*[@title='" + timeslotType.getText() + "']").click();
    }

    public void inputDescription(String description) { getDescription().clear(); getDescription().sendKeys(description); }

    public void inputImpact(Impact impact) {
        getImpact().click();
        waitForSalesforceLoad();
        getElementByXPath("//*[@data-id= 'changes']//..//*[@title='" + impact.getText() + "']").click();
    }

    public void inputCapacityText(String capacity) { getCapacity().clear(); getCapacity().sendKeys(capacity); }

    public void inputServiceInterval(ServiceInterval serviceInterval) {
        getServiceInterval().click();
        getElementByXPath("//*[@title='" + serviceInterval.getText() + "']").click();
    }

    public void inputDifferentServiceInterval() {
        if (ServiceInterval.FIFTEEN.getText().equals(getServiceInterval().readText())) {
            inputServiceInterval(ServiceInterval.THIRTY);
        } else {
            inputServiceInterval(ServiceInterval.FIFTEEN);
        }
    }

    public void clickCancelButton() { getCancelButton().click(); }
    public void clickSaveButton() { getSaveButton().click(); waitForSalesforceLoad(); }
    public int getNumberOfRequiredFields() { return getRequiredFields().size(); }
    public int getNumberOfServiceRequiredFields() { return getCemeteryServiceRequiredFields().size(); }

    public boolean waitForBannerError() {
        Search search = getSearch("xpath", "//*[@data-id='ErrorMessageBanner']");
        return isObjectPresent(search);
    }

    public void createNewTimeslot(TimeslotType timeslotType, String description, Impact impact,
            String startTime, String endTime, String locationType) {
        CeRRTGeneralNavigationPage navigation = new CeRRTGeneralNavigationPage();
        SchedulePage schedule = new SchedulePage();
        inputTimeslotType(timeslotType);
        inputDescription(description);
        inputImpact(impact);
        if (impact != Impact.CLOSED) inputCapacityText("2");
        if (impact != Impact.CAPACITY) navigation.action.sendKeys(Keys.TAB.toString().repeat(2)).perform();
        navigation.action.sendKeys(Keys.TAB).perform();
        navigation.action.sendKeys(Keys.TAB).perform();
        schedule.inputCustomTimeslotStartTime(startTime);
        schedule.inputCustomTimeslotEndTime(endTime);
        navigation.action.sendKeys(Keys.TAB).perform();
        if (impact != Impact.CLOSED) {
            navigation.action.sendKeys(Keys.TAB).perform();
            navigation.action.sendKeys(Keys.TAB).perform();
            inputRemainsType(RemainsType.CASKET);
            inputServiceActivityType(ServiceActivityType.INTERMENT_FIRST_CASKET);
            inputLocationType(locationType);
            inputHonors(Honors.HONORS);
        }
        clickSaveButton();
        waitForSalesforceLoad();
    }
}
