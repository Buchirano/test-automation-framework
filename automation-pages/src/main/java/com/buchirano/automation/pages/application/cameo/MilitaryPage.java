package com.buchirano.automation.pages.application.cameo;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;

import com.buchirano.automation.enums.BranchOfService;
import com.buchirano.automation.enums.MilitaryRank;
import com.buchirano.automation.enums.WarPeriod;
import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Military Service Details screen within the Case Establishment workflow.
 *
 * <p><b>Screen:</b> Case Establishment — Military Service Details</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class MilitaryPage extends BasePageClass {

    public final String militaryBranchServiceOptions = "//*[@class='slds-listbox__item']";
    public String militaryBranchServiceElementSelector = "//*[@data-id='bosLookup']//input";
    public String militaryRemoveBranchOfService = "//*[@data-id='bosLookup']//*[@title='Remove']";
    public String militaryRemoveServiceRowButton = "//*[@data-id='rowDeleteButton']//button";
    public String militaryRankElementSelector = "//*[@data-id='militaryRankCustomLookup']//input";
    public String militaryEODElementSelector = "//*[@data-id='enteredOnDuty']//input";
    public String militaryRADElementSelector = "//*[@data-id='releaseFromActiveDuty']//input";
    public String militarySearchWarPeriodElementSelector = "//*[@data-id='warPeriodsLookup']//input";
    public String militaryWarPeriodDropdown = "//*[@class='slds-listbox__item']";
    public String militaryServiceButton = "//*[@data-id='addMilitaryServiceButton']";
    public String militarySummaryElementSelector = "//*[@data-id='warSummary']";
    public String militaryPopupElementSelector = "//button[contains(.,'Yes')]";
    public String militaryRemoveWarPeriod = "//*[@data-id='warPeriodsLookup']//button";
    public String suspendRulesCheckbox = "//*[@name='Suspend_Rules__c']";
    public String removeSelectedItem = "//*[@data-key='close']";
    public String selectedItem = "//*[@class='slds-pill']";
    public final String warningText = "//*[@data-id='ErrorMessageBanner']";
    public final String characterOfService = "//button[@aria-label='Character of Service']";
    public final String stateElementSelector = "//*[@name='State__c']";

    public AutomatedObject getAddNewMilitaryServiceInformationButton() { scrollIntoView(militaryServiceButton); return getElementByXPath(militaryServiceButton); }

    public AutomatedObject getStateElementSelector(int row) {
        String path = "(" + stateElementSelector + ")[" + row + "]";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    public AutomatedObject getCharacterOfServiceDropdown(int row) {
        int visible = row * 2;
        String path = "(" + characterOfService + ")[" + visible + "]";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    public List<AutomatedObject> getBranchOfServiceDropdown() { return getElementsByXPath(militaryBranchServiceElementSelector); }
    public List<AutomatedObject> getMilitaryRankDropdown() { return getElementsByXPath(militaryRankElementSelector); }
    public List<AutomatedObject> getMilitaryRankOption(MilitaryRank rank) { return getElementsBy("title", rank.getText()); }
    public List<AutomatedObject> getEnteredOnDutyDateInput() { return getElementsByXPath(militaryEODElementSelector); }
    public List<AutomatedObject> getReleasedFromActiveDutyInput() { return getElementsByXPath(militaryRADElementSelector); }
    public List<AutomatedObject> getSearchWarPeriodInput() { return getElementsByXPath(militarySearchWarPeriodElementSelector); }
    public List<AutomatedObject> getRemoveWarPeriod() { return getElementsByXPath(militaryRemoveWarPeriod); }
    public AutomatedObject getWarPeriodSummaryText() { scrollIntoView(militarySummaryElementSelector); return getElementByXPath(militarySummaryElementSelector); }
    public AutomatedObject getSelectedWarPeriodsNotValidYesButton() { scrollIntoView(militaryPopupElementSelector); return getElementByXPath(militaryPopupElementSelector); }
    public AutomatedObject getSelectedWarPeriodsNotValidNoButton() { scrollIntoView(getButtonXPathByDataId("noButton")); return getButtonByDataId("noButton"); }
    public List<AutomatedObject> getMilitaryServiceRemovalButtons() { return getElementsByXPath(militaryRemoveServiceRowButton); }
    public AutomatedObject getSuspendRulesCheckbox() { scrollIntoView(suspendRulesCheckbox); return getElementByXPath(suspendRulesCheckbox); }
    public AutomatedObject getWarningMessage() { scrollIntoView(warningText); return getElementByXPath(warningText); }

    public void addMilitaryServiceInformation(BranchOfService service, MilitaryRank rank,
            String eod, String rad, List<WarPeriod> warPeriods, String state, String characterOfService) {
        getAddNewMilitaryServiceInformationButton().click();
        int rowCount = getMilitaryServiceRemovalButtons().size();
        editBranchOfService(service);
        if (rank != null) { getMilitaryRankDropdown().get(0).click(); getMilitaryRankOption(rank).get(0).click(); waitForSalesforceLoad(); }
        if (state != null && !state.equals("--None--")) { getStateElementSelector(rowCount).click(); scrollIntoView("//span[@title = '" + state + "']"); getElementByXPath("//span[@title = '" + state + "']"); }
        if (eod != null) { getEnteredOnDutyDateInput().get(0).sendKeys(eod); action.sendKeys(Keys.ENTER).perform(); waitForSalesforceLoad(); }
        if (rad != null) { getReleasedFromActiveDutyInput().get(0).sendKeys(rad); action.sendKeys(Keys.ENTER).perform(); waitForSalesforceLoad(); }
        if (warPeriods != null) {
            for (WarPeriod warPeriod : warPeriods) {
                getSearchWarPeriodInput().get(0).click();
                getSearchWarPeriodInput().get(0).sendKeys(warPeriod.getText());
                action.sendKeys(Keys.ARROW_DOWN).perform();
                action.sendKeys(Keys.ENTER).perform();
                waitForSalesforceLoad();
            }
        }
        if (characterOfService != null && !characterOfService.equals("--None--")) selectDropdownOption(getCharacterOfServiceDropdown(1), characterOfService);
    }

    public void addSecondMilitaryServiceInformation(BranchOfService service, MilitaryRank rank,
            String eod, String rad, List<WarPeriod> warPeriods, String state, String characterOfService) {
        getAddNewMilitaryServiceInformationButton().click();
        waitForSalesforceLoad();
        getBranchOfServiceDropdown().get(1).click();
        getBranchOfServiceDropdown().get(1).sendKeys(service.getText());
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
        getMilitaryRankDropdown().get(1).click();
        getMilitaryRankOption(rank).get(1).click();
        waitForSalesforceLoad();
        if (state != null && !state.equals("--None--")) { getStateElementSelector(2).click(); getElementByXPath("(//span[@title = '" + state + "'])[2]").click(); }
        getEnteredOnDutyDateInput().get(1).sendKeys(eod); action.sendKeys(Keys.ENTER).perform(); waitForSalesforceLoad();
        getReleasedFromActiveDutyInput().get(1).sendKeys(rad); action.sendKeys(Keys.ENTER).perform(); waitForSalesforceLoad();
        for (WarPeriod warPeriod : warPeriods) { getSearchWarPeriodInput().get(1).sendKeys(warPeriod.getText()); action.sendKeys(Keys.ARROW_DOWN).perform(); action.sendKeys(Keys.ENTER).perform(); waitForSalesforceLoad(); }
        if (characterOfService != null && !characterOfService.equals("--None--")) { getCharacterOfServiceDropdown(2).click(); getElementByXPath("(//span[@title = '" + characterOfService + "'])[2]").click(); }
    }

    public void addWarPeriod(WarPeriod warPeriod) { getSearchWarPeriodInput().get(0).sendKeys(warPeriod.getText()); action.sendKeys(Keys.ARROW_DOWN).perform(); action.sendKeys(Keys.ENTER).perform(); waitForSalesforceLoad(); }
    public void editEnteredOnDutyDate(String date) { clearEnteredOnDutyDate(); getEnteredOnDutyDateInput().get(0).sendKeys(date); waitForSalesforceLoad(); action.sendKeys(Keys.TAB).perform(); }
    public void clearEnteredOnDutyDate() { getEnteredOnDutyDateInput().clear(); waitForSalesforceLoad(); }
    public void editReleasedFromActiveDutyDate(String date) { clearReleasedFromActiveDutyDate(); getReleasedFromActiveDutyInput().get(0).sendKeys(date); action.sendKeys(Keys.TAB).perform(); getSelectedWarPeriodsNotValidYesButton(); waitForSalesforceLoad(); }
    public void clearReleasedFromActiveDutyDate() { getReleasedFromActiveDutyInput().clear(); waitForSalesforceLoad(); }

    public void editBranchOfService(BranchOfService service) {
        if (service == null) return;
        int index = getBranchOfServiceDropdown().size() == 1 ? 0 : 1;
        getBranchOfServiceDropdown().get(index).click();
        getBranchOfServiceDropdown().get(index).sendKeys(service.getText());
        waitForSalesforceLoad();
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
    }

    public void removeWarPeriod(int index) { getRemoveWarPeriod().get(index).click(); waitForSalesforceLoad(); }
    public void removeBranchOfService(int index) { getMilitaryServiceRemovalButtons().get(index).click(); waitForSalesforceLoad(); }

    public void deleteAllServiceEntries() {
        if (!isMilitaryServicePresent()) return;
        List<AutomatedObject> buttons = getMilitaryServiceRemovalButtons();
        for (AutomatedObject btn : buttons) { btn.click(); waitForSalesforceLoad(); }
    }

    public void createTwoServiceEntries() {
        addMilitaryServiceInformation(BranchOfService.V2_AG_US_AIR_NATIONAL_GUARD, MilitaryRank.AG_FIRST_LT, "04/03/1970", "01/01/2000", Arrays.asList(WarPeriod.PANAMA, WarPeriod.KOSOVO), "Alabama", "Honorable");
        addSecondMilitaryServiceInformation(BranchOfService.V2_AA_US_ARMY_AIR_FORCES, MilitaryRank.A1C_AIRMAN_1ST_CLASS, "10/01/1947", "04/01/1970", Arrays.asList(WarPeriod.KOREA, WarPeriod.VIETNAM), "", "Under Honorable Condition");
    }

    public void deleteSecondServiceEntry() { getMilitaryServiceRemovalButtons().get(1).click(); waitForSalesforceLoad(); }
    public void suspendCheckBox() { getSuspendRulesCheckbox().click(); waitForSalesforceLoad(); }

    public void addBranchOfService(BranchOfService branch) {
        getAddNewMilitaryServiceInformationButton().click();
        safeSleep(2000);
        if (branch == null) return;
        getBranchOfServiceDropdown().get(0).click();
        getBranchOfServiceDropdown().get(0).sendKeys(branch.getText());
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
    }

    public void addMilitaryRank(MilitaryRank rank) {
        if (rank == null) return;
        safeSleep(2000);
        getMilitaryRankDropdown().get(0).click();
        getMilitaryRankDropdown().get(0).sendKeys(rank.getText());
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        waitForSalesforceLoad();
    }

    public boolean isMilitaryServicePresent() {
        Search search = getSearch();
        search.addCriteria("xpath", militaryRemoveServiceRowButton);
        return isObjectPresent(search);
    }

    public List<AutomatedObject> listOfAllFields(int row) {
        int idx = row - 1;
        return List.of(getBranchOfServiceDropdown().get(idx), getMilitaryRankDropdown().get(idx),
                getStateElementSelector(row), getEnteredOnDutyDateInput().get(idx),
                getReleasedFromActiveDutyInput().get(idx), getSearchWarPeriodInput().get(idx),
                getCharacterOfServiceDropdown(row));
    }

    private void safeSleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); e.printStackTrace(); }
    }
}
