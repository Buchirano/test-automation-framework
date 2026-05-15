package com.buchirano.automation.pages.general;

import org.openqa.selenium.By;
import utils.ConfigProperties;

/**
 * <b>Name:</b> LoginPage.java
 * <p>
 * <b>Description:</b> Page class for the application Login screen.
 * Handles environment-aware URL construction, credential entry, login
 * execution, and post-login screen handling (password reset bypass,
 * authorized use confirmation).
 * <p>
 * Extends BasePageClass to inherit all common WebDriver interaction
 * methods, wait strategies, and scroll utilities.
 * <p>
 * The target environment is driven by the TEST_ENV value in
 * config.properties, making this class reusable across all environments
 * (dev, qa, staging, uat) without code changes.
 * <p>
 *
 * @author buchirano
 * @version 1.0
 */
public class LoginPage extends BasePageClass {

    /**
     * Environment name read from config.properties (TEST_ENV).
     * Lowercased and whitespace-stripped for URL construction.
     */
    private String environment = ConfigProperties.getValue("TEST_ENV", "qa")
            .toLowerCase()
            .replaceAll(" ", "");

    /**
     * Constructor — builds the environment-specific login URL
     * and sets it as the page URL for loadPage() to use.
     */
    public LoginPage() {
        driver.get(environmentURL());
    }

    // ─────────────────────────────────────────────
    //  URL Construction
    // ─────────────────────────────────────────────

    /**
     * Builds and returns the full login URL for the configured environment.
     * Supports sandbox and integration environment URL patterns.
     *
     * @return String full login URL for the current TEST_ENV
     */
    public String environmentURL() {
        String url;
        if (environment.equalsIgnoreCase("int") || environment.equalsIgnoreCase("utraining")) {
            url = "https://app--" + environment + ".sandbox.my.salesforce.com/";
        } else {
            url = "https://app--" + environment + ".sandbox.my.salesforce.com/";
        }
        return url;
    }

    /**
     * Navigates the browser directly to the specified URL.
     * Used for frontdoor authentication flows.
     *
     * @param url The full URL to navigate to
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    // ─────────────────────────────────────────────
    //  Private Get Methods — Element References
    // ─────────────────────────────────────────────

    /**
     * Returns a reference to the Username input field.
     *
     * @return WebElement the Username text field
     */
    private org.openqa.selenium.WebElement getUsernameTextField() {
        return driver.findElement(By.id("username"));
    }

    /**
     * Returns a reference to the Password input field.
     *
     * @return WebElement the Password text field
     */
    private org.openqa.selenium.WebElement getPasswordTextField() {
        return driver.findElement(By.id("password"));
    }

    /**
     * Returns a reference to the Login button.
     *
     * @return WebElement the Login / Log In to Sandbox button
     */
    private org.openqa.selenium.WebElement getLoginButton() {
        return driver.findElement(By.xpath("//input[@value='Log In to Sandbox'] | //input[@value='Log In']"));
    }

    // ─────────────────────────────────────────────
    //  Set Methods — Enter Values
    // ─────────────────────────────────────────────

    /**
     * Constructs the full username by appending the environment suffix
     * and enters it into the Username field.
     * Removes "mbms" prefix for int/utraining environments.
     *
     * @param user        Base username without environment suffix
     * @param environment The environment suffix to append
     */
    public void setUsername(String user, String environment) {
        if (environment.equalsIgnoreCase("int") || environment.equalsIgnoreCase("utraining")) {
            user = user.replaceFirst("mbms", "");
        }
        String fullUsername = user + environment.toLowerCase();
        getUsernameTextField().clear();
        getUsernameTextField().sendKeys(fullUsername);
    }

    /**
     * Enters the provided password into the Password field.
     *
     * @param password The password string to enter
     */
    public void setPassword(String password) {
        getPasswordTextField().clear();
        getPasswordTextField().sendKeys(password);
    }

    // ─────────────────────────────────────────────
    //  Read Methods — Read Values
    // ─────────────────────────────────────────────

    /**
     * Reads and returns the current value of the Username field.
     *
     * @return String current text in the Username field
     */
    public String readUsername() {
        return getUsernameTextField().getText();
    }

    /**
     * Reads and returns the current value of the Password field.
     *
     * @return String current text in the Password field
     */
    public String readPassword() {
        return getPasswordTextField().getText();
    }

    // ─────────────────────────────────────────────
    //  Click Methods
    // ─────────────────────────────────────────────

    /**
     * Clicks the Login button and waits for the application to fully load.
     */
    public void clickLogin() {
        getLoginButton().click();
        waitForAppLoad();
    }

    // ─────────────────────────────────────────────
    //  Composite Login Flow
    // ─────────────────────────────────────────────

    /**
     * Performs a complete login sequence:
     * 1. Loads the login page
     * 2. Enters username (with environment suffix)
     * 3. Enters password from config.properties
     * 4. Clicks the Login button
     * 5. Handles the Authorized Use Only confirmation screen if present
     * 6. Bypasses the password reset screen if present
     *
     * @param username Base username without environment suffix
     */
    public void login(String username) {
        waitForAppLoad();
        setUsername(username, environment);
        setPassword(ConfigProperties.getValue("PASSWORD", ""));
        clickLogin();
        handleAuthorizedUseOnlyConfirmation();
        bypassPasswordReset();
    }

    // ─────────────────────────────────────────────
    //  Post-Login Screen Handlers
    // ─────────────────────────────────────────────

    /**
     * Handles the Authorized Use Only confirmation screen that may appear
     * after login in certain environments. Clicks the Next button if present.
     */
    public void handleAuthorizedUseOnlyConfirmation() {
        if (isPresent("//*[(text()='Authorized Use Only')]")) {
            getElementByXPath("//input[@type='button' and @value = 'Next']").click();
            waitForAppLoad();
        }
    }

    /**
     * Bypasses the Change Your Password screen that may appear after login
     * for accounts whose passwords were set programmatically.
     * Clicks Cancel to dismiss the screen if present.
     */
    public void bypassPasswordReset() {
        if (isPresent("//h2[(text()='Change Your Password')]")) {
            driver.findElement(By.id("cancel-button")).click();
            waitForAppLoad();
        }
    }

    // ─────────────────────────────────────────────
    //  Environment Utilities
    // ─────────────────────────────────────────────

    /**
     * Maps the current Salesforce TEST_ENV to its corresponding
     * external environment tier (BETA, ALPHA, or DEV).
     * Useful for selecting the correct test dataset row for the environment.
     *
     * @return String environment tier: "BETA", "ALPHA", or "DEV"
     */
    public String getEnvironmentTier() {
        String env = ConfigProperties.getValue("TEST_ENV", "dev").toLowerCase();
        if (env.contains("uat") || env.contains("utraining")
                || env.contains("int") || env.contains("reg")) {
            return "BETA";
        } else if (env.contains("sqa") || env.contains("qa")) {
            return "ALPHA";
        } else {
            return "DEV";
        }
    }
}
