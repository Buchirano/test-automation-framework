# Slack ↔ Jenkins Integration

This document describes the two-way Slack integration built into this framework.
QA team members can **trigger any test pipeline directly from Slack** without
logging into Jenkins. Results are automatically posted back to Slack when the
run completes.

---

## Architecture

```
QA Team
   │
   ▼
Slack Workflow
   │  (HTTP POST to Jenkins API)
   ▼
Jenkins Pipeline ──────────► Maven Test Execution
   │                                │
   │                         HTML Report Generated
   │                                │
   ▼                                ▼
Slack Notification ◄──── Pipeline Post Stage
(PASS ✅ / FAIL ❌ + report link)
```

---

## How to Trigger a Test Run from Slack

1. Go to the **#qa-automation** Slack channel (or wherever the workflow is pinned)
2. Click the **⚡ Workflow** shortcut — select **"Run Regression Tests"**
3. Choose the suite: `regression` / `smoke` / `sanity`
4. Click **Submit**

That's it. Jenkins picks it up and you'll get a result notification in the channel
within minutes.

---

## How the Integration Works (Technical Details)

### 1 — Jenkins API Token Setup

A dedicated Jenkins service account has an API token stored as a Slack secret:

```
Jenkins API Token → Stored in Slack as a Workflow secret variable
```

The token is used to authenticate HTTP requests from Slack to Jenkins.

### 2 — Slack Workflow Configuration

The Slack Workflow Builder is configured with a **Webhook step** that sends:

```
POST https://<jenkins-host>/job/test-automation-framework/buildWithParameters
Authorization: Basic <base64(username:api-token)>
Content-Type: application/x-www-form-urlencoded

TEST_SUITE=regression&TRIGGERED_BY=Slack
```

To set this up in Slack Workflow Builder:
1. **Trigger:** Shortcut or scheduled
2. **Step:** Send a webhook
3. **URL:** Your Jenkins buildWithParameters URL
4. **Method:** POST
5. **Headers:** `Authorization: Basic <encoded-token>`
6. **Body:** `TEST_SUITE=regression&TRIGGERED_BY=<user.name>`

### 3 — Slack Notification Back

The Jenkinsfile `post {}` block sends results back using `slackSend`:

```groovy
slackSend(
    tokenCredentialId: 'slack-bot-token',
    channel:           '#qa-automation-results',
    color:             'good',
    message:           "✅ All tests passed — <report-url|View Report>"
)
```

The Slack bot token is stored in **Jenkins Credentials** (not hardcoded).

---

## What the Slack Notification Contains

**On Pass ✅**
```
✅ Regression Test Suite — ALL TESTS PASSED
Suite:        regression
Branch:       main
Triggered by: Slack
Build:        #42
Report:       [View HTML Report]
Duration:     4 min 12 sec
```

**On Failure ❌**
```
❌ Regression Test Suite — FAILURES DETECTED
Suite:        regression
Branch:       main
Triggered by: Slack
Build:        #43
Report:       [View HTML Report]
Duration:     3 min 55 sec
👉 Review the report for VP failures and stack traces.
```

---

## Jenkins Credentials Required

Store these in Jenkins → Manage Credentials:

| Credential ID        | Type              | Description                          |
|----------------------|-------------------|--------------------------------------|
| `github-credentials` | Username/Password | GitHub access for repo checkout      |
| `slack-bot-token`    | Secret text       | Slack Bot OAuth token (xoxb-...)     |
| `maven-settings-xml` | Config file       | Maven settings.xml with repo creds   |

---

## Adding a New Pipeline to Slack

To expose a different test suite to the Slack workflow:
1. Add a new Jenkins job (copy the existing Jenkinsfile, update `TESTNG_SUITE`)
2. Update the Slack Workflow Builder webhook URL to point to the new job
3. Optionally add a new Slack shortcut for that suite
