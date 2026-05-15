# automation-pages

[![Java](https://img.shields.io/badge/Java-11%2B-orange?style=flat-square&logo=java)](https://www.java.com)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-green?style=flat-square&logo=selenium)](https://www.selenium.dev)
[![TestNG](https://img.shields.io/badge/TestNG-7.x-blue?style=flat-square)](https://testng.org)
[![Pattern](https://img.shields.io/badge/Pattern-Page%20Object%20Model-purple?style=flat-square)]()
[![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)](LICENSE)

A production-grade **Page Object Model (POM)** library for end-to-end UI test automation of enterprise Salesforce Lightning applications. This repository serves as the page layer in a three-tier automation architecture, providing clean, reusable, and maintainable abstractions over complex Salesforce DOM structures.

---

## Architecture

This library sits at **Tier 2** of a three-tier automation stack:

```
┌──────────────────────────────────────────────────────────┐
│                    TIER 1 — CORE                         │
│      BasePageClass · WebDriver · Wait Strategies         │
│      Search API · AutomatedObject · Action Utilities     │
└─────────────────────┬────────────────────────────────────┘
                      │ extends
┌─────────────────────▼────────────────────────────────────┐
│              TIER 2 — PAGE LAYER  ◄── THIS REPO          │
│                                                          │
│  pages/application/cameo/     enums/                     │
│  ├── StartPage                ├── AuthorityForBurial     │
│  ├── VeteranPage              ├── BranchOfService        │
│  ├── MilitaryPage             ├── RemainsType            │
│  ├── ClaimantPage             ├── VerificationElement    │
│  └── 16 more...              └── 24 more...             │
│                                                          │
│  pages/application/cerrt/                                │
│  ├── SchedulePage                                        │
│  ├── AvailabilityPage                                    │
│  └── 7 more...                                          │
└─────────────────────┬────────────────────────────────────┘
                      │ consumed by
┌─────────────────────▼────────────────────────────────────┐
│                TIER 3 — TEST SCRIPTS                     │
│      TestNG Tests · Data Providers · Jenkins Pipeline    │
└──────────────────────────────────────────────────────────┘
```

**Layer responsibilities:**
- **Page classes** own all UI interactions — XPaths, element accessors, and Salesforce-specific interaction patterns
- **Enums** own all valid field values — no magic strings scattered across test scripts
- **Test scripts** (separate repo) own assertions and test flow

---

## Repository Structure

```
automation-pages/
└── src/main/java/com/buchirano/automation/
    ├── pages/
    │   ├── general/
    │   │   ├── BasePageClass.java
    │   │   ├── CaseDetailsPage.java
    │   │   └── LoginPage.java
    │   └── application/
    │       ├── cameo/                    (20 page classes)
    │       │   ├── StartPage.java
    │       │   ├── VeteranPage.java
    │       │   ├── MilitaryPage.java
    │       │   ├── ClaimantPage.java
    │       │   ├── OrganizationsPage.java
    │       │   ├── PersonalRepresentativePage.java
    │       │   ├── AdditionalContactsPage.java
    │       │   ├── IntermentPage.java
    │       │   ├── SchedulingPage.java
    │       │   ├── SummaryPage.java
    │       │   └── 10 supporting classes...
    │       └── cerrt/                    (9 page classes)
    │           ├── SchedulePage.java
    │           ├── AvailabilityPage.java
    │           ├── CemeteryRegulationsPage.java
    │           ├── UsersPage.java
    │           └── 5 supporting classes...
    └── enums/                            (28 enum classes)
        ├── RemainsType.java
        ├── ServiceActivityType.java
        ├── VerificationElement.java
        └── 25 more...
```

---

## Application Coverage

### CaMEO — Case Management and Eligibility Operations

Full automation coverage of the **10-step Case Establishment workflow**:

| Step | Screen | Page Class |
|------|--------|------------|
| 1 | Start | `StartPage` |
| 2 | Veteran Details | `VeteranPage` |
| 3 | Military Service | `MilitaryPage` |
| 4 | Claimant Details | `ClaimantPage` |
| 5 | Organizations | `OrganizationsPage` |
| 6 | Personal Representative | `PersonalRepresentativePage` |
| 7 | Additional Contacts | `AdditionalContactsPage` |
| 8 | Interment Details | `IntermentPage` |
| 9 | Scheduling | `SchedulingPage` |
| 10 | Summary | `SummaryPage` |

**Supporting classes:** `CaMEOGeneralNavigationPage`, `CaseEstablishmentHeaderPage`, `ReportsTabPage`, `DecedentSearchModalPage`, `ModalPageClass`, `VeteranSearchModal`, `OrganizationsSearchModal`, `CemeteryRegulationsPage`, `SpecialGuidancePage`, `CaseEstablishmentsPage`

### CeRRT — Cemetery Regulations and Reservation Tool

Full automation coverage of all CeRRT cemetery management screens:

| Screen | Page Class |
|--------|------------|
| Home | `HomePage` |
| Cemetery Details | `CemeteryDetailsPage` |
| Schedule | `SchedulePage` |
| Availability | `AvailabilityPage` |
| Cemetery Regulations | `CemeteryRegulationsPage` |
| MBMS Users | `UsersPage` |
| Activity | `ActivityPage` |
| Shared Modal | `ModalClass` |
| Navigation | `CeRRTGeneralNavigationPage` |

---

## Design Principles

### Enum-Driven Field Interactions

All dropdown selections and picklist values are backed by strongly-typed enums, eliminating magic strings and providing IDE autocomplete:

```java
// Without enums — brittle, error-prone
interment.selectRemainsType("Casket");

// With enums — type-safe, self-documenting
interment.selectRemainsType(RemainsType.CASKET);
interment.selectAuthorityForBurial(AuthorityForBurial.DISCHARGE_DOCUMENT);
interment.selectServiceActivityType(ServiceActivityType.DIRECT_INTERMENT);
```

### Null-Safe Conditional Interactions

All setter methods guard against null inputs, enabling clean data-driven test scenarios where optional fields may be absent:

```java
public void selectRemainsType(String remains) {
    if (remains == null) return;
    scrollIntoView(remainsType);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(remainsType)));
    selectDropdownOptionJSClick(getRemainsTypeDropdown(), remains);
    waitForSalesforceLoad();
}
```

### Salesforce Lightning Compatibility

Salesforce Lightning introduces specific automation challenges. This library addresses them consistently:

- `jsClick()` for elements present in the DOM but not natively clickable due to Lightning rendering
- `scrollIntoView()` before every interaction to handle off-screen Lightning components
- `waitForSalesforceLoad()` after every state-changing action
- `data-id` attribute targeting over dynamic Salesforce-generated element IDs
- `ExpectedConditions` fluent waits for asynchronous Lightning component rendering

### XPath-Based Verification

The `VerificationElement` enum stores XPath expressions for common page-load verification points, enabling expressive wait calls without raw strings in test scripts:

```java
waitForVisibility(VerificationElement.MILITARY_CHEVRON);
waitForVisibility(VerificationElement.ORGANIZATIONS_CHEVRON);
```

---

## Notable Engineering Highlights

**Dynamic row management** — `MilitaryPage` handles an indefinite number of military service rows added at runtime, using list-based element retrieval with 0-based indexing and row-aware XPath construction.

**Iframe-aware reporting** — `ReportsTabPage` uses direct `WebElement` for elements inside the Salesforce Report Viewer iframe where standard Selenium focus alignment is insufficient.

**Email validation** — `OrganizationsPage` includes a static RFC-compliant email validator used to programmatically identify valid contacts in search results without relying on UI state.

**Character limit testing** — `CemeteryRegulationsPage` programmatically generates random strings of controlled length to validate Salesforce rich text field character limits and error recovery behavior.

**Date computation utilities** — `SchedulePage` provides a full suite of `java.time`-based date helpers — no `Thread.sleep`, no hardcoded dates. Tests navigate dynamically to the first Monday of next month, the next occurrence of any weekday, or any relative past/future date.

---

## Usage Examples

**Completing the Interment Details screen:**

```java
IntermentPage interment = new IntermentPage();

interment.selectRemainsType(RemainsType.NO_REMAINS.getText());
interment.selectAuthorityForBurial(AuthorityForBurial.DISCHARGE_DOCUMENT);
interment.selectServiceActivityType(ServiceActivityType.DIRECT_INTERMENT);
interment.selectSexualOffenseConvicted(GeneralResponse.NO);
interment.selectCapitalCrimeConvicted(GeneralResponse.NO);
interment.selectFamilyRequestService(GeneralResponse.NO.getText());
interment.selectFamilyToWitnessInterment(GeneralResponse.NO.getText());
interment.selectEmblem(Emblem.BUDDHIST.getText());
```

**Creating an availability record in CeRRT:**

```java
AvailabilityPage availability = new AvailabilityPage();

availability.createNewAvailability(
    "Morning Interment Block",
    Impact.CAPACITY,
    ServiceInterval.THIRTY,
    "7:30 AM",
    "11:30 AM",
    RemainsType.CASKET,
    ServiceActivityType.INTERMENT_FIRST_CASKET,
    "Ossuary",
    Honors.HONORS
);
```

**Navigating chevrons and verifying screen state:**

```java
CaMEOGeneralNavigationPage navigate = new CaMEOGeneralNavigationPage();

navigate.clickMilitaryChevron();
navigate.waitForVisibility(VerificationElement.MILITARY_CHEVRON);
assertTrue(navigate.verifyCurrentChevron("Military"));
```

---

## Technology Stack

| Technology | Version | Role |
|------------|---------|------|
| Java | 11+ | Primary language |
| Selenium WebDriver | 4.x | Browser automation |
| TestNG | 7.x | Test framework (consumed by test-scripts layer) |
| Maven | 3.x | Build and dependency management |
| Salesforce Lightning | — | Target application platform |

---

## Related Repositories

| Repository | Description |
|------------|-------------|
| [`test-automation-framework`](https://github.com/buchirano/test-automation-framework) | Jenkins CI/CD pipeline, Salesforce CLI auth, Slack integration, HTML reporting |
| [`automation-scripts`](https://github.com/buchirano/automation-scripts) | Modular and execution test scripts for CeRRT and CaMEO |

---

## Getting Started

```bash
git clone https://github.com/buchirano/automation-pages.git
cd automation-pages
mvn clean install -DskipTests
```

Add as a Maven dependency in your test project:

```xml
<dependency>
    <groupId>com.buchirano.automation</groupId>
    <artifactId>automation-pages</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## Author

**Chimbuchi Uwakwe** — QA Automation Engineer · DevOps Practitioner  
[github.com/buchirano](https://github.com/buchirano) · Harrisburg, PA
