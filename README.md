# test-automation-framework

> A production-grade Java + Selenium + TestNG regression automation framework with Jenkins CI/CD pipeline and Slack integration.

---

## Overview

This framework automates regression testing for web applications using a **3-layer Page Object Model** architecture inspired by enterprise ATI (Automation Tool Interface) patterns. It converts manual test cases into fully automated scripts that run daily in Jenkins and deliver results directly to Slack.

**Key capabilities:**
- Java + Selenium WebDriver automation with a reusable, maintainable Page Object Model
- TestNG test execution with data-driven test support
- Jenkins declarative pipeline — daily scheduled runs + on-demand via Slack
- Two-way Slack integration: QA triggers pipelines from Slack, results post back automatically
- ExtentReports HTML reporting with verification point (VP) pass/fail details
- Headless Chrome execution on CI agents with configurable screen resolution
- Maven-based dependency management and build lifecycle

---

## Architecture

The framework follows a strict 3-layer separation of concerns:

```
┌─────────────────────────────────────────────────────┐
│  TEST SCRIPTS  (tests/)                             │
│  TestNG test classes — test scenarios & assertions  │
│  Written by QA / Automation Engineers               │
└──────────────────────┬──────────────────────────────┘
                       │ uses
┌──────────────────────▼──────────────────────────────┐
│  PAGE CLASSES  (pages/)                             │
│  Page Object Model — one class per application page │
│  Encapsulates all element locators & interactions   │
│  Written in Java following strict naming conventions│
└──────────────────────┬──────────────────────────────┘
                       │ extends
┌──────────────────────▼──────────────────────────────┐
│  AUTOMATION CORE  (base/ + utils/)                  │
│  BasePageClass — abstract WebDriver wrapper         │
│  DriverManager — thread-safe WebDriver lifecycle    │
│  ConfigProperties — config.properties reader        │
└─────────────────────────────────────────────────────┘
```

---

## Project Structure

```
test-automation-framework/
├── src/main/java/
│   ├── base/
│   │   └── BasePageClass.java       # Abstract base — all page classes extend this
│   ├── pages/
│   │   └── LoginPage.java           # Example page class
│   ├── tests/
│   │   └── LoginTest.java           # Example TestNG test suite
│   └── utils/
│       ├── ConfigProperties.java    # Reads config.properties
│       └── DriverManager.java       # Thread-safe WebDriver management
├── config/
│   └── config.properties            # Browser, URL, wait, and test settings
├── jenkins/
│   └── Jenkinsfile                  # Declarative CI/CD pipeline
├── slack-integration/
│   └── README.md                    # Slack ↔ Jenkins setup guide
├── reports/                         # Generated HTML reports (gitignored)
└── pom.xml                          # Maven dependencies and build config
```

---

## Naming Conventions

Page class methods follow strict ATI-style camelCase conventions:

| Prefix    | Purpose                                 | Example                        |
|-----------|-----------------------------------------|--------------------------------|
| `get*()`  | Returns element reference (private)     | `getLoginButton()`             |
| `set*()`  | Enters a value into a field             | `setUsername(String username)` |
| `read*()` | Reads and returns a page value          | `readErrorMessage()`           |
| `click*()` | Clicks a control, waits for page load  | `clickLoginButton()`           |
| `select*()` | Selects a dropdown option             | `selectFacilityCode(String)`   |
| `is*()`   | Returns boolean for state checks        | `isLoginButtonEnabled()`       |

---

## Running Tests Locally

**Prerequisites:** Java 11+, Maven 3.8+, Chrome browser installed

```bash
# Clone the repo
git clone https://github.com/<your-username>/test-automation-framework.git
cd test-automation-framework

# Run the full regression suite (headless)
mvn test -Dsuite=regression

# Run in headed mode (browser visible — useful for debugging)
mvn test -Dsuite=regression -Dheadless=false

# Run against a specific environment
mvn test -DAPP_URL=https://staging.yourapp.com -Dsuite=smoke

# Run a specific test class
mvn test -Dtest=LoginTest
```

---

## Jenkins Pipeline

The `jenkins/Jenkinsfile` defines a 5-stage declarative pipeline:

```
Checkout → Build → Run Tests → Generate Report → Archive
```

**Pipeline triggers:**
- **Scheduled:** Monday–Friday at 6:00 AM EST
- **On-demand:** Via Slack Workflow (QA team triggers without Jenkins access)

**Report:** An ExtentReports HTML report is published in Jenkins after every run, showing each test case with VP pass/fail status, screenshots on failure, and execution duration.

---

## Slack Integration

QA team members trigger pipelines and receive results entirely within Slack:

1. Open the `#qa-automation` channel
2. Use the **⚡ Run Tests** workflow shortcut
3. Select suite → Submit
4. Results post back automatically on completion

See [`slack-integration/README.md`](slack-integration/README.md) for full setup instructions.

---

## Configuration

All settings are in `config/config.properties`. Runtime overrides use Maven `-D` flags:

| Property              | Default         | Description                              |
|-----------------------|-----------------|------------------------------------------|
| `BROWSER`             | `CHROME`        | Browser to use (CHROME/FIREFOX/EDGE)     |
| `HEADLESS`            | `true`          | Headless mode (false for local debug)    |
| `APP_URL`             | *(required)*    | Base URL of the application under test   |
| `TEST_ENV`            | `QA`            | Environment label shown in reports       |
| `SEARCH_WAIT_SECONDS` | `15`            | Default element wait timeout             |
| `CLOSE_ON_TERMINATE`  | `true`          | Close browser after test completion      |

---

## Tech Stack

| Tool               | Version | Purpose                              |
|--------------------|---------|--------------------------------------|
| Java               | 11      | Primary language                     |
| Selenium WebDriver | 4.18    | Browser automation                   |
| TestNG             | 7.9     | Test execution and reporting         |
| Maven              | 3.8+    | Build and dependency management      |
| Jenkins            | 2.4x+   | CI/CD pipeline orchestration         |
| ExtentReports      | 5.1     | HTML test execution reports          |
| Apache POI         | 5.2     | Excel data-driven test support       |
| Slack API          | —       | Pipeline trigger + result notification|

---

## License

MIT License — feel free to use this as a reference for your own framework.
