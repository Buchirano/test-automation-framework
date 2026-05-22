package com.buchirano.automation.pages.application.cameo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.enums.VerificationElement;
import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class for the Reports tab within the NexusCM application.
 *
 * <p><b>Screen:</b> Reports Tab</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class ReportsTabPage extends LightningBasePageClass {

    private final CaMEOGeneralNavigationPage navigate = new CaMEOGeneralNavigationPage();

    public final String reportsSearchBox = "//input[@placeholder='Search all reports...']";
    public String caseCreatedDateSortDropdown = "(//span[@data-tooltip='Case Created Date']//..//button[@tabindex=-1])[1]";
    public String caseCreatedDateSortDropdownDTCINT = "(//span[@data-tooltip='Case Created Date']//..//button[@tabindex=-1])[last()]";
    public String createdDateSortDescendingDropdownOption = "//*/text()[(.)='Sort Descending']/../..";
    public String refreshButton = "//*[.='Refresh']/div/button";
    public String firstCaseNumberResult = "(//tr//div//span//a)[3]";

    public AutomatedObject getReportsButton(String searchText) { String path = "//a[.='" + searchText + "']/parent::*"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject getAllReportsMenuItem() { scrollIntoView(".//a[(text()='All Reports')]"); return getElementByInnerText("a", "All Reports"); }
    public AutomatedObject getAllReportsSearchBoxInput() { scrollIntoView(reportsSearchBox); return getElementByXPath(reportsSearchBox); }

    public WebElement getCaseName(String caseName) {
        String path = "//div[.='" + caseName + "']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
        scrollIntoView(path);
        return driver.findElement(By.xpath(path));
    }

    public WebElement getAssociatedCaseNumber(String caseIdentifier) {
        String path = caseNumberXpathByIdentifier(caseIdentifier);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
        scrollIntoView(path);
        return driver.findElement(By.xpath(path));
    }

    public WebElement getFirstCaseNumberLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstCaseNumberResult)));
        scrollIntoView(firstCaseNumberResult);
        return driver.findElement(By.xpath(firstCaseNumberResult));
    }

    public void clickRefreshButton() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(refreshButton)))).click();
        waitForSalesforceLoad();
    }

    public void clickToAllReports() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("All Reports")));
        getAllReportsMenuItem().click();
        waitForSalesforceLoad();
        waitForVisibility(VerificationElement.All_REPORTS);
        waitForVisibility("//th[@aria-label = 'Report Name']");
    }

    public boolean allReportsSearch(String searchText) {
        getAllReportsSearchBoxInput().sendKeys(searchText);
        clickReportsFolder(searchText);
        return true;
    }

    public void selectSortDescending() {
        String dropdownXpath = (environment.contains("dtc") && environment.contains("int"))
                ? caseCreatedDateSortDropdownDTCINT : caseCreatedDateSortDropdown;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createdDateSortDescendingDropdownOption))).click();
    }

    public boolean navigateToReportsTabAndSelect(String recordIdentifier, String reportToSearchFor) {
        navigate.clickReportsTab();
        clickToAllReports();
        allReportsSearch(reportToSearchFor);
        switchFrame("Report Viewer");
        selectSortDescending();
        return clickAssociatedCaseNumber(recordIdentifier);
    }

    private String caseNumberXpathByIdentifier(String caseIdentifier) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.='" + caseIdentifier + "']")));
        return "//div[.='" + caseIdentifier + "']/../..//td[@data-column-index=0]//a";
    }

    private void clickReportsFolder(String reportsFolder) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='" + reportsFolder + "']")));
        getReportsButton(reportsFolder).click();
        waitForSalesforceLoad();
    }

    private boolean clickAssociatedCaseNumber(String caseIdentifier) {
        boolean visible = getAssociatedCaseNumber(caseIdentifier).isDisplayed();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(caseNumberXpathByIdentifier(caseIdentifier)))).click();
        waitForSalesforceLoad();
        return visible;
    }
}
