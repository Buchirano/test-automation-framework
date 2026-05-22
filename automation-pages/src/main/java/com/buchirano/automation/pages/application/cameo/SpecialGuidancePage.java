package com.buchirano.automation.pages.application.cameo;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class for the Special Guidance modal within NexusCM Case Management.
 *
 * <p><b>Modal:</b> Special Guidance</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class SpecialGuidancePage extends LightningBasePageClass {

    public String cemeteryLabelElementSelector = "//*[@class='slds-modal__header']/h2";
    public String cemeteryInstLabelElementSelector = "//*[@class='slds-modal__container']/section/div";

    public AutomatedObject getCemeteryLabel() { scrollIntoView(cemeteryLabelElementSelector); return getElementByXPath(cemeteryLabelElementSelector); }
    public AutomatedObject getInstructionsLabel() { scrollIntoView(cemeteryInstLabelElementSelector); return getElementByXPath(cemeteryInstLabelElementSelector); }
}
