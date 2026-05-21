# automation-scripts

**Modular and execution test scripts for end-to-end regression of enterprise Salesforce Lightning applications — designed and built from scratch.**

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.x-FF6C37)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?logo=jenkins&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub-Actions-2088FF?logo=github-actions&logoColor=white)

---

## Overview

This repository is **Tier 3** of a 3-tier enterprise test automation architecture. It delivers complete end-to-end regression coverage for two Salesforce Lightning application modules: **NexusCM** (case management) and **PortalRM** (resource management). Scripts are organized into two layers — modular and execution — and driven by an external JSON DataProvider pattern for maintainable, data-driven testing.

---

## Architecture

```
┌──────────────────────────────────────────────────────────────────┐
│                    TIER 1 — CORE                                 │
│         BasePageClass · WebDriver · Wait Strategies              │
└──────────────────────────┬───────────────────────────────────────┘
                           │
┌──────────────────────────▼───────────────────────────────────────┐
│              TIER 2 — PAGE LAYER                                 │
│         automation-pages (page classes + enums)                  │
└──────────────────────────┬───────────────────────────────────────┘
                           │
┌──────────────────────────▼───────────────────────────────────────┐
│          TIER 3 — TEST SCRIPTS  ◄── THIS REPO                    │
│                                                                  │
│  testscript/modular/          testscript/execution/              │
│  ├── cerrt/  (13 scripts)     ├── cerrt/  (3 scripts)            │
│  └── cameo/  (14 scripts)     └── cameo/  (3 scripts)            │
│                                                                  │
│  dataSets/master/             utilities/                         │
│  ├── loginData/               ├── DataReader.java                │
│  └── scriptData/              ├── RetryFailedXrayPosts.java      │
│      ├── cerrt/               └── EncryptAuthenticationFile.java │
│      └── cameo/                                                  │
└──────────────────────────────────────────────────────────────────┘
```

---

## Features

- **Two-layer architecture** — modular scripts cover individual screens; execution scripts chain them into full regression workflows
- **JSON-based DataProvider** — all test data lives in external JSON files; the same scripts run across any environment without code changes
- **13 PortalRM modular scripts** — full coverage of scheduling, availability management, regulations, and user administration
- **14 NexusCM modular scripts** — full coverage of the 10-step case management workflow
- **6 execution scripts** — 3 per module, targeting different user roles and dataset configurations
- **4 TestNG suite profiles** — full suite, modular-only, execution-only, and module-specific variants
- **Strongly typed constants** — `Data.java` provides all JSON field key constants; `Args.java` provides dataset selector keys; `UserRole.java` maps user roles to login dataset IDs
- **XRay/Jira integration** — test results posted to XRay for traceability against requirements

---

## Tech Stack

| Technology | Version | Role |
|---|---|---|
| Java | 11+ | Primary language |
| Selenium WebDriver | 4.x | Browser automation |
| TestNG | 7.x | Test execution and DataProvider |
| Jackson | 2.17 | JSON dataset parsing |
| WebDriverManager | 5.x | Automatic ChromeDriver management |
| Maven | 3.x | Build and profile-based execution |

---

## Repository Structure

```
automation-scripts/
├── dataSets/
│   └── master/
│       ├── loginData/
│       │   └── login.json                    # 9 user role credentials
│       └── scriptData/
│           ├── cerrt/
│           │   ├── availability_data.json    # portalrm_dataset_1/2/3
│           │   ├── schedule_data.json        # portalrm_schedule_001
│           │   ├── cemetery_regulations_data.json
│           │   └── users_data.json
│           └── cameo/
│               ├── cameo_data.json           # NexusCMdataset1/2, NexusCMPreNeedDataset
│               └── boss_data.json            # DEV/ALPHA/BETA environment data
│
├── src/
│   ├── utilities/
│   │   ├── DataReader.java                   # JSON loader + DataProvider helper
│   │   ├── RetryFailedXrayPosts.java         # Retry failed XRay result posts
│   │   └── EncryptAuthenticationFile.java
│   │
│   └── testscript/
│       ├── Args.java                         # Dataset key constants (DATA_ID, PLATFORM_DATA)
│       ├── Data.java                         # JSON field name constants + runtime data store
│       ├── UserRole.java                     # User role enum — maps to login dataset IDs
│       │
│       ├── modular/
│       │   ├── cerrt/                        # 13 PortalRM modular scripts
│       │   └── cameo/                        # 14 NexusCM modular scripts
│       │
│       ├── execution/
│       │   ├── cerrt/                        # 3 PortalRM execution scripts
│       │   └── cameo/                        # 3 NexusCM execution scripts
│       │
│       └── suites/
│           ├── cerrt_full_suite.xml
│           ├── cerrt_modular_only_suite.xml
│           ├── cerrt_execution_only_suite.xml
│           └── cameo_full_suite.xml
│
└── pom.xml
```

---

## Script Design

### Two-Layer Script Architecture

**Modular scripts** are self-contained, independently executable building blocks. Each covers a single screen or action and can be run in isolation during development or targeted regression.

**Execution scripts** chain modular scripts in the exact order they run during a full regression pass, under a specific user role and dataset.

```
Execution Script — PortalRM Dataset 1 (Cemetery Director role)
├── LaunchOnlineHelpTest          → validates online help tab launch
├── SearchCemeteryDetailsTest     → finds and opens the location record
├── TabSelectionTest              → navigates all detail tabs
├── ScheduleDatePickerTest        → validates date picker on schedule tab
├── SelectAvailabilityDayTest     → selects all 7 availability day tabs
├── DeleteAvailabilityTest        → deletes rows, validates required fields
├── AddAvailabilityTest           → creates 3 availability records
├── CloneAvailabilityTest         → clones Monday record to all other days
├── AvailabilitySortTest          → validates 6 column header sort behaviors
├── AvailabilitySearchTest        → validates search across 9 criteria types
├── EditLunchAvailabilityTest     → edits the lunch availability record
├── ScheduleTimeslotTest          → creates, edits, and deletes a timeslot
├── CemeteryRegulationsTest       → validates character limit enforcement
├── UserSearchTest                → searches users by name and email
└── AssociatedCemeteryTest        → add, cancel, and remove location association
```

### JSON-Based DataProvider Pattern

Test data is external, version-controlled JSON. No test data lives in code:

```java
@DataProvider(name = "availabilityData")
public Object[][] availabilityData() {
    return DataReader.toDataProviderArray(
        DataReader.load("scriptData/cerrt/availability_data.json"));
}

@Test(dataProvider = "availabilityData")
public void addAvailabilityTest(Map<String, String> data) {
    String description  = DataReader.get(data, Data.START_AVAILABILITY_DESCRIPTION);
    String locationType = DataReader.get(data, Data.START_AVAILABILITY_LOCATIONTYPE);
    // ...
}
```

The `datasets.dir` system property resolves the root directory at runtime. Maven sets it automatically from `pom.xml`; Jenkins overrides it via `-D` flags.

---

## Application Coverage

### PortalRM — Resource Management Module

| Modular Script | Coverage |
|---|---|
| `SearchCemeteryDetailsTest` | Location search and record open |
| `TabSelectionTest` | Schedule, Availability, Regulations, Activity tabs |
| `SelectAvailabilityDayTest` | All 7 availability day tabs |
| `AddAvailabilityTest` | Create 3 availability records |
| `CloneAvailabilityTest` | Clone Monday to all other days |
| `AvailabilitySortTest` | 6 column header sorts |
| `AvailabilitySearchTest` | Search by 9 field criteria |
| `EditAvailabilityTest` | Edit service type and location |
| `EditLunchAvailabilityTest` | Edit lunch record times |
| `DeleteAvailabilityTest` | Delete, cancel, required field errors |
| `ScheduleDatePickerTest` | Date picker validation |
| `ScheduleTimeslotTest` | Timeslot create, edit, delete |
| `ScheduleJumpToWeekTest` | Bi-weekly recurring timeslot |
| `CemeteryRegulationsTest` | Character limit enforcement |
| `LaunchOnlineHelpTest` | Online Help tab |
| `UserSearchTest` | Search users by name and email |
| `AssociatedCemeteryTest` | Add, cancel, and confirm delete |

**Execution scripts:**

| Script | User Role | Distinguishing Steps |
|---|---|---|
| `CeRRTExecutionTest1` | Cemetery Director | Uses `EditLunchAvailabilityTest` |
| `CeRRTExecutionTest2` | District Director | Uses `EditAvailabilityTest` |
| `CeRRTExecutionTest3` | Cemetery Representative | Adds `ScheduleJumpToWeekTest` |

### NexusCM — Case Management Module

| Modular Script | Screen Covered |
|---|---|
| `CreateNewCaseTest` | Start — previous decedent search and case creation |
| `ApplicantContactSearchTest` | Applicant Details — contact search modal |
| `EnterApplicantInformationTest` | Applicant Details — all fields |
| `CompleteMilitaryScreen` | Military Service — entries and war periods |
| `CompleteClaimantDetailsTest` | Claimant — all fields, domestic and foreign address |
| `CompleteOrganizationScreenTest` | Organizations — search, select, and contact |
| `CompletePersonalRepresentativeScreenTest` | Personal Representative — all fields |
| `CompleteAdditionalContactScreenTest` | Additional Contacts — create and assign type |
| `CompleteIntermentDetailsScreenTest` | Interment — all fields, regulations tab |
| `CompleteSchedulingDetailsTest` | Scheduling — daily schedule and timeslot |
| `CompleteSummaryScreenTest` | Summary — remarks, traversal, and transfer |
| `VerifyClaimantCaseCreatedTest` | Start — search for created case |
| `VerifyMigratedCaseReport` | Reports — case migration verification |
| `CompletePreNeedStartScreen` | Start — Pre-Need case type workflow |

**Execution scripts:**

| Script | Scenario |
|---|---|
| `CaMEOExecutionTest1` | Full Case Establishment — NexusCM Dataset 1 |
| `CaMEOExecutionTest2` | Full Case Establishment — NexusCM Dataset 2 |
| `CaMEOPreNeedExecutionTest` | Pre-Need regression — Eligibility Analyst + Supervisor |

---

## How to Run

```bash
git clone https://github.com/buchirano/automation-scripts.git
cd automation-scripts

# Run PortalRM full suite
mvn test -Dsuite.file=src/testscript/suites/cerrt_full_suite.xml

# Run NexusCM full suite
mvn test -Dsuite.file=src/testscript/suites/cameo_full_suite.xml

# Run PortalRM modular scripts only
mvn test -Dsuite.file=src/testscript/suites/cerrt_modular_only_suite.xml

# Run PortalRM execution scripts only
mvn test -Dsuite.file=src/testscript/suites/cerrt_execution_only_suite.xml

# Override dataset directory for CI/Jenkins
mvn test -Ddatasets.dir=/workspace/automation-scripts/dataSets/master \
         -Dsuite.file=src/testscript/suites/cerrt_full_suite.xml
```

---

## Related Repositories

| Repository | Description |
|---|---|
| [`automation-pages`](https://github.com/buchirano/automation-pages) | Page Object Model library — page classes and enums |
| [`test-automation-framework`](https://github.com/buchirano/test-automation-framework) | Jenkins CI/CD pipeline, Salesforce CLI auth, Slack integration |

---

## Contact

**Buchi Uwakwe**
- Email: buchiuwakwe@boltzintelligence.com
- GitHub: [github.com/buchirano](https://github.com/buchirano)
