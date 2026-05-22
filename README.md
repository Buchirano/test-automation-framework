# test-automation-framework

**A production-grade Java + Selenium + TestNG automation framework with Jenkins CI/CD, Slack integration, and multi-environment Salesforce Lightning support — designed and built from scratch.**

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.x-FF6C37)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?logo=jenkins&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub-Actions-2088FF?logo=github-actions&logoColor=white)

---

## Overview

This framework delivers end-to-end automated regression testing for enterprise Salesforce Lightning applications. It is built on a strict 3-layer Page Object Model architecture that separates element locators, interaction logic, and test orchestration into independently maintainable layers. Tests run daily on Jenkins and deliver results directly to Slack — no Jenkins access required for QA teams.

**Highlights:**
- Full Salesforce Lightning UI automation with robust XPath targeting and `waitForSalesforceLoad` synchronization
- 3-layer POM: Core base class, application page classes (from `automation-pages`), and modular + execution test scripts (from `automation-scripts`)
- Jenkins declarative pipeline with Salesforce CLI authentication, multi-attempt retry logic, and VP (verification point) pass/fail tracking
- Two-way Slack integration: QA team triggers pipeline runs from Slack and receives results automatically
- XRay/Jira integration for test result reporting and traceability
- Multi-environment support: dev, qa, staging, uat — config-driven with no code changes required
- Headless Chrome execution via Xvfb on Linux CI agents

---

## Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│  EXECUTION TEST SCRIPTS  (automation-scripts/execution/)        │
│  Full end-to-end regression flows — orchestrate modular tests   │
│  One script per application module and dataset                  │
└──────────────────────────┬──────────────────────────────────────┘
                           │ calls
┌──────────────────────────▼──────────────────────────────────────┐
│  MODULAR TEST SCRIPTS  (automation-scripts/modular/)            │
│  Single-screen tests — focused, reusable, independently runnable│
│  JSON DataProvider-driven with external test datasets           │
└──────────────────────────┬──────────────────────────────────────┘
                           │ uses
┌──────────────────────────▼──────────────────────────────────────┐
│  PAGE OBJECT LIBRARY  (automation-pages/)                       │
│  One class per application screen                               │
│  Enum-driven field interactions, null-safe conditionals         │
│  Salesforce Lightning compatibility patterns                    │
└──────────────────────────┬──────────────────────────────────────┘
                           │ extends
┌──────────────────────────▼──────────────────────────────────────┐
│  FRAMEWORK CORE  (src/main/java/)                               │
│  BasePageClass — abstract WebDriver wrapper with all utilities  │
│  DriverManager — thread-safe WebDriver lifecycle management     │
│  ConfigProperties — environment-aware config reader             │
└─────────────────────────────────────────────────────────────────┘
```

---

## Features

- **Salesforce Lightning automation** — designed specifically for the complexities of Salesforce Lightning UI: shadow DOM, dynamic XPath, `data-id` attribute targeting, JS click fallbacks, and `waitForSalesforceLoad` synchronization
- **3-layer Page Object Model** — clean separation between locators, interaction logic, and test assertions
- **Multi-attempt retry pipeline** — Jenkins retries failed test runs up to 3 times, tracking VP pass/fail/exception counts per attempt
- **Salesforce CLI authentication** — automated SFDX URL authentication without MFA; token injected directly into test execution
- **Two-way Slack integration** — QA team triggers runs via Slack workflow shortcuts; results post back automatically with VP summary and report link
- **XRay reporting** — test results pushed to XRay/Jira for full traceability against requirements
- **Multi-environment config** — single codebase runs across dev, qa, staging, and uat without code changes
- **Modular + execution test layers** — modular tests run independently for development; execution tests chain the full workflow for regression

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 11+ | Primary automation language |
| Selenium WebDriver | 4.x | Browser automation engine |
| TestNG | 7.x | Test execution, DataProvider, and suites |
| Maven | 3.8+ | Build and dependency management |
| Jenkins | 2.4x+ | CI/CD pipeline orchestration |
| Salesforce CLI | Latest stable | SFDX authentication and org management |
| Slack API | — | Pipeline trigger and result notification |
| XRay | — | Jira test result reporting |
| Xvfb | — | Virtual display for headless Linux execution |

---

## Project Structure

```
test-automation-framework/
├── src/main/java/
│   ├── base/
│   │   └── BasePageClass.java         # Abstract WebDriver wrapper — all page classes extend this
│   ├── pages/
│   │   └── LoginPage.java             # Application login page class
│   ├── tests/
│   │   └── LoginTest.java             # Example regression test
│   └── utils/
│       ├── ConfigProperties.java      # config.properties reader with runtime override support
│       └── DriverManager.java         # Thread-safe WebDriver lifecycle management
├── automation-pages/                  # Page Object Model library (application module)
│   └── src/main/java/.../pages/
│       ├── application/mainapp/        # CaseManagementApp page classes (20+ pages)
│       ├── application/portal/         # ResourcePortal page classes (9 pages)
│       ├── general/                   # Shared page classes (LoginPage, CaseDetailsPage)
│       └── enums/                     # 28+ enum classes for dropdown and field values
├── automation-scripts/
│   └── README.md                      # Test scripts module documentation
├── config/
│   └── config.properties              # Browser, URL, wait, and test configuration
├── jenkins/
│   └── Jenkinsfile                    # 8-stage declarative CI/CD pipeline
├── slack-integration/
│   └── README.md                      # Slack + Jenkins workflow setup guide
└── pom.xml                            # Maven build configuration
```

---

## How to Run

**Prerequisites:** Java 11+, Maven 3.8+, Chrome browser

```bash
# Clone the repo
git clone https://github.com/buchirano/test-automation-framework.git
cd test-automation-framework

# Compile and package
mvn clean compile install -q

# Run the full regression suite
mvn exec:java -Dexec.mainClass='testScripts.execution.RegressionTestScript'

# Run against a specific environment
mvn exec:java -Dexec.mainClass='testScripts.execution.RegressionTestScript' \
  -DAPP_URL=https://your-sandbox.salesforce.com \
  -DTEST_ENV=staging

# Run in debug mode (headless=false, browser stays open)
mvn exec:java -Dheadless=false -Dexec.mainClass='testScripts.execution.RegressionTestScript'
```

---

## Pipeline Overview

The `jenkins/Jenkinsfile` defines an 8-stage declarative pipeline:

```
Start Confirmation → Clone → Install SF CLI → Build →
SF Authentication → Extract Token → Execute Tests → Reporting
```

**Stage details:**
1. **Start Confirmation** — immediately notifies Slack that the pipeline queued; prevents duplicate triggers
2. **Install Salesforce CLI** — downloads SF CLI once and caches; skips download if already present
3. **Build** — Maven clean compile install
4. **Salesforce Authentication** — SFDX URL auth; no MFA; auth file written and immediately deleted
5. **Extract Token** — parses SF CLI JSON output; injects access token and instance URL as Maven `-D` flags
6. **Execute Tests** — Xvfb virtual display, XRay credentials injected, retry loop up to 3 attempts, VP counts parsed from XML results per attempt
7. **Reporting** — archives all attempt artifacts, publishes HTML index linking all attempt reports, sets build status

**Triggers:**
- Scheduled: Monday–Friday, 6:00 AM
- On-demand: Slack Workflow shortcut (no Jenkins access required)

**Notifications:**
- SUCCESS → external QA channel
- UNSTABLE (VP failure) → both QA channel and internal automation channel with full VP details
- FAILURE (build error) → both channels

---

## Contact

**Buchi Uwakwe**
- Email: buchiuwakwe@boltzintelligence.com
- GitHub: [github.com/buchirano](https://github.com/buchirano)
