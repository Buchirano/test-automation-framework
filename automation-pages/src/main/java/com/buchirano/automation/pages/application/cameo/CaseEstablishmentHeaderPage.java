package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import org.openqa.selenium.By;

import com.buchirano.automation.pages.general.BasePageClass;

/**
 * Page class encapsulating mapped element paths and read methods for the
 * Case Establishment header section, which persists across all chevron screens.
 *
 * <p><b>Screen:</b> Case Establishment Header (all screens)</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class CaseEstablishmentHeaderPage extends BasePageClass {

    public final String versionNumber = "//div[contains(text(), 'Version Number')]";
    public final String headerCemeteryNameAndID = "//*[@data-id='headerCemeteryName']//lightning-formatted-text";
    public final String headerDesiredCemeteryNameAndID = "//label[(text()='Desired Cemetery Name and Station ID')]//..//lightning-formatted-text";
    public final String headerCaseDetailsName = "//*[@data-id='headerCaseDetailsName']//a/..";
    public final String headerCaseType = "//*[@data-id='headerCaseType']//lightning-formatted-text";
    public final String headerCaseDetailsCaseStatus = "//*[@data-id='headerCaseDetailsCaseStatus']//lightning-formatted-text";
    public final String headerCaseDetailsEligibilityReviewStatus = "//*[@data-id='headerCaseDetailsEligibilityReviewStatus']//lightning-formatted-text";
    public final String headerPreNeedEligibilityStatus = "//*[@data-id='headerPreNeedEligibilityStatus']//lightning-formatted-text";
    public final String headerClaimantName = "//*[@data-id='headerDecedentFullName']//lightning-formatted-text";
    public final String headerRelationshipToVeteran = "//label[(text()='Relationship to Veteran')]//..//lightning-formatted-text";
    public final String headerSex = "//label[(text()='Sex')]//..//lightning-formatted-text";
    public final String headerSsn = "//*[@data-id='headerSsn']//lightning-formatted-text";
    public final String headerDOB = "//*[@data-id='headerDOB']//lightning-formatted-text";
    public final String headerDOD = "//*[@data-id='headerDOD']//lightning-formatted-text";
    public final String headerDecedentBOSSId = "//*[@data-id='headerCaseDetailsBOSSId']//lightning-formatted-text";

    public String retrieveVersionNumber() { scrollIntoView(versionNumber); return driver.findElement(By.xpath(versionNumber)).getText(); }
    public String retrieveCemeteryNameAndID() { scrollIntoView(headerCemeteryNameAndID); return driver.findElement(By.xpath(headerCemeteryNameAndID)).getText(); }
    public String retrieveDesiredCemeteryNameAndID() { scrollIntoView(headerDesiredCemeteryNameAndID); return driver.findElement(By.xpath(headerDesiredCemeteryNameAndID)).getText(); }
    public String retrieveCaseDetailsName() { scrollIntoView(headerCaseDetailsName); return driver.findElement(By.xpath(headerCaseDetailsName)).getText(); }

    public void clickCaseDetailsLink() {
        scrollIntoView(headerCaseDetailsName);
        driver.findElement(By.xpath(headerCaseDetailsName)).click();
        waitForSalesforceLoad();
        openSecondTab();
    }

    public String retrieveCaseType() { scrollIntoView(headerCaseType); return driver.findElement(By.xpath(headerCaseType)).getText(); }
    public String retrieveCaseStatus() { scrollIntoView(headerCaseDetailsCaseStatus); return driver.findElement(By.xpath(headerCaseDetailsCaseStatus)).getText(); }
    public String retrieveEligibilityReviewStatus() { scrollIntoView(headerCaseDetailsEligibilityReviewStatus); return driver.findElement(By.xpath(headerCaseDetailsEligibilityReviewStatus)).getText(); }
    public String retrievePreNeedEligibilityStatus() { scrollIntoView(headerPreNeedEligibilityStatus); return driver.findElement(By.xpath(headerPreNeedEligibilityStatus)).getText(); }
    public String retrieveClaimantName() { scrollIntoView(headerClaimantName); return driver.findElement(By.xpath(headerClaimantName)).getText(); }
    public String retrieveRelationshipToVeteran() { scrollIntoView(headerRelationshipToVeteran); return driver.findElement(By.xpath(headerRelationshipToVeteran)).getText(); }
    public String retrieveSexValue() { scrollIntoView(headerSex); return driver.findElement(By.xpath(headerSex)).getText(); }
    public String retrieveSsn() { scrollIntoView(headerSsn); return driver.findElement(By.xpath(headerSsn)).getText(); }
    public String retrieveDOB() { scrollIntoView(headerDOB); return driver.findElement(By.xpath(headerDOB)).getText(); }
    public String retrieveDOD() { scrollIntoView(headerDOD); return driver.findElement(By.xpath(headerDOD)).getText(); }
    public String retrieveDecedentId() { scrollIntoView(headerDecedentBOSSId); return driver.findElement(By.xpath(headerDecedentBOSSId)).getText(); }

    public List<String> listOfPreNeedCaseEstablishmentHeaderValues() {
        return List.of(retrieveCemeteryNameAndID(), retrieveDesiredCemeteryNameAndID(),
                retrieveCaseDetailsName(), retrieveCaseType(), retrieveCaseStatus(),
                retrieveEligibilityReviewStatus(), retrievePreNeedEligibilityStatus(),
                retrieveClaimantName(), retrieveRelationshipToVeteran(), retrieveSexValue(),
                retrieveSsn(), retrieveDOB(), retrieveDecedentId());
    }
}
