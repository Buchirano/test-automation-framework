package com.buchirano.automation.pages.application.cerrt;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating tab-level navigation and general interaction methods
 * for the PortalRM application.
 *
 * <p>This class provides click handlers for all top-level and detail-level tabs
 * within PortalRM, including Home, Cemetery Details, System Users, Reports, Schedule,
 * Availability, Cemetery Regulations, Activity, and Online Help.</p>
 *
 * <p><b>Application:</b> PortalRM Resource Management</p>
 * <p><b>Layer:</b> Page Object (Application — Navigation)</p>
 */
public class CeRRTGeneralNavigationPage extends BasePageClass {

    private AutomatedObject getHomeTab() { return getElementByXPath("//a[.='Home']/parent::*"); }
    private AutomatedObject getCemeteryDetailsTab() { return getElementByXPath("//a[@title='Cemetery Details']/parent::*"); }
    private AutomatedObject getSystemUsersTab() { return getElementByXPath("//a[.='MBMS Users']/parent::*"); }
    private AutomatedObject getReportsTab() { return getElementByXPath("//a[.='Reports']/parent::*"); }

    public AutomatedObject getScheduleTab() { return getElementByXPath("//a[@data-label='Schedule']"); }

    private AutomatedObject getOnlineHelpButton() {
        Search search = getSearch();
        search.addCriteria("title", "Online Help");
        scrollIntoView(getObject(search));
        return getObject(search);
    }

    public void clickHomeTab() { pageScrollUp(); getHomeTab().click(); waitForSalesforceLoad(); }
    public void clickCemeteryDetailsTab() { pageScrollUp(); getCemeteryDetailsTab().click(); waitForSalesforceLoad(); }

    public void clickCemeteryDetailsTabTemp() {
        pageScrollUp();
        driver.findElement(By.xpath("//a[@title='Cemetery Details']/parent::*")).click();
        waitForSalesforceLoad();
    }

    public void clickSystemUsersTab() { getSystemUsersTab().click(); waitForSalesforceLoad(); }
    public void clickSystemUsersTabTemp() { getSystemUsersTab().click(); }
    public void clickReportsTab() { pageScrollUp(); getReportsTab().click(); waitForSalesforceLoad(); }
    public void clickScheduleTab() { pageScrollUp(); getScheduleTab().click(); waitForSalesforceLoad(); }
    public void clickAvailabilityTab() { pageScrollUp(); getElementByXPath("//a[@data-label='Availability']").click(); waitForSalesforceLoad(); }
    public void clickCemeteryRegulationsTab() { pageScrollUp(); getElementByXPath("//a[@data-label='Cemetery Regulations']").click(); waitForSalesforceLoad(); }
    public void clickActivityTab() { pageScrollUp(); getElementByXPath("//a[@data-label='Activity']").click(); waitForSalesforceLoad(); }

    public void clickOnlineHelp() {
        int startingWindowCount = new ArrayList<>(driver.getWindowHandles()).size();
        getOnlineHelpButton().click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(startingWindowCount + 1));
        closeSecondTab();
    }
}
