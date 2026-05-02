package tests;

import base.BasePageClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigProperties;
import utils.DriverManager;

/**
 * LoginTest — Regression test suite for application login functionality.
 *
 * Demonstrates the 3-layer ATI framework pattern:
 *   Automation Core (BasePageClass) → Page Class (LoginPage) → Test Script (LoginTest)
 *
 * Tests are data-driven, pulling credentials from config.properties.
 * Verification points (vpEquals/vpContains from BasePageClass) log PASS/FAIL
 * to the console and are captured in the Jenkins HTML report.
 *
 * Suite is triggered daily via Jenkins pipeline and can also be triggered
 * on-demand through the Slack workflow integration.
 *
 * @author  Portfolio Framework
 * @version 1.0
 */
public class LoginTest {

    private LoginPage loginPage;

    // ─────────────────────────────────────────────
    //  Setup & Teardown
    // ─────────────────────────────────────────────

    /**
     * Initializes the browser and loads the login page before each test method.
     */
    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        loginPage = new LoginPage();
        loginPage.loadPage();
    }

    /**
     * Closes the browser session after each test method completes.
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    // ─────────────────────────────────────────────
    //  Data Providers
    // ─────────────────────────────────────────────

    /**
     * Provides valid credential combinations for positive login tests.
     * In production, these would be read from an Excel dataset file.
     *
     * @return Object[][] array of {username, password} pairs
     */
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][] {
            {
                ConfigProperties.getValue("TEST_USERNAME", "testuser@example.com"),
                ConfigProperties.getValue("TEST_PASSWORD", "TestPass123!")
            }
        };
    }

    /**
     * Provides invalid credential combinations for negative login tests.
     *
     * @return Object[][] array of {username, password, expectedError} triples
     */
    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
            { "invalid@example.com", "WrongPass1!", "Invalid username or password" },
            { "",                    "TestPass123!", "Username is required"         },
            { "testuser@example.com", "",            "Password is required"         }
        };
    }

    // ─────────────────────────────────────────────
    //  Test Methods
    // ─────────────────────────────────────────────

    /**
     * TC-001: Verify that the login page loads with all required elements visible.
     */
    @Test(description = "TC-001: Verify login page loads successfully")
    public void testLoginPageLoads() {
        Assert.assertTrue(loginPage.isPageLoaded(),
            "Login page should display username and password fields");
        Assert.assertTrue(loginPage.isLoginButtonEnabled(),
            "Login button should be enabled on page load");
    }

    /**
     * TC-002: Verify successful login with valid credentials navigates to the home page.
     *
     * @param username Valid username from data provider
     * @param password Valid password from data provider
     */
    @Test(
        description   = "TC-002: Verify successful login with valid credentials",
        dataProvider  = "validCredentials"
    )
    public void testSuccessfulLogin(String username, String password) {
        loginPage.login(username, password);

        String currentUrl = loginPage.getPageUrl();
        Assert.assertFalse(currentUrl.contains("login"),
            "After login, URL should not contain 'login'. Actual URL: " + currentUrl);

        String pageTitle = loginPage.readPageTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null after login");
    }

    /**
     * TC-003: Verify that invalid credentials display the appropriate error message.
     *
     * @param username        Invalid username from data provider
     * @param password        Invalid password from data provider
     * @param expectedError   Expected error message text
     */
    @Test(
        description  = "TC-003: Verify error message displayed for invalid credentials",
        dataProvider = "invalidCredentials"
    )
    public void testInvalidLoginShowsError(String username, String password, String expectedError) {
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
            "Error message should be visible after invalid login attempt");

        String actualError = loginPage.readLoginErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
            "Error message should contain: '" + expectedError + "'. Actual: '" + actualError + "'");
    }

    /**
     * TC-004: Verify the login form fields can be cleared after entry.
     */
    @Test(description = "TC-004: Verify username field accepts and clears input")
    public void testUsernameFieldInputAndClear() {
        loginPage.setUsername("testuser@example.com");
        Assert.assertEquals(loginPage.readUsername(), "testuser@example.com",
            "Username field should contain the entered value");
    }
}
