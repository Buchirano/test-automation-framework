package com.buchirano.automation.pages.application.cameo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.buchirano.automation.enums.AuthorityForBurial;
import com.buchirano.automation.enums.ContainerSize;
import com.buchirano.automation.enums.CremainsType;
import com.buchirano.automation.enums.GeneralResponse;
import com.buchirano.automation.enums.LinerSize;
import com.buchirano.automation.enums.LinerType;
import com.buchirano.automation.enums.MilitaryHonors;
import com.buchirano.automation.enums.ServiceActivityType;
import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating all mapped element paths and interaction methods
 * for the Interment Details screen within the Case Establishment workflow.
 *
 * <p><b>Screen:</b> Case Establishment — Interment Details</p>
 * <p><b>Layer:</b> Page Object (Application — NexusCM)</p>
 */
public class IntermentPage extends BasePageClass {

    public final String remainsType = "//*[@data-id='remainsType']//div/button";
    public final String serviceActivityType = "//*[@data-id='burialActivityType']//div/button";
    public final String cremainsType = "//*[@data-id='cremainsType']//div/button";
    public final String containerSize = "//*[@data-id='containerSize']//div/button";
    public final String linerType = "//*[@data-id='linerType']//div/button";
    public final String linerSize = "//*[@data-id='linerSize']//div/button";
    public final String sexualOffense = "(//*[@data-id='poiSO']//div/button)";
    public final String capitalCrime = "//*[@data-id='poiCC']//div/button";
    public final String authorityForBurial = "//*[@data-id='authorityForBurial']//div/button";
    public final String militaryHonors = "//*[@data-id='militaryHonors']//div/button";
    public final String familyRequestService = "//*[@data-id='familyRequestService']//div/button";
    public final String familyToWitness = "//*[@data-id='familyToWitnessInterment']//div/button";
    public final String emblem = "//*[@data-id='Emblem']//div/button";
    public final String cemeteryRegulationsButton = "//*/text()[normalize-space(.)='Cemetery Regulations']/parent::*";

    public AutomatedObject getRemainsTypeDropdown() { scrollIntoView(remainsType); return getElementByXPath(remainsType); }
    public AutomatedObject getServiceActivityType() { scrollIntoView(serviceActivityType); return getElementByXPath(serviceActivityType); }
    public AutomatedObject getCremainsType() { scrollIntoView(cremainsType); return getElementByXPath(cremainsType); }
    public AutomatedObject getContainerSize() { scrollIntoView(containerSize); return getElementByXPath(containerSize); }
    public AutomatedObject getLinerType() { scrollIntoView(linerType); return getElementByXPath(linerType); }
    public AutomatedObject getLinerSize() { scrollIntoView(linerSize); return getElementByXPath(linerSize); }
    public AutomatedObject getSexualOffenseCommitted() { scrollIntoView(sexualOffense); return getElementByXPath(sexualOffense); }
    public AutomatedObject getCapitalCrime() { scrollIntoView(capitalCrime); return getElementByXPath(capitalCrime); }
    public AutomatedObject getAuthorityForBurial() { scrollIntoView(authorityForBurial); return getElementByXPath(authorityForBurial); }
    public AutomatedObject getMilitaryHonors() { scrollIntoView(militaryHonors); return getElementByXPath(militaryHonors); }
    public AutomatedObject getFamilyRequestService() { scrollIntoView(familyRequestService); return getElementByXPath(familyRequestService); }
    public AutomatedObject getFamilyToWitnessInterment() { scrollIntoView(familyToWitness); return getElementByXPath(familyToWitness); }
    public AutomatedObject getEmblem() { scrollIntoView(emblem); return getElementByXPath(emblem); }
    public AutomatedObject getCemeteryRegulationsButton() { scrollIntoView(cemeteryRegulationsButton); return getElementByXPath(cemeteryRegulationsButton); }

    public void selectRemainsType(String remains) {
        if (remains == null) return;
        scrollIntoView(remainsType);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(remainsType)));
        selectDropdownOptionJSClick(getRemainsTypeDropdown(), remains);
        waitForSalesforceLoad();
    }

    public void selectServiceActivityType(ServiceActivityType serviceActivity) {
        if (serviceActivity == null) return;
        scrollIntoView(serviceActivityType);
        selectDropdownOptionJSClick(getServiceActivityType(), serviceActivity.getText());
        waitForSalesforceLoad();
    }

    public void selectCremainsType(CremainsType cremains) {
        if (cremains == null) return;
        scrollIntoView(cremainsType);
        selectDropdownOption(getCremainsType(), cremains.getText());
        waitForSalesforceLoad();
    }

    public void selectContainerSize(ContainerSize container) {
        if (container == null) return;
        scrollIntoView(containerSize);
        selectDropdownOption(getContainerSize(), container.getText());
        waitForSalesforceLoad();
    }

    public void selectLinerType(LinerType liner) {
        if (liner == null) return;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(linerType)));
        selectDropdownOption(getLinerType(), liner.getText());
        waitForSalesforceLoad();
    }

    public void selectLinerSize(LinerSize liner) {
        if (liner == null) return;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(linerSize)));
        selectDropdownOption(getLinerSize(), liner.getText());
        waitForSalesforceLoad();
    }

    public void selectSexualOffenseConvicted(GeneralResponse response) {
        if (response == null) return;
        selectDropdownGeneralOption(getSexualOffenseCommitted(), response.getText());
        waitForSalesforceLoad();
    }

    public void selectCapitalCrimeConvicted(GeneralResponse response) {
        if (response == null) return;
        selectDropdownGeneralOption(getCapitalCrime(), response.getText());
        waitForSalesforceLoad();
    }

    public void selectAuthorityForBurial(AuthorityForBurial authority) {
        if (authority == null) return;
        selectDropdownOptionJSClick(getAuthorityForBurial(), authority.getText());
        waitForSalesforceLoad();
    }

    public void selectMilitaryHonors(MilitaryHonors honors) {
        if (honors == null) return;
        scrollIntoView(militaryHonors);
        selectDropdownGeneralOption(getMilitaryHonors(), honors.getText());
        waitForSalesforceLoad();
    }

    public void selectFamilyRequestService(String response) {
        if (response == null) return;
        scrollIntoView(familyRequestService);
        selectDropdownGeneralOption(getFamilyRequestService(), response);
        waitForSalesforceLoad();
    }

    public void selectFamilyToWitnessInterment(String response) {
        if (response == null) return;
        scrollIntoView(familyToWitness);
        selectDropdownGeneralOption(getFamilyToWitnessInterment(), response);
        waitForSalesforceLoad();
    }

    public void selectEmblem(String emblemToSelect) {
        if (emblemToSelect == null) return;
        scrollIntoView(emblem);
        selectDropdownGeneralOption(getEmblem(), emblemToSelect);
        waitForSalesforceLoad();
    }

    public List<AutomatedObject> listOfAllFields() {
        return List.of(getRemainsTypeDropdown(), getServiceActivityType(),
                getSexualOffenseCommitted(), getCapitalCrime(), getFamilyRequestService(),
                getFamilyToWitnessInterment(), getAuthorityForBurial(),
                getMilitaryHonors(), getEmblem());
    }
}
