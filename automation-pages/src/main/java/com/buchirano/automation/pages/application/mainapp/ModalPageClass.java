package com.buchirano.automation.pages.application.mainapp;

import java.util.List;

import org.openqa.selenium.Keys;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating mapped element paths and interaction methods
 * for modal dialogs used across the CaseManagementApp application.
 *
 * <p><b>Layer:</b> Page Object (Application — CaMEO Modal)</p>
 */
public class ModalPageClass extends LightningBasePageClass {

    public String customSelectButton = "//*[@id='selectButton']";

    public AutomatedObject contactSearchButton() { scrollIntoView(getButtonXPathByDataId("searchContactButton")); return getButtonByDataId("searchContactButton"); }
    public AutomatedObject getCloseModalButton() { String path = "//lightning-icon[@data-id='xButton']"; scrollIntoView(path); return getElementByXPath(path); }
    public AutomatedObject getSearchButton() { scrollIntoView("//*[@data-id='searchButton']"); return getElementBy("data-id", "searchButton"); }
    public List<AutomatedObject> getSelectContactButtons() { return getElementsBy("data-id", "selectButton"); }

    public void openContactSearchModal() { contactSearchButton().click(); waitForSalesforceLoad(); }
    public void clickCloseModalButton() { getCloseModalButton().click(); waitForSalesforceLoad(); }
    public void clickModalSearchButton() { getSearchButton().click(); waitForSalesforceLoad(); }
    public void selectFirstEnabledResult() { getFirstEnabledElement(getSelectContactButtons()).click(); }

    public void clickSelectButtonOrganizations() {
        String path = "(//*[(@data-id='selectRecord' and @data-row='1')])[1]";
        scrollIntoView(path); getElementByXPath(path).click(); waitForSalesforceLoad();
    }

    public void clickSelectButtonApplicantTab() {
        String path = "//*[(@data-id='selectButton' and @data-row='1')]";
        scrollIntoView(path); getElementByXPath(path).click(); waitForSalesforceLoad();
    }

    public void clickFirstSelectButtonClaimantTab() {
        String path = "(//*[@data-id='secondResultTab']//*[@data-id='selectButton'])[1]";
        scrollIntoView(path); getElementByXPath(path).click(); waitForSalesforceLoad();
    }

    public void clickSelectDecedentClaimantButton() {
        String path = "//*[(@data-id='selectButton' and @data-row='2')]";
        scrollIntoView(path); getElementByXPath(path).click(); waitForSalesforceLoad();
    }

    public void scrollDownDetails() {
        action.sendKeys(Keys.DOWN.toString().repeat(2)).perform();
        getElementByXPath("(//span[@data-id='First Name'])[1]").click();
        action.sendKeys(Keys.DOWN.toString().repeat(15)).perform();
    }
}
