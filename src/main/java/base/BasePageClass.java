package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;

/**
 * BasePageClass - Abstract base class for all page objects in the framework.
 *
 * All page classes extend this base to inherit common WebDriver interaction methods,
 * object identification helpers, and wait strategies. This mirrors the ATI framework's
 * AutomatedPage / platformIndependentCore pattern, keeping test scripts fully
 * independent of tool-specific implementation details.
 *
 * @author  Portfolio Framework
 * @version 1.0
 */
public abstract class BasePageClass {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String pageUrl;

    private static final int DEFAULT_WAIT_SECONDS = Integer.parseInt(
        ConfigProperties.getValue("SEARCH_WAIT_SECONDS", "15")
    );

    /**
     * Constructor — initializes driver, wait, and sets the page URL.
     * Subclasses call super(url) to register their endpoint.
     *
     * @param url The URL for this page, configured in config.properties
     */
    protected BasePageClass(String url) {
        this.driver  = DriverManager.getDriver();
        this.wait    = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
        this.pageUrl = url;
    }

    // ─────────────────────────────────────────────
    //  Page Navigation
    // ─────────────────────────────────────────────

    /**
     * Loads the page URL in the current browser session.
     */
    public void loadPage() {
        driver.get(pageUrl);
        waitForPageLoad();
    }

    /**
     * Returns the current browser URL.
     *
     * @return String current page URL
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Returns the title of the currently loaded page.
     *
     * @return String page title
     */
    public String readPageTitle() {
        return driver.getTitle();
    }

    /**
     * Closes the current browser session.
     */
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─────────────────────────────────────────────
    //  Object Identification — by ID
    // ─────────────────────────────────────────────

    /**
     * Returns a WebElement located by its HTML id attribute.
     *
     * @param id The id attribute of the target element
     * @return WebElement matching the given id
     */
    protected WebElement getObjectById(String id) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    /**
     * Returns a Select (dropdown) element located by its HTML id attribute.
     *
     * @param id The id attribute of the select element
     * @return Select wrapper around the dropdown element
     */
    protected Select getDropDownById(String id) {
        return new Select(getObjectById(id));
    }

    // ─────────────────────────────────────────────
    //  Object Identification — by Search Criteria
    // ─────────────────────────────────────────────

    /**
     * Returns a WebElement using a CSS selector search.
     *
     * @param cssSelector The CSS selector string
     * @return WebElement matching the selector
     */
    protected WebElement getObjectByCss(String cssSelector) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    /**
     * Returns a WebElement using an XPath expression.
     * Note: XPath should be the last resort — prefer id, css, or name locators first.
     *
     * @param xpath The XPath expression string
     * @return WebElement matching the XPath
     */
    protected WebElement getObjectByXPath(String xpath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Returns a WebElement by its visible link text.
     *
     * @param linkText The exact visible text of the anchor element
     * @return WebElement matching the link text
     */
    protected WebElement getObjectByLinkText(String linkText) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
    }

    /**
     * Returns a WebElement by its name attribute.
     *
     * @param name The name attribute value
     * @return WebElement matching the name
     */
    protected WebElement getObjectByName(String name) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    }

    /**
     * Returns multiple WebElements matching a CSS selector.
     *
     * @param cssSelector The CSS selector string
     * @return List of matching WebElements
     */
    protected List<WebElement> getObjects(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }

    /**
     * Checks whether an element is present on the page without throwing an exception.
     *
     * @param by The By locator strategy
     * @return true if present, false otherwise
     */
    protected boolean isObjectPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

    // ─────────────────────────────────────────────
    //  Actions on Elements
    // ─────────────────────────────────────────────

    /**
     * Reads and returns the visible text content of an element.
     *
     * @param element The target WebElement
     * @return String visible text of the element
     */
    protected String readText(WebElement element) {
        return element.getText();
    }

    /**
     * Reads the current value of an input field.
     *
     * @param element The input WebElement
     * @return String value of the input
     */
    protected String readValue(WebElement element) {
        return element.getAttribute("value");
    }

    /**
     * Sets a value in an input field.
     * Clears any existing content before entering the new value.
     *
     * @param element   The input WebElement
     * @param value     The value to enter
     */
    protected void setValue(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Clicks the given element and waits for the page to fully load.
     *
     * @param element The WebElement to click
     */
    protected void click(WebElement element) {
        element.click();
        waitForPageLoad();
    }

    /**
     * Clicks an element using JavaScript — fallback when standard click() is blocked.
     *
     * @param element The WebElement to click via JS
     */
    protected void clickWithJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForPageLoad();
    }

    /**
     * Clears the value from an input or textarea element.
     *
     * @param element The input WebElement to clear
     */
    protected void clear(WebElement element) {
        if (element != null) {
            element.clear();
        }
    }

    /**
     * Returns whether the given element is currently enabled.
     *
     * @param element The WebElement to check
     * @return true if enabled, false if disabled
     */
    protected boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    /**
     * Returns whether the given element is currently displayed on the page.
     *
     * @param element The WebElement to check
     * @return true if visible, false otherwise
     */
    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns whether a checkbox or radio button is currently selected.
     *
     * @param element The checkbox or radio WebElement
     * @return true if selected, false otherwise
     */
    protected boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    // ─────────────────────────────────────────────
    //  Dropdown Interactions
    // ─────────────────────────────────────────────

    /**
     * Selects an option from a dropdown by its visible text.
     *
     * @param select      The Select wrapper for the dropdown
     * @param visibleText The visible text of the option to select
     */
    protected void selectByVisibleText(Select select, String visibleText) {
        select.selectByVisibleText(visibleText);
    }

    /**
     * Reads and returns the currently selected option from a dropdown.
     *
     * @param select The Select wrapper for the dropdown
     * @return String visible text of the selected option
     */
    protected String readSelected(Select select) {
        return select.getFirstSelectedOption().getText();
    }

    // ─────────────────────────────────────────────
    //  Wait Strategies
    // ─────────────────────────────────────────────

    /**
     * Waits until the browser reports the page load state as "complete".
     * Override in subclasses when pages have slow-loading dynamic content.
     */
    public void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Waits until the specified element is visible on the page.
     *
     * @param by The By locator for the element to wait for
     */
    protected void waitForObject(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits until the specified element is no longer visible on the page.
     *
     * @param by The By locator for the element to wait to disappear
     */
    protected void waitForNotVisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Verifies the page has loaded by checking the page title is non-empty.
     *
     * @return true if page title is present, false otherwise
     */
    public boolean isPageLoaded() {
        return readPageTitle() != null && !readPageTitle().isEmpty();
    }

    // ─────────────────────────────────────────────
    //  Verification Points
    // ─────────────────────────────────────────────

    /**
     * Verification point — asserts that actual equals expected.
     * Logs PASS or FAIL to the console with the VP label.
     *
     * @param vpLabel   Descriptive name for the verification point
     * @param expected  The expected value
     * @param actual    The actual value from the application
     */
    protected void vpEquals(String vpLabel, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("[VP PASS] " + vpLabel + " | Expected: " + expected + " | Actual: " + actual);
        } else {
            System.out.println("[VP FAIL] " + vpLabel + " | Expected: " + expected + " | Actual: " + actual);
        }
    }

    /**
     * Verification point — asserts that the actual value contains the expected substring.
     *
     * @param vpLabel   Descriptive name for the verification point
     * @param expected  The expected substring
     * @param actual    The actual value from the application
     */
    protected void vpContains(String vpLabel, String expected, String actual) {
        if (actual != null && actual.contains(expected)) {
            System.out.println("[VP PASS] " + vpLabel + " | Expected contains: " + expected);
        } else {
            System.out.println("[VP FAIL] " + vpLabel + " | Expected to contain: " + expected + " | Actual: " + actual);
        }
    }
}
