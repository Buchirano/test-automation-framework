package com.buchirano.automation.pages.application.cerrt;

import org.openqa.selenium.Keys;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Cemetery Details list view within the CeRRT application.
 *
 * <p><b>Application:</b> CeRRT (Cemetery Scheduling and Regulations Tool)</p>
 * <p><b>Screen:</b> Cemetery Details</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class CemeteryDetailsPage extends BasePageClass {

    public AutomatedObject getCemeteryName(String cemeteryName) {
        String xPath = "//span//a[@title='" + cemeteryName + "']";
        Search search = new Search();
        search.addCriteria("XPath", xPath);
        scrollIntoView(xPath);
        return getObject(search);
    }

    public AutomatedObject getStationId(String stationID) {
        String xPath = "//span[@title='" + stationID + "']";
        Search search = new Search();
        search.addCriteria("XPath", xPath);
        scrollIntoView(xPath);
        return getObject(search);
    }

    private AutomatedObject getCemeteryDetailsDropDown() {
        String xPath = "//button[contains(@title,'Cemetery Details')]";
        Search search = new Search();
        search.addCriteria("XPath", xPath);
        scrollIntoView(xPath);
        return getObject(search);
    }

    private AutomatedObject getCemeterySearchBox() {
        Search search = getSearch();
        search.addCriteria("tag", "input");
        search.addCriteria("name", "Cemetery Details-search-input");
        scrollIntoView(getObject(search));
        return getObject(search);
    }

    public void selectListView(String listViewToSelect) {
        getCemeteryDetailsDropDown().click();
        waitForSalesforceLoad();
        String xPath = "//span[text()='" + listViewToSelect + "']";
        Search search = new Search();
        search.addCriteria("XPath", xPath);
        scrollIntoView(xPath);
        getObject(search).click();
        waitForSalesforceLoad();
    }

    public String readCemeteryDetails() { return getCemeteryDetailsDropDown().readText(); }
    public void clearCemeterySearchBox() { getCemeterySearchBox().clear(); waitForSalesforceLoad(); }

    public void searchCemetery(String searchText) {
        getCemeterySearchBox().click();
        getCemeterySearchBox().click();
        getCemeterySearchBox().setValue(searchText);
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
    }

    public String readCemeterySearchBox() { return getCemeterySearchBox().readText(); }

    public void clickFirstSearchResult() {
        String xPath = "(//th//span//a)[1]";
        Search search = getSearch();
        search.addCriteria("xpath", xPath);
        scrollIntoView(xPath);
        getObject(search).click();
        waitForSalesforceLoad();
    }

    public void clickSpecifiedCemeteryResult(String cemeteryResultToClick) {
        getCemeteryName(cemeteryResultToClick).click();
        waitForSalesforceLoad();
    }
}
