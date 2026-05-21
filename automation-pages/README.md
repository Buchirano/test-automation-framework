# automation-pages

**A production-grade Page Object Model library for enterprise Salesforce Lightning application test automation — designed and built from scratch.**

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.x-FF6C37)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?logo=jenkins&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub-Actions-2088FF?logo=github-actions&logoColor=white)

---

## Overview

This library is the Page Object Model layer in a 3-tier enterprise test automation architecture. It provides clean, reusable, and maintainable abstractions over complex Salesforce Lightning DOM structures for two application modules: **NexusCM** (case management) and **PortalRM** (resource management). Over 20 page classes, 9 portal page classes, and 28+ enum classes — all built to handle the specific rendering and interaction patterns of Salesforce Lightning at scale.

---

## Architecture

```
┌──────────────────────────────────────────────────────────────────┐
│                    TIER 1 — CORE                                 │
│      BasePageClass · WebDriver · Wait Strategies                 │
│      Search API · AutomatedObject · Action Utilities             │
└──────────────────────────┬───────────────────────────────────────┘
                           │ extends
┌──────────────────────────▼───────────────────────────────────────┐
│              TIER 2 — PAGE LAYER  ◄── THIS REPO                  │
│                                                                  │
│  pages/application/cameo/        enums/                          │
│  ├── StartPage                   ├── AuthorityForBurial          │
│  ├── ApplicantPage               ├── BranchOfService             │
│  ├── MilitaryPage                ├── RemainsType                 │
│  ├── ClaimantPage                ├── VerificationElement         │
│  └── 16 more...                 └── 24 more...                  │
│                                                                  │
│  pages/application/cerrt/                                        │
│  ├── SchedulePage                                                │
│  ├── AvailabilityPage                                            │
│  └── 7 more...                                                   │
└──────────────────────────┬───────────────────────────────────────┘
                           │ consumed by
┌──────────────────────────▼───────────────────────────────────────┐
│                TIER 3 — TEST SCRIPTS                             │
│      TestNG Tests · DataProvider · Jenkins Pipeline              │
└──────────────────────────────────────────────────────────────────┘
```

---

## Features

- **20+ NexusCM page classes** covering the full 10-step case management workflow
- **9 PortalRM page classes** covering scheduling, availability, regulations, and user management
- **28+ enum classes** — all valid dropdown values and picklist entries are strongly typed; no magic strings
- **Enum-driven field interactions** — all setter methods accept typed enums for IDE autocomplete and compile-time safety
- **Null-safe conditionals** — every interaction method guards against null or blank inputs, enabling clean data-driven testing
- **Salesforce Lightning compatibility patterns** — `jsClick`, `scrollIntoView`, `waitForSalesforceLoad`, `data-id` attribute targeting
- **Dynamic row management** — handles indefinite runtime rows (military service entries, additional contacts)
- **Date computation utilities** — `java.time`-based helpers for relative date navigation; no `Thread.sleep`, no hardcoded dates
- **XPath-based verification** — `VerificationElement` enum stores all page-load verification XPaths for expressive wait calls

---

## Tech Stack

| Technology | Version | Role |
|---|---|---|
| Java | 11+ | Primary language |
| Selenium WebDriver | 4.x | Browser automation |
| TestNG | 7.x | Test framework (consumed by scripts layer) |
| Maven | 3.x | Build and dependency management |
| Salesforce Lightning | — | Target application platform |

---

## Repository Structure

```
automation-pages/
└── src/main/java/com/buchirano/automation/
    ├── pages/
    │   ├── general/
    │   │   ├── BasePageClass.java              # Abstract WebDriver wrapper
    │   │   ├── CaseDetailsPage.java            # Case Details dashboard page
    │   │   └── LoginPage.java                  # Multi-environment login page
    │   └── application/
    │       ├── cameo/                           # NexusCM — 20 page classes
    │       │   ├── StartPage.java
    │       │   ├── ApplicantPage.java
    │       │   ├── ApplicantSearchModal.java
    │       │   ├── MilitaryPage.java
    │       │   ├── ClaimantPage.java
    │       │   ├── OrganizationsPage.java
    │       │   ├── OrganizationsSearchModal.java
    │       │   ├── PersonalRepresentativePage.java
    │       │   ├── AdditionalContactsPage.java
    │       │   ├── IntermentPage.java
    │       │   ├── SchedulingPage.java
    │       │   ├── SummaryPage.java
    │       │   ├── CaMEOGeneralNavigationPage.java
    │       │   ├── CaseEstablishmentHeaderPage.java
    │       │   ├── CaseEstablishmentsPage.java
    │       │   ├── ReportsTabPage.java
    │       │   ├── DecedentSearchModalPage.java
    │       │   ├── ModalPageClass.java
    │       │   ├── CemeteryRegulationsPage.java
    │       │   └── SpecialGuidancePage.java
    │       └── cerrt/                           # PortalRM — 9 page classes
    │           ├── HomePage.java
    │           ├── CemeteryDetailsPage.java
    │           ├── SchedulePage.java
    │           ├── AvailabilityPage.java
    │           ├── CemeteryRegulationsPage.java
    │           ├── UsersPage.java
    │           ├── ActivityPage.java
    │           ├── ModalClass.java
    │           └── CeRRTGeneralNavigationPage.java
    └── enums/                                   # 28+ enum classes
        ├── RemainsType.java
        ├── ServiceActivityType.java
        ├── VerificationElement.java
        ├── BranchOfService.java
        ├── AuthorityForBurial.java
        └── 23 more...
```

---

## Application Coverage

### NexusCM — Case Management Module

Full automation coverage of the **10-step Case Management workflow**:

| Step | Screen | Page Class |
|---|---|---|
| 1 | Start | `StartPage` |
| 2 | Applicant Details | `ApplicantPage` |
| 3 | Military Service | `MilitaryPage` |
| 4 | Claimant Details | `ClaimantPage` |
| 5 | Organizations | `OrganizationsPage` |
| 6 | Personal Representative | `PersonalRepresentativePage` |
| 7 | Additional Contacts | `AdditionalContactsPage` |
| 8 | Interment Details | `IntermentPage` |
| 9 | Scheduling | `SchedulingPage` |
| 10 | Summary | `SummaryPage` |

### PortalRM — Resource Management Module

| Screen | Page Class |
|---|---|
| Home | `HomePage` |
| Location Details | `CemeteryDetailsPage` |
| Schedule | `SchedulePage` |
| Availability | `AvailabilityPage` |
| Regulations | `CemeteryRegulationsPage` |
| Users | `UsersPage` |
| Activity | `ActivityPage` |
| Shared Modal | `ModalClass` |
| Navigation | `CeRRTGeneralNavigationPage` |

---

## Design Patterns

### Enum-Driven Field Interactions

All dropdown selections are backed by strongly-typed enums:

```java
// Type-safe, self-documenting, IDE autocomplete
interment.selectRemainsType(RemainsType.CASKET);
interment.selectAuthorityForBurial(AuthorityForBurial.DISCHARGE_DOCUMENT);
interment.selectServiceActivityType(ServiceActivityType.DIRECT_INTERMENT);
interment.selectEmblem(Emblem.BUDDHIST);
```

### Null-Safe Conditionals

Optional fields are skipped gracefully when data is absent:

```java
if (militaryStatus != null) selectDropdownOption(getMilitaryStatusDropdown(), militaryStatus);
if (suffix != null && !suffix.isBlank()) selectDropdownGeneralOption(getSuffixDropdown(), suffix);
```

### Salesforce Lightning Compatibility

Every interaction accounts for Lightning rendering specifics:

```java
// JS click for elements present but not natively clickable
jsClick(getChevron("Summary"));

// Scroll before interaction for off-screen Lightning components
scrollIntoView(firstNameElementSelector);
getElementByXPath(firstNameElementSelector);

// Wait for Lightning async rendering after every state change
waitForSalesforceLoad();

// data-id targeting over dynamic Salesforce-generated IDs
public final String firstNameElementSelector = "//*[@data-id='vetFirstName']//input";
```

### XPath Verification

`VerificationElement` enum enables expressive page-load assertions:

```java
waitForVisibility(VerificationElement.MILITARY_CHEVRON);
waitForVisibility(VerificationElement.ORGANIZATIONS_CHEVRON);
```

---

## Notable Engineering Highlights

- **Dynamic row management** — `MilitaryPage` and `AdditionalContactsPage` handle an indefinite number of rows added at runtime via list-based element retrieval and row-aware XPath construction
- **Iframe-aware reporting** — `ReportsTabPage` uses direct `WebElement` for elements inside the Salesforce Report Viewer iframe
- **Email validation** — `OrganizationsPage` includes an RFC-compliant static email validator for identifying valid contacts in search results
- **Character limit testing** — `CemeteryRegulationsPage` generates random strings of controlled length to validate Salesforce rich text field character limits
- **Date computation** — `SchedulePage` provides `java.time`-based date helpers for relative past/future navigation with no hardcoded dates

---

## How to Run

```bash
git clone https://github.com/buchirano/automation-pages.git
cd automation-pages
mvn clean install -DskipTests
```

Add as a Maven dependency:

```xml
<dependency>
    <groupId>com.buchirano.automation</groupId>
    <artifactId>automation-pages</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## Related Repositories

| Repository | Description |
|---|---|
| [`test-automation-framework`](https://github.com/buchirano/test-automation-framework) | Jenkins CI/CD pipeline, Salesforce CLI auth, Slack integration |
| [`automation-scripts`](https://github.com/buchirano/automation-scripts) | Modular and execution test scripts for NexusCM and PortalRM |

---

## Contact

**Buchi Uwakwe**
- Email: buchiuwakwe@boltzintelligence.com
- GitHub: [github.com/buchirano](https://github.com/buchirano)
