package com.buchirano.automation.pages.application.cameo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.buchirano.automation.enums.AuthorityForBurial;
import com.buchirano.automation.enums.BranchOfService;
import com.buchirano.automation.enums.ContactType;
import com.buchirano.automation.enums.Emblem;
import com.buchirano.automation.enums.GeneralResponse;
import com.buchirano.automation.enums.MilitaryRank;
import com.buchirano.automation.enums.RemainsType;
import com.buchirano.automation.enums.ServiceActivityType;
import com.buchirano.automation.enums.VerificationElement;
import com.buchirano.automation.enums.WarPeriod;
import com.buchirano.automation.pages.general.BasePageClass;
import com.buchirano.automation.core.AutomatedObject;

/**
 * Page class encapsulating tab-level navigation and workflow traversal methods
 * for the CaMEO Case Establishment application.
 *
 * <p>This class provides click handlers for all top-level tabs, all 10 Case
 * Establishment chevrons, Next/Previous button navigation, and convenience
 * methods to navigate directly to any screen by pre-populating all preceding screens.</p>
 *
 * <p><b>Application:</b> CaMEO</p>
 * <p><b>Layer:</b> Page Object (Application — Navigation)</p>
 */
public class CaMEOGeneralNavigationPage extends BasePageClass {

    // ========================== Dependent Page Objects ==========================

    private final StartPage startScreen = new StartPage();
    private final VeteranPage veteranScreen = new VeteranPage();
    private final MilitaryPage military = new MilitaryPage();
    private final ClaimantPage claimant = new ClaimantPage();
    private final PersonalRepresentativePage personalRep = new PersonalRepresentativePage();
    private final AdditionalContactsPage additionalContacts = new AdditionalContactsPage();
    private final IntermentPage interment = new IntermentPage();
    private final SummaryPage summary = new SummaryPage();

    // ========================== XPath Constants ==========================

    /** XPath for the Next button in Case Establishment. */
    public String nextButton = "//button[@title='Go to next screen']";

    /** XPath for the Previous button in Case Establishment. */
    public String previousButton = "//button[@title='Go to previous screen']";

    /** XPath for all Case Establishment chevron list items. */
    public String chevrons = "//ul[@data-id='navigationPath']//li";

    // ========================== Element Accessors ==========================

    /** @return list of all Case Establishment chevron elements */
    public List<AutomatedObject> getChevrons() { return getElementsByXPath(chevrons); }

    /**
     * Returns the Home tab element.
     *
     * @return {@link AutomatedObject} for the Home tab
     */
    public AutomatedObject getHomeTab() {
        String path = "//a[@title='Home']/parent::*";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the Case Establishment tab element.
     *
     * @return {@link AutomatedObject} for the Case Establishment tab
     */
    public AutomatedObject getCaseEstablishmentTab() {
        String path = "//a[@title='Case Establishment']/parent::*";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the Reports tab element.
     *
     * @return {@link AutomatedObject} for the Reports tab
     */
    public AutomatedObject getReportsTab() {
        String path = "//a[@title='Reports']/parent::*";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the MBMS Case Details tab element.
     *
     * @return {@link AutomatedObject} for the MBMS Case Details tab
     */
    public AutomatedObject getCaseDetailsTab() {
        String path = "//a[@title='MBMS Case Details']/parent::*";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the chevron element for the specified step name.
     *
     * @param chevron the chevron name as displayed (e.g., "Start", "Veteran", "Military")
     * @return {@link AutomatedObject} for the specified chevron
     */
    public AutomatedObject getChevron(String chevron) {
        String path = "//li[@title='" + chevron + "' and @data-step]";
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the currently active chevron tab element.
     *
     * @return {@link AutomatedObject} for the active chevron tab
     */
    public AutomatedObject getActiveTab() {
        String path = "//a[@aria-selected='true' and @data-step]";
        waitForClickable(path);
        scrollIntoView(path);
        return getElementByXPath(path);
    }

    /**
     * Returns the View Profile button in the Salesforce global header.
     *
     * @return {@link AutomatedObject} for the View Profile button
     */
    public AutomatedObject getViewProfileButton() {
        return getElementByXPath(
                "//button[@class='slds-button branding-userProfile-button slds-button "
                + "slds-global-actions__avatar slds-global-actions__item-action']");
    }

    /** @return {@link AutomatedObject} for the Log Out link in the quick view panel */
    public AutomatedObject getLogOut() { return getElementByXPath("//a[text() = 'Log out']"); }

    /** @return {@link AutomatedObject} for the logged-in user name link */
    public AutomatedObject getUserName() { return getElementByXPath("//h1//a"); }

    // ========================== Tab Navigation ==========================

    /** Clicks the Reports tab and waits for the page to stabilize. */
    public void clickReportsTab() {
        getReportsTab().click();
        waitForSalesforceLoad();
        waitForVisibility(VerificationElement.RECENT_REPORTS);
    }

    /** Clicks the MBMS Case Details tab and waits for the page to stabilize. */
    public void clickCaseDetailsTab() {
        getCaseDetailsTab().click();
        waitForSalesforceLoad();
        waitForVisibility(VerificationElement.MBMS_CASE_DETAILS_TAB);
    }

    /** Clicks the Case Establishment tab and waits for the Start chevron. */
    public void clickCaseEstablishment() {
        getCaseEstablishmentTab().click();
        waitForSalesforceLoad();
        waitForVisibility(VerificationElement.START_CHEVRON);
    }

    /** Clicks the Home tab and waits for the Home tab to become current. */
    public void clickHomeTab() {
        getHomeTab().click();
        waitForSalesforceLoad();
        waitForVisibility(VerificationElement.HOME_TAB);
    }

    // ========================== Chevron Navigation ==========================

    /** Clicks the Next button and waits for the DOM to stabilize. */
    public void clickNextButton() {
        scrollIntoView(nextButton);
        waitForSalesforceLoad();
        getElementByXPath(nextButton).click();
        waitForSalesforceLoad();
    }

    /** Clicks the Previous button and waits for the DOM to stabilize. */
    public void clickPreviousButton() {
        scrollIntoView(previousButton);
        waitForSalesforceLoad();
        getElementByXPath(previousButton).click();
        waitForSalesforceLoad();
    }

    /**
     * Traverses backward through all Case Establishment screens using the Previous button.
     *
     * @return action description string for test reporting
     */
    public String traverseBackwardsByPrevButton() {
        int totalScreens = getChevrons().size();
        for (int i = totalScreens; i > 1; i--) {
            clickPreviousButton();
            waitForSalesforceLoad();
            handleToastMessage();
        }
        waitForSalesforceLoad();
        waitForSalesforceLoad();
        return "Traversed backwards using the 'Previous' button. Landed on "
                + getCurrentChevron() + " Screen";
    }

    /**
     * Traverses forward through all Case Establishment screens by clicking chevrons directly.
     *
     * @return action description string for test reporting
     */
    public String traverseForwardsByChevron() {
        scrollIntoView(chevrons);
        int currentChevron = Integer.parseInt(getActiveTab().getPropertyValue("data-step"));
        for (int i = currentChevron; i < 11; i++) {
            if (i < 6) getChevrons().get(i).click();
            else getChevrons().get(i - 1).click();
            waitForSalesforceLoad();
        }
        return "Traversed forward by clicking chevrons. Landed on " + getCurrentChevron() + " Screen";
    }

    /**
     * Returns the name of the currently active chevron step.
     *
     * @return the chevron name string (e.g., "Start", "Veteran", "Summary")
     */
    public String getCurrentChevron() {
        String path = "(//a[@aria-selected='true']//span)[3]";
        scrollIntoView(path);
        return getElementByXPath(path).readText();
    }

    /**
     * Checks if the current screen matches the expected chevron name.
     *
     * @param expectedChevronName the chevron name to verify against
     * @return true if the active tab contains the expected name
     */
    public Boolean verifyCurrentChevron(String expectedChevronName) {
        return readText(getActiveTab()).contains(expectedChevronName);
    }

    /** Clicks the Start chevron and waits for the Start screen to load. */
    public void clickStartChevron() {
        scrollIntoView("//li[@title='Start']");
        jsClick(getChevron("Start"));
        if (!isPresent(VerificationElement.START_CHEVRON.getXpathValue())) {
            jsClick(getChevron("Start"));
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.START_CHEVRON);
    }

    /** Clicks the Veteran chevron and waits for the Veteran screen to load. */
    public void clickVeteranChevron() {
        scrollIntoView("//li[@title='Veteran']");
        jsClick(getChevron("Veteran"));
        if (!isPresent(VerificationElement.VETERAN_CHEVRON.getXpathValue())) {
            jsClick(getChevron("Veteran"));
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.VETERAN_CHEVRON);
    }

    /** Clicks the Military chevron and waits for the Military screen to load. */
    public void clickMilitaryChevron() {
        scrollIntoView("//li[@title='Military']");
        jsClick(getChevron("Military"));
        if (!isPresent(VerificationElement.MILITARY_CHEVRON.getXpathValue())) {
            jsClick(getChevron("Military"));
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.MILITARY_CHEVRON);
    }

    /** Clicks the Claimant chevron and waits for the Claimant screen to load. */
    public void clickClaimantChevron() {
        scrollIntoView("//li[@title='Claimant']");
        jsClick(getChevron("Claimant"));
        if (!isPresent(VerificationElement.CLAIMANT_CHEVRON.getXpathValue())) {
            jsClick(getChevron("Claimant"));
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.CLAIMANT_CHEVRON);
    }

    /** Clicks the Organizations chevron and waits for the Organizations screen to load. */
    public void clickOrganizationsChevron() {
        scrollIntoView("//li[@title='Organizations']");
        jsClick(getChevron("Organizations"));
        if (!isPresent(VerificationElement.ORGANIZATIONS_CHEVRON.getXpathValue())) {
            getChevron("Organizations").click();
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.ORGANIZATIONS_CHEVRON);
    }

    /** Clicks the Personal Representative chevron and waits for that screen to load. */
    public void clickPersonalRepresentativeChevron() {
        scrollIntoView("//li[@title='Personal Representative']");
        jsClick(getChevron("Personal Representative"));
        if (!isPresent(VerificationElement.PERSONAL_REPRESENTATIVE_CHEVRON.getXpathValue())) {
            getChevron("Personal Representative").click();
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.PERSONAL_REPRESENTATIVE_CHEVRON);
    }

    /** Clicks the Additional Contacts chevron and waits for that screen to load. */
    public void clickAdditionalContactsChevron() {
        scrollIntoView("//li[@title='Additional Contacts']");
        jsClick(getChevron("Additional Contacts"));
        waitForSalesforceLoad();
        if (!isPresent(VerificationElement.ADDITIONAL_CONTACTS_CHEVRON.getXpathValue())) {
            getChevron("Additional Contacts").click();
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.ADDITIONAL_CONTACTS_CHEVRON);
    }

    /** Clicks the Interment chevron and waits for the Interment screen to load. */
    public void clickIntermentChevron() {
        scrollIntoView("//li[@title='Interment']");
        getChevron("Interment").click();
        waitForSalesforceLoad();
        if (!isPresent(VerificationElement.INTERMENT_CHEVRON.getXpathValue())) {
            getChevron("Interment").click();
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.INTERMENT_CHEVRON);
    }

    /** Clicks the Scheduling chevron and waits for the Scheduling screen to load. */
    public void clickSchedulingChevron() {
        scrollIntoView("//li[@title='Scheduling']");
        getChevron("Scheduling").click();
        waitForSalesforceLoad();
        if (!isPresent(VerificationElement.SCHEDULING_CHEVRON.getXpathValue())) {
            getChevron("Scheduling").click();
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.SCHEDULING_CHEVRON);
    }

    /** Clicks the Summary chevron and waits for the Summary screen to load. */
    public void clickSummaryChevron() {
        scrollIntoView("//li[@title='Summary']");
        getChevron("Summary").click();
        if (!isPresent(VerificationElement.SUMMARY_CHEVRON.getXpathValue())) {
            jsClick(getChevron("Summary"));
            waitForSalesforceLoad();
        }
        waitForVisibility(VerificationElement.SUMMARY_CHEVRON);
    }

    // ========================== Screen Navigation Shortcuts ==========================

    /** Navigates to the Veteran screen by completing the Start screen. */
    public void goToVeteranScreen() {
        startScreen.clickCreateNewCaseButton();
        populateStartScreen("Subsequent");
        clickNextButton();
    }

    /** Navigates to the Military screen by completing all preceding screens. */
    public void goToMilitaryScreen() {
        goToVeteranScreen();
        sleep(3);
        pageScrollDown();
        populateVeteranScreen();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Claimant screen by completing all preceding screens. */
    public void goToClaimantScreen() {
        goToMilitaryScreen();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Organizations screen by completing all preceding screens. */
    public void goToOrganizationsScreen() {
        goToClaimantScreen();
        populateClaimantScreen();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Personal Representative screen by completing all preceding screens. */
    public void goToPersonalRepresentativeScreen() {
        goToOrganizationsScreen();
        pageScrollDown();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Additional Contacts screen by completing all preceding screens. */
    public void goToAdditionalContactsScreen() {
        goToPersonalRepresentativeScreen();
        populatePersonalRepresentativeScreen();
        pageScrollDown();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Interment screen by completing all preceding screens. */
    public void goToIntermentScreen() {
        goToAdditionalContactsScreen();
        populateAdditionalContactsScreen();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Scheduling screen by completing all preceding screens. */
    public void goToSchedulingScreen() {
        goToIntermentScreen();
        populateIntermentScreen();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Summary screen by completing all preceding screens. */
    public void goToSummaryScreen() {
        goToSchedulingScreen();
        clickNextButton();
        waitForSalesforceLoad();
    }

    /** Navigates to the Summary screen and performs a case transfer. */
    public void goToSummaryScreenAndTransferCase() {
        goToSummaryScreen();
        summary.performCaseTransferOperations("BOSS NCSO");
        waitForSalesforceLoad();
    }

    // ========================== Screen Population Helpers ==========================

    /**
     * Populates the Start screen with a cemetery selection and interment type.
     *
     * @param intermentType "First" or "Subsequent"
     */
    public void populateStartScreen(String intermentType) {
        startScreen.selectCemetery("FT. CUSTER NATIONAL CEMETERY - 909");
        startScreen.selectIntermentType(intermentType);
        waitForSalesforceLoad();
    }

    /** Populates all required fields on the Veteran Details screen. */
    public void populateVeteranScreen() {
        fillInputField(veteranScreen.getFirstNameInput(), "AutomatedVetFirst");
        fillInputField(veteranScreen.getLastNameInput(), "Test Vet");
        pageScrollDown();
        selectDropdownOption(veteranScreen.getMilitaryStatusDropdown(), "VETERAN");
        selectDropdownOption(veteranScreen.getServiceEligibilityDropdown(), "Yes");
        waitForSalesforceLoad();
    }

    /** Populates all required fields on the Military Service Details screen. */
    public void populateMilitaryScreen() {
        List<WarPeriod> warPeriods = new ArrayList<>(Arrays.asList(WarPeriod.PANAMA, WarPeriod.PERSIAN_GULF));
        military.addMilitaryServiceInformation(BranchOfService.V2_AR_US_ARMY, MilitaryRank.FIRST_LT,
                "09/05/1985", "01/01/2021", warPeriods, "", "Under Honorable Condition");
        waitForSalesforceLoad();
    }

    /** Populates all required fields on the Claimant Details screen. */
    public void populateClaimantScreen() {
        String ssn = randomSSN();
        claimant.selectRelationshipToVeteran("WIFE");
        claimant.enterPersonalInformation("Populate", "ClaimantScreen", "Automation",
                "--None--", ssn, "Female", "Married", "(888)888-888", "AutomationClaimant@email.com");
        claimant.enterDates("01/01/1919", "01/01/2019");
        claimant.enterDecedentHomeOfRecord("42 Road Street", "Apartment Q", "27514", "Yes");
        claimant.enterDemographics("Other", "Automation", "Hispanic or Latino", "Other Race", "Automation");
        claimant.clickSearchUrnOrPlaque();
    }

    /** Populates all required fields on the Personal Representative Details screen. */
    public void populatePersonalRepresentativeScreen() {
        personalRep.enterNameFields("AutomationRepFirst", "AutomationRepMiddle", "AutomationRepLast", "JUNIOR");
        personalRep.enterAdditionalFields("HUSBAND", personalRep.randomSSN(), "7278675309",
                BasePageClass.generateTestEmailAddress());
        personalRep.enterAddressFields("123 Rep Street", "Unit 2");
    }

    /** Populates all required fields on the Additional Contacts Details screen. */
    public void populateAdditionalContactsScreen() {
        additionalContacts.clickDeleteRow(1);
        additionalContacts.clickCreateNewContactButton();
        additionalContacts.enterNewContactName("Additional", "Automated", "Contact", "SECOND");
        additionalContacts.enterNewContactAdditionalInfo("01/01/1950", "SISTER", false, false);
        additionalContacts.selectContactType(ContactType.PERSONS_ELIGIBLE.getText(), 1);
        waitForSalesforceLoad();
    }

    /** Populates all required fields on the Interment Details screen. */
    public void populateIntermentScreen() {
        interment.selectRemainsType(RemainsType.NO_REMAINS.getText());
        interment.selectSexualOffenseConvicted(GeneralResponse.NO);
        interment.selectCapitalCrimeConvicted(GeneralResponse.NO);
        interment.selectAuthorityForBurial(AuthorityForBurial.DISCHARGE_DOCUMENT);
        interment.selectServiceActivityType(ServiceActivityType.DIRECT_INTERMENT);
        interment.selectFamilyRequestService(GeneralResponse.NO.getText());
        interment.selectFamilyToWitnessInterment(GeneralResponse.NO.getText());
        interment.selectEmblem(Emblem.BUDDHIST.getText());
        waitForSalesforceLoad();
    }

    // ========================== User Session Helpers ==========================

    /** Clicks the View Profile button and waits for the DOM to stabilize. */
    public void clickViewProfile() { getViewProfileButton().click(); waitForSalesforceLoad(); }

    /** Clicks the Log Out link and waits for the DOM to stabilize. */
    public void clickLogOut() { getLogOut().click(); waitForSalesforceLoad(); }

    /**
     * Returns the name of the currently logged-in user.
     *
     * @return the logged-in user's name string
     */
    public String readLoggedInUser() { clickViewProfile(); return getUserName().readText(); }
}
