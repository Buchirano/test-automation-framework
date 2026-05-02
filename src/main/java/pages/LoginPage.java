package pages;

import base.BasePageClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import utils.ConfigProperties;

/**
 * LoginPage — Page class for the application login screen.
 *
 * Follows ATI framework conventions:
 *  - get*()     : returns the WebElement reference (private)
 *  - set*()     : enters a value into an input field
 *  - read*()    : reads and returns a value from the page
 *  - click*()   : clicks a control and waits for page load
 *  - is*()      : returns boolean for state checks
 *
 * All page classes extend BasePageClass, which provides the WebDriver,
 * wait strategies, and common interaction methods.
 *
 * @author  Portfolio Framework
 * @version 1.0
 */
public class LoginPage extends BasePageClass {

    // ─── URL configured in config.properties as APP_URL ───
    private static final String LOGIN_URL = ConfigProperties.getValue("APP_URL", "");

    /**
     * Constructor — passes the login URL up to BasePageClass.
     */
    public LoginPage() {
        super(LOGIN_URL);
    }

    // ─────────────────────────────────────────────
    //  Private Get Methods — Element References
    // ─────────────────────────────────────────────

    /**
     * Returns a reference to the Username input field.
     *
     * @return WebElement the Username text field
     */
    private WebElement getUsernameTextField() {
        return getObjectById("username");
    }

    /**
     * Returns a reference to the Password input field.
     *
     * @return WebElement the Password text field
     */
    private WebElement getPasswordTextField() {
        return getObjectById("password");
    }

    /**
     * Returns a reference to the Login button.
     *
     * @return WebElement the Login button
     */
    private WebElement getLoginButton() {
        return getObjectByCss("button[type='submit']");
    }

    /**
     * Returns a reference to the error message element displayed after failed login.
     *
     * @return WebElement the error message container
     */
    private WebElement getLoginErrorMessage() {
        return getObjectByCss(".error-message");
    }

    /**
     * Returns a reference to the "Forgot Password" link.
     *
     * @return WebElement the Forgot Password anchor element
     */
    private WebElement getForgotPasswordLink() {
        return getObjectByLinkText("Forgot Password");
    }

    // ─────────────────────────────────────────────
    //  Public Set Methods — Enter Values
    // ─────────────────────────────────────────────

    /**
     * Enters the provided username into the Username field.
     *
     * @param username The username string to enter
     */
    public void setUsername(String username) {
        setValue(getUsernameTextField(), username);
    }

    /**
     * Enters the provided password into the Password field.
     *
     * @param password The password string to enter
     */
    public void setPassword(String password) {
        setValue(getPasswordTextField(), password);
    }

    // ─────────────────────────────────────────────
    //  Public Read Methods — Read Values
    // ─────────────────────────────────────────────

    /**
     * Reads and returns the current value of the Username field.
     *
     * @return String current text in the Username field
     */
    public String readUsername() {
        return readValue(getUsernameTextField());
    }

    /**
     * Reads and returns the login error message text.
     *
     * @return String visible error message text
     */
    public String readLoginErrorMessage() {
        return readText(getLoginErrorMessage());
    }

    // ─────────────────────────────────────────────
    //  Public Click Methods — Actions
    // ─────────────────────────────────────────────

    /**
     * Clicks the Login button and waits for the page to load.
     */
    public void clickLoginButton() {
        click(getLoginButton());
    }

    /**
     * Clicks the Forgot Password link and waits for the page to load.
     */
    public void clickForgotPasswordLink() {
        click(getForgotPasswordLink());
    }

    // ─────────────────────────────────────────────
    //  Public Is/State Methods — Boolean Checks
    // ─────────────────────────────────────────────

    /**
     * Returns true if the Login button is enabled on the page.
     *
     * @return boolean true if button is enabled, false if disabled
     */
    public boolean isLoginButtonEnabled() {
        return isEnabled(getLoginButton());
    }

    /**
     * Returns true if the login error message is currently displayed.
     *
     * @return boolean true if error message is visible
     */
    public boolean isLoginErrorDisplayed() {
        return isObjectPresent(By.cssSelector(".error-message"));
    }

    /**
     * Verifies the page has loaded by checking for the presence of the login form.
     *
     * @return boolean true if login form is visible
     */
    @Override
    public boolean isPageLoaded() {
        return isObjectPresent(By.id("username")) && isObjectPresent(By.id("password"));
    }

    // ─────────────────────────────────────────────
    //  Composite / Reusable Business Methods
    // ─────────────────────────────────────────────

    /**
     * Performs a full login sequence: sets username, password, and clicks Login.
     * This is a reusable helper called from test scripts and modular scripts.
     *
     * @param username The username to enter
     * @param password The password to enter
     */
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }
}
