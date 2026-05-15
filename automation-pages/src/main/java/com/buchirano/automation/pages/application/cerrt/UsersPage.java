package com.buchirano.automation.pages.application.cerrt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the MBMS Users screen within the CeRRT application.
 *
 * <p><b>Application:</b> CeRRT (Cemetery Scheduling and Regulations Tool)</p>
 * <p><b>Screen:</b> MBMS Users</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class UsersPage extends BasePageClass {

    private final String userSearchBox = "//input[@name='MBMS User-search-input']";
    private final String userViewDropdown = "//*[@title='Select a List View: MBMS Users']";
    private final String cemeterySearch = "//*[@name='enter-search']";
    private final String firstCemeteryResult = ".//span[(text()='Select Item 1')]//parent::label";
    private final String cemeteryRows = "//span[@title='Associated Cemeteries']//following::tr//following::tr";
    public final String firstUserEmailResult = "(//td//span//a)[1]";

    public AutomatedObject getUserSearchBox() { return getElementByXPath(userSearchBox); }
    public AutomatedObject getUserViewDropdown() { return getElementByXPath(userViewDropdown); }

    public AutomatedObject getCemeterySearch() {
        Search search = getSearch();
        search.addCriteria("xpath", cemeterySearch);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cemeterySearch)));
        return getObject(search);
    }

    public AutomatedObject getFirstCemeteryResult() {
        Search search = getSearch();
        search.addCriteria("xpath", firstCemeteryResult);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstCemeteryResult)));
        return getObject(search);
    }

    public List<AutomatedObject> getAssociatedCemRows() { return getElementsByXPath(cemeteryRows); }

    public AutomatedObject getUserName(String userName) {
        Search search = new Search();
        search.addCriteria("XPath", "//th/span//a[contains(@title,'" + userName + "')]");
        return getObject(search);
    }

    public AutomatedObject getUserEmail() {
        Search search = new Search();
        search.addCriteria("XPath", firstUserEmailResult);
        return getObject(search);
    }

    public void searchForUser(String user) {
        getUserSearchBox().click();
        getUserSearchBox().click();
        getUserSearchBox().clear();
        getUserSearchBox().sendKeys(user);
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
        if (user.contains("@")) {
            wait.until(ExpectedConditions.attributeToBe(By.xpath(firstUserEmailResult), "innerText", user));
        } else {
            wait.until(ExpectedConditions.attributeToBe(By.xpath("//th/span//a"), "innerText", user));
        }
    }

    public void clickUserDropdown() { getUserViewDropdown().click(); waitForSalesforceLoad(); }

    public void selectListView(String listViewToSelect) {
        clickUserDropdown();
        Search search = new Search();
        search.addCriteria("XPath", "//span[text()='" + listViewToSelect + "']");
        getObject(search).click();
    }

    public void selectUser(String user) { getElementBy("title", user).click(); waitForSalesforceLoad(); }
    public void clickAddAssociatedCemetery() { getElementBy("title", "Add Associated Cemeteries").click(); }

    public void inputCemeterySearch(String text) {
        getCemeterySearch().clear();
        getCemeterySearch().sendKeys(text);
        action.sendKeys(Keys.ENTER).perform();
        waitForPageLoad();
    }

    public void selectFirstCemeteryResult() { getFirstCemeteryResult().click(); }

    public void clickAddCemeteryButton() {
        Search search = getSearch();
        search.addCriteria("title", "Add");
        AutomatedObject addButton = getObject(search);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getXpathFromAutomatedObject(addButton))));
        addButton.click();
        waitForSalesforceLoad();
    }

    public void clickDeleteAssociatedCemetery() { getElementBy("title", "Delete Associated Cemeteries").click(); }

    public void clickDeleteCemeteryButton() {
        Search search = getSearch();
        search.addCriteria("title", "Delete");
        AutomatedObject deleteButton = getObject(search);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getXpathFromAutomatedObject(deleteButton))));
        deleteButton.click();
        waitForSalesforceLoad();
    }

    public void clickYes() { getElementBy("title", "Yes").click(); waitForSalesforceLoad(); }
    public void clickNo() { getElementBy("title", "No").click(); waitForSalesforceLoad(); }
    public int getNumberOfAssociatedCemeteries() { return getAssociatedCemRows().size(); }

    public Boolean isAssociatedCemeteryPresent(String cemeteryText) {
        String xPath = ".//lightning-base-formatted-text[(text()='" + cemeteryText + "')]";
        Search search = getSearch();
        search.addCriteria("xpath", xPath);
        return isObjectPresent(search);
    }
}
