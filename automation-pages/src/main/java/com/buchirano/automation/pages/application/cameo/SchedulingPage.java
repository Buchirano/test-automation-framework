package com.buchirano.automation.pages.application.cameo;

import java.time.DayOfWeek;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class for the Scheduling Details screen within CaMEO Case Establishment.
 *
 * <p><b>Screen:</b> Case Establishment — Scheduling Details</p>
 * <p><b>Layer:</b> Page Object (Application — CaMEO)</p>
 */
public class SchedulingPage extends BasePageClass {

    public final String dailyScheduleButton = getButtonXPathByDataId("dailySchedule");
    public final String refreshCemeteryButton = getButtonXPathByDataId("refreshCemetery");
    public final String modalConfirmButton = getButtonXPathByDataId("yesButton");
    public final String specialGuidance = "//div[@class='specialGuidance']//span[@class='uiOutputText']";
    public final String dailyScheduleContainer = "//*[@data-id='dayContainer']";
    public String availabilityDateSelector = "//*[@data-id='requestedDate']//input";
    public String servicesScheduled = "//span[.='Services Scheduled']//following-sibling::div";
    public String summaryDescription = "//span[.='Description']//following-sibling::div";
    public String summaryCapacity = "//span[.='Capacity']//following-sibling::div";
    public String summaryServiceInterval = "//span[.='Service Interval']//following-sibling::div";
    public String remainsType = "//span[.='Remains Type']//following-sibling::div/lightning-formatted-text";
    public String serviceActivityType = "//span[.='Service Activity Type']//following-sibling::div/lightning-formatted-text";
    public String locationType = "//span[.='Location Type']//following-sibling::div/lightning-formatted-text";
    public String honors = "//span[.='Honors']//following-sibling::div/lightning-formatted-text";
    public final String remainsTypeSummary = "//*[@data-id='timeslotRemainsType']";
    public final String serviceActivityTypeSummary = "//*[@data-id='timeslotActivityType']";
    public final String locationTypeSummary = "//*[@data-id='timeslotLocationType']";
    public final String honorsSummary = "//*[@data-id='timeslotHonors']";
    private final String summaryDate = "//*[@data-id='timeslotDates']";
    private final String summaryTime = "//*[@data-id='timeslotTimes']";

    private final CaMEOGeneralNavigationPage navigation = new CaMEOGeneralNavigationPage();

    public AutomatedObject getSummaryDate() { scrollIntoView(summaryDate); return getElementByXPath(summaryDate); }
    public AutomatedObject getSummaryTime() { scrollIntoView(summaryTime); return getElementByXPath(summaryTime); }
    public AutomatedObject getDailyScheduleButton() { scrollIntoView(dailyScheduleButton); return getElementByXPath(dailyScheduleButton); }
    public AutomatedObject getRefreshCemeteryButton() { scrollIntoView(refreshCemeteryButton); return getElementByXPath(refreshCemeteryButton); }
    public AutomatedObject getSpecialGuidance() { scrollIntoView(specialGuidance); return getElementByXPath(specialGuidance); }
    public AutomatedObject getServicesScheduled() { scrollIntoView(servicesScheduled); return getElementByXPath(servicesScheduled); }
    public AutomatedObject getSummaryDescription() { scrollIntoView(summaryDescription); return getElementByXPath(summaryDescription); }
    public AutomatedObject getSummaryCapacity() { scrollIntoView(summaryCapacity); return getElementByXPath(summaryCapacity); }
    public AutomatedObject getSummaryServiceInterval() { scrollIntoView(summaryServiceInterval); return getElementByXPath(summaryServiceInterval); }
    public AutomatedObject getRemainsType() { scrollIntoView(remainsType); return getElementByXPath(remainsType); }
    public AutomatedObject getServiceActivityType() { scrollIntoView(serviceActivityType); return getElementByXPath(serviceActivityType); }
    public AutomatedObject getLocationType() { scrollIntoView(locationType); return getElementByXPath(locationType); }
    public AutomatedObject getHonors() { scrollIntoView(honors); return getElementByXPath(honors); }
    public AutomatedObject getRemainsTypeSummary() { scrollIntoView(remainsTypeSummary); return getElementByXPath(remainsTypeSummary); }
    public AutomatedObject getServiceActivityTypeSummary() { scrollIntoView(serviceActivityTypeSummary); return getElementByXPath(serviceActivityTypeSummary); }
    public AutomatedObject getLocationTypeSummary() { scrollIntoView(locationTypeSummary); return getElementByXPath(locationTypeSummary); }
    public AutomatedObject getHonorsSummary() { scrollIntoView(honorsSummary); return getElementByXPath(honorsSummary); }

    public String getTimeslotXpath(String dayOfTheWeek, String time) {
        int dayOfWeek = DayOfWeek.valueOf(dayOfTheWeek.toUpperCase()).getValue() + 1;
        if (dayOfTheWeek.equalsIgnoreCase("SUNDAY")) dayOfWeek = 1;
        return "((//*[contains(text(),'" + time + "')])[" + dayOfWeek + "]//..)[1]";
    }

    public AutomatedObject getTimeslot(String dayOfTheWeek, String time) {
        String path = getTimeslotXpath(dayOfTheWeek, time);
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    public boolean verifyDailySchedule(String cemetery) {
        String path = "//slot[@name='main']//div[text()='" + cemetery + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
        return getElementByXPath(path).isDisplayed();
    }

    public void clickDailyScheduleButton() {
        waitForButtonToBeEnabled(getDailyScheduleButton());
        int startingWindowCount = new ArrayList<>(driver.getWindowHandles()).size();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dailyScheduleButton))).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(startingWindowCount + 1));
        openSecondTab();
    }

    public void refreshCemetery() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(refreshCemeteryButton)));
        getRefreshCemeteryButton().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dailyScheduleContainer)));
    }

    public boolean selectTimeSlot(String dayOfTheWeek, String time) {
        String path = getTimeslotXpath(dayOfTheWeek, time);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
        scrollIntoView(path);
        getTimeslot(dayOfTheWeek, time).click();
        waitForSalesforceLoad();
        return getTimeslot(dayOfTheWeek, time).getPropertyValue("title").contains("Unavailable");
    }

    public void clickConfirmModalYes() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(modalConfirmButton))).click();
    }

    public void selectShowAvailabilityStartingFromDate(String date) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(availabilityDateSelector))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(availabilityDateSelector))).sendKeys(date);
        action.sendKeys(Keys.ENTER).perform();
    }

    public void clickNextButton(boolean selectedTimeslotUnavailable) {
        scrollIntoView(navigation.nextButton);
        waitForSalesforceLoad();
        getElementByXPath(navigation.nextButton).click();
        if (selectedTimeslotUnavailable) clickConfirmModalYes();
        waitForSalesforceLoad();
    }
}
