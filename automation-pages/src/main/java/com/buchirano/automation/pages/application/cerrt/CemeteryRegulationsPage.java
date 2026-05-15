package com.buchirano.automation.pages.application.cerrt;

import java.util.Random;

import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;
import com.buchirano.automation.core.Search;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Cemetery Regulations tab within the CeRRT Cemetery Details screen.
 *
 * <p><b>Application:</b> CeRRT (Cemetery Scheduling and Regulations Tool)</p>
 * <p><b>Screen:</b> Cemetery Details — Cemetery Regulations Tab</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class CemeteryRegulationsPage extends BasePageClass {

    private final String cemeteryRegulationsTab = "//a[@data-label='Cemetery Regulations']";
    private final String remainsTypeField = "//*[@data-field-id='RecordRemains_Type__cField']//child::div[2]//child::span";
    private final String activityTypeField = "//*[@data-field-id='RecordService_Activity_Type__cField']//child::div[2]//child::span";
    private final String locationTypeField = "//*[@data-field-id='RecordLocation_Type__cField']//child::div[2]//child::span";
    private final String honorsField = "//*[@data-field-id='RecordHonors__cField']//child::div[2]//child::span";
    private final String regulationsTabOne = "//*[@data-field-id='RecordCemetery_Regulations_Tab_1_cField1']//child::div[2]//child::span";
    private final String regulationsTabTwo = "//*[@data-field-id='RecordCemetery_Regulations_Tab_2_cField1']//child::div[2]//child::span";
    private final String regulationsTabOneEditButton = "//button[@title='Edit Cemetery Regulations Tab 1']";
    private final String cemeteryRegulationsOneTextField = "//*[@data-field-id='RecordCemetery_Regulations_Tab_1_cField1']//child::p";
    private final String cemeteryRegulationsTwoTextField = "//*[@data-field-id='RecordCemetery_Regulations_Tab_2_cField1']//child::p";
    private final String regulationsTabTwoEditButton = "//button[@title='Edit Cemetery Regulations Tab 2']";
    private final String closeErrorDialog = "//button[@title='Close error dialog']";
    private final String saveButton = "//button[@name='SaveEdit']//parent::lightning-button";

    public AutomatedObject getCemeteryRegulationsTab() { return getElementByXPath(cemeteryRegulationsTab); }
    public AutomatedObject getRemainsTypeField() { return getElementByXPath(remainsTypeField); }
    public AutomatedObject getServiceActivityType() { return getElementByXPath(activityTypeField); }
    public AutomatedObject getLocationType() { return getElementByXPath(locationTypeField); }
    public AutomatedObject getHonors() { return getElementByXPath(honorsField); }
    public AutomatedObject getCemeteryRegulationsTabOne() { return getElementByXPath(regulationsTabOne); }
    public AutomatedObject getCemeteryRegulationsTabTwo() { return getElementByXPath(regulationsTabTwo); }
    public AutomatedObject getCemRegsTabOneEditButton() { return getElementByXPath(regulationsTabOneEditButton); }
    public AutomatedObject getCemRegsTabTwoEditButton() { return getElementByXPath(regulationsTabTwoEditButton); }
    public AutomatedObject getCemRegsOneField() { return getElementByXPath(cemeteryRegulationsOneTextField); }
    public AutomatedObject getCemRegsTwoField() { return getElementByXPath(cemeteryRegulationsTwoTextField); }
    public AutomatedObject getCloseErrorDialog() { return getElementByXPath(closeErrorDialog); }
    public AutomatedObject getSaveButton() { return getElementByXPath(saveButton); }
    public AutomatedObject getCancelButton() { return getElementByXPath(saveButton); }

    public void clickCemeteryRegulationsTab() { getCemeteryRegulationsTab().click(); waitForSalesforceLoad(); }
    public void clickEditCemeteryRegulationsTabOne() { getCemRegsTabOneEditButton().click(); }
    public void clickEditCemeteryRegulationsTabTwo() { getCemRegsTabTwoEditButton().click(); }
    public void clickSave() { getSaveButton().click(); waitForSalesforceLoad(); }
    public void clickCancel() { getCancelButton().click(); waitForSalesforceLoad(); }

    public String testCemeteryRegulationsOneInput() {
        String cemRegsOne = randomStringOfLength(3351);
        clickEditCemeteryRegulationsTabOne();
        clearText();
        getCemRegsOneField().sendKeys(cemRegsOne);
        clickSave();
        Search tabOneErrorMessage = getSearch();
        tabOneErrorMessage.addCriteria("xpath", ".//div[(text()='The Cemetery Regulations Tab 1 limit of 3,350 characters has been reached.')]");
        scrollIntoView(getObject(tabOneErrorMessage));
        String errorPresent = Boolean.toString(isObjectPresent(tabOneErrorMessage));
        getCloseErrorDialog().click();
        getCemRegsOneField().click();
        clearText();
        clickSave();
        clickEditCemeteryRegulationsTabOne();
        getCemRegsOneField().sendKeys("Automation");
        clickSave();
        return errorPresent;
    }

    public String testCemeteryRegulationsTwoInput() {
        clickEditCemeteryRegulationsTabTwo();
        clearText();
        String cemRegsTwo = randomStringOfLength(4001);
        getCemRegsTwoField().sendKeys(cemRegsTwo);
        clickSave();
        Search tabTwoErrorMessage = getSearch();
        tabTwoErrorMessage.addCriteria("xpath", ".//div[(text()='The Cemetery Regulations Tab 2 limit of 4,000 characters has been reached.')]");
        scrollIntoView(getObject(tabTwoErrorMessage));
        String errorPresent = Boolean.toString(isObjectPresent(tabTwoErrorMessage));
        getCloseErrorDialog().click();
        getCemRegsTwoField().click();
        clearText();
        clickSave();
        clickEditCemeteryRegulationsTabTwo();
        getCemRegsTwoField().sendKeys("Automation");
        clickSave();
        return errorPresent;
    }

    private static String randomStringOfLength(int length) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
