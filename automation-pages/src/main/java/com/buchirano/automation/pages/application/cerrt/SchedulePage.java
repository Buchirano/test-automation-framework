package com.buchirano.automation.pages.application.cerrt;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.enums.AvailabilityDays;
import com.buchirano.automation.enums.Occurrence;
import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Schedule screen within the PortalRM resource management record.
 *
 * <p><b>Application:</b> PortalRM Resource Management</p>
 * <p><b>Screen:</b> Cemetery Details — Schedule Tab</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class SchedulePage extends LightningBasePageClass {

    private final String dateSearchBox = "//label[.='Jump to Week']//following-sibling::div//child::input";
    private final String invalidDateMessage = "//*[.='Cemetery schedule cannot be viewed in the past.']";
    private final String selectedTimeSlot = "//span[contains(text(),'- Selected')]//parent::*//parent::*";
    private final String editTimeSlot = "//button[@title='Edit Custom Timeslot']";
    private final String deleteTimeSlot = "//button[@title='Delete Custom Timeslot']";
    private final String multiTimeslotCheckbox = "//*[@data-id='acrossMultTimeslots']//child::input";
    private final String occurrence = "//*[@data-id='recurrenceType']//child::lightning-base-combobox";
    private final String recurrenceInterval = "//*[@data-id='recurrenceInterval']//child::button";
    private final String startTime = "//*[@data-id='startTime']//child::lightning-base-combobox//child::input";
    private final String endTime = "//*[@data-id='endTime']//child::lightning-base-combobox//child::input";
    private final String editSeries = "//input[@value='2']";
    private final String deleteSeries = "//label[span='Series']";
    private final String timeslotEndDate = "//lightning-input[@data-id='endDate']//child::input";
    private final String summaryDate = "//*[@data-id='timeslotDates']";
    private final String time = "//*[@data-id='timeslotTimes']";
    private final String description = "//div[@data-id='timeslotDescription']";
    private final String capacity = "//div[@data-id='timeslotCapacity']";
    private final String serviceInterval = "//div[@data-id='timeslotInterval']";
    private final String remainsType = "//div[@data-id='timeslotRemainsType']";
    private final String serviceActivityType = "//div[@data-id='timeslotActivityType']";
    private final String locationType = "//div[@data-id='timeslotLocationType']";
    private final String honors = "//div[@data-id='timeslotHonors']";

    private final CeRRTGeneralNavigationPage navigation = new CeRRTGeneralNavigationPage();
    private final LocalDate today = LocalDate.now();

    public AutomatedObject getDateSearchBox() { return getElementByXPath(dateSearchBox); }
    public AutomatedObject getInvalidDateMessage() { return getElementByXPath(invalidDateMessage); }

    public AutomatedObject getFirstTimeSlotForDay(String day) {
        String xPath = "(//span[contains(text(),'" + day + "')])[1]/../..";
        scrollIntoView(xPath);
        return getElementByXPath(xPath);
    }

    public AutomatedObject getSpecificTimeSlotForDay(String day, int index) {
        return getElementByXPath("(//span[contains(text(),'" + day + "')])[" + index + "]/../..");
    }

    public AutomatedObject getSelectedTimeSlot() { return getElementByXPath(selectedTimeSlot); }
    public AutomatedObject getEditTimeSlotButton() { return getElementByXPath(editTimeSlot); }
    public AutomatedObject getDeleteTimeSlotButton() { return getElementByXPath(deleteTimeSlot); }
    public AutomatedObject getTimeslotEndDate() { return getElementByXPath(timeslotEndDate); }
    public AutomatedObject getEditSeries() { return getElementByXPath(editSeries); }
    public AutomatedObject getDeleteSeries() { return getElementByXPath(deleteSeries); }
    public AutomatedObject getMultiTimeslotCheckbox() { return getElementByXPath(multiTimeslotCheckbox); }
    public AutomatedObject getOccurrence() { return getElementByXPath(occurrence); }
    public AutomatedObject getRecurrenceInterval() { return getElementByXPath(recurrenceInterval); }
    public AutomatedObject getRecurrenceIntervalValue(String value) { return getElementByXPath("//*[@data-value='" + value + "']"); }
    public AutomatedObject getCustomTimeslotStartTime() { return getElementByXPath(startTime); }
    public AutomatedObject getCustomTimeslotEndTime() { return getElementByXPath(endTime); }
    public AutomatedObject getRecurrenceDay(String day) { return getElementByXPath("(//label[span='" + day + "']/span)[1]"); }
    public AutomatedObject getSummaryDate() { return getElementByXPath(summaryDate); }
    public AutomatedObject getSummaryTime() { return getElementByXPath(time); }
    public AutomatedObject getSummaryDescription() { return getElementByXPath(description); }
    public AutomatedObject getSummaryCapacity() { return getElementByXPath(capacity); }
    public AutomatedObject getSummaryServiceInterval() { return getElementByXPath(serviceInterval); }
    public AutomatedObject getSummaryRemainsType() { return getElementByXPath(remainsType); }
    public AutomatedObject getSummaryActivityType() { return getElementByXPath(serviceActivityType); }
    public AutomatedObject getSummaryLocationType() { return getElementByXPath(locationType); }
    public AutomatedObject getSummaryHonors() { return getElementByXPath(honors); }

    public void inputNewDate(String date) {
        waitForSalesforceLoad();
        getDateSearchBox().clear();
        getDateSearchBox().sendKeys(date);
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
    }

    public void inputRecurrenceEndDate(String date) { getTimeslotEndDate().clear(); getTimeslotEndDate().sendKeys(date); }
    public String returnTodaysDate(String format) { return today.format(DateTimeFormatter.ofPattern(format)); }
    public String returnDate(LocalDate dateToReturn, String format) { return dateToReturn.format(DateTimeFormatter.ofPattern(format)); }
    public String returnPreviousDate(Long numberOfDays) { return today.minusDays(numberOfDays).format(DateTimeFormatter.ofPattern("MMM dd, YYYY")); }
    public String returnFutureDate(Long numberOfDays) { return today.plusDays(numberOfDays).format(DateTimeFormatter.ofPattern("MM/dd/YYYY")); }
    public String returnNextDay(String weekDay) { return today.with(TemporalAdjusters.next(DayOfWeek.valueOf(weekDay))).format(DateTimeFormatter.ofPattern("MMM dd, YYYY")); }

    public String returnDateWithDay(LocalDate date) {
        String day = StringUtils.capitalize(date.getDayOfWeek().toString().toLowerCase());
        return day + ", " + returnDate(date, "MM/dd/YYYY");
    }

    public String returnCurrentDayOfWeek() { return StringUtils.capitalize(today.getDayOfWeek().toString().toLowerCase()); }

    public void inputFirstMondayOfNextMonth() {
        inputNewDate(today.with(TemporalAdjusters.firstDayOfNextMonth())
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
    }

    public void selectFirstTimeslotForCurrentDay() {
        String dayFormatted = formatDayOfWeek(today);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'" + dayFormatted + "')])[1]/../..")));
        getFirstTimeSlotForDay(dayFormatted).click();
        waitForSalesforceLoad();
    }

    public void selectFirstTimeslotForDay(LocalDate localDate) {
        String dayFormatted = formatDayOfWeek(localDate);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'" + dayFormatted + "')])[1]/../..")));
        getFirstTimeSlotForDay(dayFormatted).click();
        waitForSalesforceLoad();
    }

    public void selectSpecificTimeslotForDay(LocalDate localDate, int index) {
        String dayFormatted = formatDayOfWeek(localDate);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'" + dayFormatted + "')])[1]/../..")));
        getSpecificTimeSlotForDay(dayFormatted, index).click();
        waitForSalesforceLoad();
    }

    public void selectEditButton() { getEditTimeSlotButton().click(); waitForSalesforceLoad(); }
    public void selectDeleteButton() { getDeleteTimeSlotButton().click(); waitForSalesforceLoad(); }
    public void selectConfirmDeleteButton() { getElementByInnerText("button", "Yes").click(); waitForSalesforceLoad(); }
    public void selectOkButton() { getElementByInnerText("button", "OK").click(); waitForSalesforceLoad(); }
    public String getLastDayOfMonthDay() { return String.valueOf(getLastDayOfMonthDate().getDayOfMonth()); }

    public void selectMultiTimeslot(boolean isAcrossMultiTimeslots) {
        if (isAcrossMultiTimeslots != getMultiTimeslotCheckbox().isSelected()) getMultiTimeslotCheckbox().click();
    }

    public void inputCustomTimeslotStartTime(String startTime) {
        getCustomTimeslotStartTime().click();
        getElementBy("title", startTime).click();
        waitForSalesforceLoad();
    }

    public void inputCustomTimeslotEndTime(String endTime) {
        getCustomTimeslotEndTime().click();
        getElementByXPath("(.//*[ (@title='" + endTime + "')])[2]").click();
        waitForSalesforceLoad();
    }

    public void inputOccurrenceType(Occurrence occurrence) { getOccurrence().click(); getObjectByValue(occurrence.getText()).click(); }
    public void inputRecurrenceInterval(String interval) { getRecurrenceInterval().click(); getRecurrenceIntervalValue(interval).click(); }

    public void selectRecurrenceDay(AvailabilityDays day) {
        scrollIntoView("(//label[span='" + day.getText() + "']/span)[1]");
        getRecurrenceDay(day.getText()).click();
    }

    public void selectEditSeries() { getEditSeries().click(); waitForSalesforceLoad(); }
    public void selectDeleteSeries() { getDeleteSeries().click(); waitForSalesforceLoad(); }

    private String formatDayOfWeek(LocalDate date) {
        return StringUtils.capitalize(date.getDayOfWeek().toString().toLowerCase());
    }

    private LocalDate getLastDayOfMonthDate() {
        return YearMonth.from(LocalDate.now()).atEndOfMonth().with(TemporalAdjusters.lastDayOfMonth());
    }
}
