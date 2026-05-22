package com.buchirano.automation.pages.application.cameo;

import com.buchirano.automation.pages.general.LightningBasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating mapped element paths and interaction methods
 * for the Cemetery Regulations display panel within NexusCM Case Management.
 *
 * <p>This panel displays read-only cemetery regulation data including remains type,
 * service activity type, location type, honors, and regulation text tabs.
 * It also provides access to the Special Guidance button.</p>
 *
 * <p><b>Screen:</b> Cemetery Regulations Panel (CaMEO)</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class CemeteryRegulationsPage extends LightningBasePageClass {

    public String cemeteryTitle = "//div[.='Cemetery Details']//following::slot/lightning-formatted-text";
    public String cemeteryRegulationsLastModifiedDate = "//label[.='Cemetery Regulations Last Modified Date']//following-sibling::lightning-output-field/div/lightning-formatted-text";
    public String remainsType = "//span[.='Remains Type']//following-sibling::div";
    public String serviceActivityType = "//span[.='Service Activity Type']//following-sibling::div";
    public String locationType = "//span[.='Location Type']//following-sibling::div";
    public String honors = "//span[.='Honors']//following-sibling::div";
    public String cemeteryRegulations = "//p[2]";
    public String cemeteryRegulationsTab1 = "//span[.='Cemetery Regulations Tab 1']//following-sibling::div/lightning-formatted-rich-text/span/p";
    public String cemeteryRegulationsTab2 = "//span[.='Cemetery Regulations Tab 2']//following-sibling::div/lightning-formatted-rich-text/span/p";
    public final String specialGuidance = "//button[contains(.,'Special Guidance')]";

    public AutomatedObject getCemeteryTitle() { scrollIntoView(cemeteryTitle); return getElementByXPath(cemeteryTitle); }
    public AutomatedObject getCemeteryRegulationsLastModifiedDate() { scrollIntoView(cemeteryRegulationsLastModifiedDate); return getElementByXPath(cemeteryRegulationsLastModifiedDate); }
    public AutomatedObject getServiceActivityType() { scrollIntoView(serviceActivityType); return getElementByXPath(serviceActivityType); }
    public AutomatedObject getRemainsType() { scrollIntoView(remainsType); return getElementByXPath(remainsType); }
    public AutomatedObject getLocationType() { scrollIntoView(locationType); return getElementByXPath(locationType); }
    public AutomatedObject getHonors() { scrollIntoView(honors); return getElementByXPath(honors); }
    public AutomatedObject getCemeteryRegulations() { scrollIntoView(cemeteryRegulations); return getElementByXPath(cemeteryRegulations); }
    public AutomatedObject getCemeteryRegulationsTab1() { scrollIntoView(cemeteryRegulationsTab1); return getElementByXPath(cemeteryRegulationsTab1); }
    public AutomatedObject getCemeteryRegulationsTab2() { scrollIntoView(cemeteryRegulationsTab2); return getElementByXPath(cemeteryRegulationsTab2); }
    public AutomatedObject getSpecialGuidanceButton() { scrollIntoView(specialGuidance); return getElementByXPath(specialGuidance); }
}
