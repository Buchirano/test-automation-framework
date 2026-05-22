package com.buchirano.automation.pages.application.cerrt;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating the mapped element paths and interaction methods
 * for the Activity tab within the PortalRM resource management screen.
 *
 * <p>The Activity tab provides a log of changes and actions taken on a cemetery
 * record. This class provides the accessor and click handler needed to navigate
 * to and interact with that tab.</p>
 *
 * <p><b>Application:</b> PortalRM Resource Management</p>
 * <p><b>Screen:</b> Cemetery Details — Activity Tab</p>
 * <p><b>Layer:</b> Page Object (Application)</p>
 */
public class ActivityPage extends LightningBasePageClass {

    // ========================== XPath Constants ==========================

    /** XPath for the Activity tab navigation link inside a Cemetery Detail record. */
    private final String activityTab = "//a[@data-label='Activity']";

    // ========================== Element Accessors ==========================

    /**
     * Returns the Activity tab navigation element.
     *
     * @return {@link AutomatedObject} for the Activity tab
     */
    public AutomatedObject getActivityTab() {
        return getElementByXPath(activityTab);
    }

    // ========================== Interaction Methods ==========================

    /**
     * Clicks the Activity tab and waits for the Salesforce DOM to stabilize.
     */
    public void clickActivityTab() {
        getActivityTab().click();
        waitForSalesforceLoad();
    }
}
