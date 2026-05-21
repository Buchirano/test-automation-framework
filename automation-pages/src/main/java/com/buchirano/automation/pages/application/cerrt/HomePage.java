package com.buchirano.automation.pages.application.cerrt;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating interaction methods for the PortalRM Home page.
 *
 * <p><b>Application:</b> PortalRM Resource Management</p>
 * <p><b>Screen:</b> Home</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class HomePage extends BasePageClass {

    private AutomatedObject getLaunchOnlineHelpButton() {
        Search search = getSearch();
        search.addCriteria("tag", "button");
        search.addCriteria("title", "Launch Online Help");
        scrollIntoView(getObject(search));
        return getObject(search);
    }

    /**
     * Clicks the Launch Online Help button, waits for a new browser tab to open,
     * then immediately closes it.
     */
    public void clickLaunchOnlineHelp() {
        wait.until(ExpectedConditions.attributeToBe(
                By.xpath("//button[@title='Launch Online Help']"), "aria-disabled", "false"));
        int startingWindowCount = new ArrayList<>(driver.getWindowHandles()).size();
        getLaunchOnlineHelpButton().click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(startingWindowCount + 1));
        closeSecondTab();
    }
}
