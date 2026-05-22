package base;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigProperties;
import utils.DriverManager;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * BasePageClass — portable abstract base for all page objects in the framework.
 *
 * Provides core WebDriver interaction, By-based locators, standard HTML Select
 * helpers, wait strategies, verification points, and shared utilities. Contains
 * nothing application-specific — subclasses like LightningBasePageClass layer on
 * technology-specific behavior (Salesforce Lightning wait chains, JS scrolling,
 * XPath builders, etc.).
 *
 * Two constructors:
 *   BasePageClass()         — for Lightning/production pages that manage their own navigation.
 *   BasePageClass(String url) — for example/portfolio pages that register a URL at construction.
 *
 * @author  buchirano
 * @version 2.0
 */
public abstract class BasePageClass {

    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;
    protected String pageUrl;

    private static final int DEFAULT_WAIT_SECONDS = Integer.parseInt(
        ConfigProperties.getValue("SEARCH_WAIT_SECONDS", "15")
    );

    /** For Lightning and other pages that handle their own navigation. */
    protected BasePageClass() {
        this.driver  = DriverManager.getDriver();
        this.wait    = buildFluentWait();
        this.pageUrl = null;
    }

    /** For example/portfolio pages that register a URL at construction. */
    protected BasePageClass(String url) {
        this.driver  = DriverManager.getDriver();
        this.wait    = buildFluentWait();
        this.pageUrl = url;
    }

    // ─────────────────────────────────────────────
    //  Page Navigation
    // ─────────────────────────────────────────────

    public void loadPage() {
        driver.get(pageUrl);
        waitForPageLoad();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String readPageTitle() {
        return driver.getTitle();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─────────────────────────────────────────────
    //  Object Identification — By-based locators
    // ─────────────────────────────────────────────

    protected WebElement getObjectById(String id) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    protected Select getDropDownById(String id) {
        return new Select(getObjectById(id));
    }

    protected WebElement getObjectByCss(String cssSelector) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected WebElement getObjectByXPath(String xpath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement getObjectByLinkText(String linkText) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
    }

    protected WebElement getObjectByName(String name) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    }

    protected List<WebElement> getObjects(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }

    protected boolean isObjectPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

    // ─────────────────────────────────────────────
    //  Actions on Elements
    // ─────────────────────────────────────────────

    protected String readText(WebElement element) {
        return element.getText();
    }

    protected String readValue(WebElement element) {
        return element.getAttribute("value");
    }

    protected void setValue(WebElement element, String value) {
        if (value == null) return;
        element.clear();
        element.sendKeys(value);
    }

    protected void click(WebElement element) {
        element.click();
        waitForPageLoad();
    }

    protected void clickWithJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForPageLoad();
    }

    protected void clear(WebElement element) {
        if (element != null) {
            element.clear();
        }
    }

    protected boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    // ─────────────────────────────────────────────
    //  Dropdown Interactions — standard HTML Select
    // ─────────────────────────────────────────────

    protected void selectByVisibleText(Select select, String visibleText) {
        select.selectByVisibleText(visibleText);
    }

    protected String readSelected(Select select) {
        return select.getFirstSelectedOption().getText();
    }

    // ─────────────────────────────────────────────
    //  Wait Strategies
    // ─────────────────────────────────────────────

    public void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForObject(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForNotVisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean isPageLoaded() {
        return readPageTitle() != null && !readPageTitle().isEmpty();
    }

    // ─────────────────────────────────────────────
    //  Presence & Shared Utilities
    // ─────────────────────────────────────────────

    public boolean isPresent(String xPath) {
        return !driver.findElements(By.xpath(xPath)).isEmpty();
    }

    public boolean isHeadlessMode() {
        return Boolean.parseBoolean(ConfigProperties.getValue("HEADLESS", "false").trim());
    }

    protected void sleep(double seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis((long) seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────
    //  Verification Points
    // ─────────────────────────────────────────────

    protected void vpEquals(String vpLabel, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("[VP PASS] " + vpLabel + " | Expected: " + expected + " | Actual: " + actual);
        } else {
            System.out.println("[VP FAIL] " + vpLabel + " | Expected: " + expected + " | Actual: " + actual);
        }
    }

    protected void vpContains(String vpLabel, String expected, String actual) {
        if (actual != null && actual.contains(expected)) {
            System.out.println("[VP PASS] " + vpLabel + " | Expected contains: " + expected);
        } else {
            System.out.println("[VP FAIL] " + vpLabel + " | Expected to contain: " + expected + " | Actual: " + actual);
        }
    }

    // ─────────────────────────────────────────────
    //  FluentWait Configuration
    // ─────────────────────────────────────────────

    protected FluentWait<WebDriver> buildFluentWait() {
        return new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(DEFAULT_WAIT_SECONDS))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoreAll(Arrays.asList(
                NoSuchElementException.class,
                ElementNotInteractableException.class,
                ElementClickInterceptedException.class));
    }
}
