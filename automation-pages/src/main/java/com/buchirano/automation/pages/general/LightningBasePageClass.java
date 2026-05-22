package com.buchirano.automation.pages.general;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import base.BasePageClass;
import com.buchirano.automation.enums.VerificationElement;
import utils.ConfigProperties;

/**
 * LightningBasePageClass — Salesforce Lightning-specific extension of BasePageClass.
 *
 * Extends the portable core with Lightning-aware wait chains (spinner, toast, app load),
 * XPath builders tuned for Lightning's data-id DOM attributes, JavaScript scroll utilities,
 * tab and frame management, advanced presence checks that return diagnostic strings,
 * table cell reading, and test data generators.
 *
 * All 29 production page classes for CaseManagementApp and ResourcePortal extend this class.
 *
 * @author  buchirano
 * @version 2.0
 */
public abstract class LightningBasePageClass extends BasePageClass {

    protected JavascriptExecutor js;
    public final Actions action;
    public String environment = ConfigProperties.getValue("TEST_ENV", "qa").toLowerCase();

    /**
     * Constructor — calls portable core, then overrides wait with Lightning's 60-second
     * FluentWait and initializes the JavascriptExecutor and Actions instances.
     */
    public LightningBasePageClass() {
        super();
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoreAll(Arrays.asList(
                        NoSuchElementException.class,
                        ElementNotInteractableException.class,
                        ElementClickInterceptedException.class));
        this.js     = (JavascriptExecutor) driver;
        this.action = new Actions(driver);
    }

    // ─────────────────────────────────────────────
    //  XPath Builders
    // ─────────────────────────────────────────────

    /**
     * Builds an XPath string from a WebElement by reading its DOM attributes.
     * Prioritizes data-id when available for more stable Lightning locators.
     *
     * @param element WebElement to derive XPath from
     * @return String XPath expression for the element
     */
    public String getXpathFromElement(WebElement element) {
        String tag        = element.getTagName();
        String dataId     = element.getAttribute("data-id");
        String id         = element.getAttribute("id");
        String className  = element.getAttribute("class");
        String title      = element.getAttribute("title");
        String name       = element.getAttribute("name");
        String ariaLabel  = element.getAttribute("aria-label");
        String dataLabel  = element.getAttribute("data-label");
        String role       = element.getAttribute("role");
        String type       = element.getAttribute("type");
        String placeholder= element.getAttribute("placeholder");

        if (dataId != null && !dataId.isEmpty()) {
            return "//" + tag + "[@data-id='" + dataId + "']";
        }
        if (id != null && !id.isEmpty()) {
            return "//" + tag + "[@id='" + id + "']";
        }

        StringBuilder xpath = new StringBuilder("//" + tag + "[");
        List<String> criteria = new ArrayList<>();

        if (title       != null && !title.isEmpty())       criteria.add("@title='" + title + "'");
        if (name        != null && !name.isEmpty())        criteria.add("@name='" + name + "'");
        if (ariaLabel   != null && !ariaLabel.isEmpty())   criteria.add("@aria-label='" + ariaLabel + "'");
        if (dataLabel   != null && !dataLabel.isEmpty())   criteria.add("@data-label='" + dataLabel + "'");
        if (role        != null && !role.isEmpty())        criteria.add("@role='" + role + "'");
        if (type        != null && !type.isEmpty())        criteria.add("@type='" + type + "'");
        if (placeholder != null && !placeholder.isEmpty()) criteria.add("@placeholder='" + placeholder + "'");
        if (className   != null && !className.isEmpty())   criteria.add("@class='" + className + "'");

        xpath.append(String.join(" and ", criteria)).append("]");
        return xpath.toString();
    }

    /**
     * Returns the XPath for a field, button, or link by its visible label name.
     * Supports inputs, buttons, checkboxes, and anchor tags.
     *
     * @param elementName The visible label name of the element on screen
     * @param index       Index when multiple matches exist (1-based)
     * @return String XPath expression
     */
    public String getXpathFromElementName(String elementName, int index) {
        return "(//button[text()='" + elementName + "'] | //label[text()='" + elementName
                + "']/parent::*/child::*//button[@part='input-button'] | //label[text()='" + elementName
                + "']/parent::*/child::*//button[@aria-label='" + elementName + "'] | //label[text()='" + elementName
                + "']/ancestor::*/child::*//button[@aria-label='" + elementName + "'] | //label[text()='" + elementName
                + "']/parent::*/child::*//input | //span[text()='" + elementName
                + "']/ancestor::lightning-primitive-input-checkbox//input | //*[text()='" + elementName
                + "']/ancestor::a)[" + index + "]";
    }

    /**
     * Returns the XPath for a field, button, or link by its visible label name.
     *
     * @param elementName The visible label name of the element on screen
     * @return String XPath expression
     */
    public String getXpathFromElementName(String elementName) {
        return "//button[text()='" + elementName + "'] | //label[text()='" + elementName
                + "']/parent::*/child::*//button[@part='input-button'] | //label[text()='" + elementName
                + "']/parent::*/child::*//button[@aria-label='" + elementName + "'] | //label[text()='" + elementName
                + "']/ancestor::*/child::*//button[@aria-label='" + elementName + "'] | //label[text()='" + elementName
                + "']/parent::*/child::*//input | //span[text()='" + elementName
                + "']/ancestor::lightning-primitive-input-checkbox//input | //*[text()='" + elementName
                + "']/ancestor::a";
    }

    // ─────────────────────────────────────────────
    //  Element Retrieval
    // ─────────────────────────────────────────────

    /**
     * Finds and returns a WebElement by XPath, scrolling it into view first.
     * Retries once on StaleElementReferenceException.
     *
     * @param xPath XPath of the element to find
     * @return WebElement matching the given XPath
     */
    public WebElement getElementByXPath(String xPath) {
        scrollIntoView(xPath);
        waitForVisibility(xPath);
        try {
            return driver.findElement(By.xpath(xPath));
        } catch (StaleElementReferenceException e) {
            return driver.findElement(By.xpath(xPath));
        }
    }

    /**
     * Returns a list of WebElements matching the given XPath.
     *
     * @param xPath XPath of the elements to find
     * @return List of matching WebElements
     */
    public List<WebElement> getElementsByXPath(String xPath) {
        return driver.findElements(By.xpath(xPath));
    }

    /**
     * Finds and returns a WebElement by a key-value attribute pair.
     *
     * @param key   Attribute name (e.g., "id", "class", "title")
     * @param value Attribute value to match
     * @return WebElement matching the criteria
     */
    public WebElement getElementBy(String key, String value) {
        String xPath = "//*[@" + key + "='" + value + "']";
        scrollIntoView(xPath);
        return driver.findElement(By.xpath(xPath));
    }

    /**
     * Returns the first WebElement found by its visible inner text within a tag.
     *
     * @param tag       HTML tag encapsulating the text (e.g., "span", "div")
     * @param innerText Visible text content to match
     * @return WebElement matching the tag and inner text
     */
    public WebElement getElementByInnerText(String tag, String innerText) {
        String xPath = ".//" + tag + "[(text()='" + innerText + "')]";
        scrollIntoView(xPath);
        return driver.findElement(By.xpath(xPath));
    }

    /**
     * Returns the input element within a container identified by its data-id.
     *
     * @param dataId The data-id attribute of the container element
     * @return WebElement input inside the container
     */
    public WebElement getInputByDataId(String dataId) {
        String xPath = "//*[@data-id='" + dataId + "']//input";
        scrollIntoView(xPath);
        return driver.findElement(By.xpath(xPath));
    }

    /**
     * Returns a button element within a container identified by its data-id.
     *
     * @param dataId The data-id attribute of the container element
     * @return WebElement button inside the container
     */
    public WebElement getButtonByDataId(String dataId) {
        String xPath = "//*[@data-id='" + dataId + "']//button";
        scrollIntoView(xPath);
        return driver.findElement(By.xpath(xPath));
    }

    /**
     * Returns a button element identified by its title attribute.
     *
     * @param title The title attribute value of the button
     * @return WebElement button with matching title
     */
    public WebElement getButtonByTitle(String title) {
        String xPath = "//button[@title='" + title + "']";
        scrollIntoView(xPath);
        return driver.findElement(By.xpath(xPath));
    }

    /**
     * Returns a button element identified by its name attribute.
     *
     * @param name The name attribute value of the button
     * @return WebElement button with matching name
     */
    public WebElement getButtonByName(String name) {
        return driver.findElement(By.xpath("//button[@name='" + name + "']"));
    }

    /**
     * Returns the first enabled element from a list of WebElements.
     * Throws InvalidElementStateException if all elements are disabled.
     *
     * @param elements List of WebElements to check
     * @return First enabled WebElement
     */
    public WebElement getFirstEnabledElement(List<WebElement> elements) {
        Optional<WebElement> result = elements.stream()
                .filter(WebElement::isEnabled)
                .findFirst();
        if (!result.isPresent()) {
            throw new InvalidElementStateException("No enabled element found — all elements were disabled.");
        }
        scrollIntoView(result.get());
        return result.get();
    }

    /**
     * Returns the element matching the given name as displayed on screen.
     * Tries up to 3 index variations to find the visible match.
     *
     * @param elementName Visible label name of the target element
     * @return WebElement matching the name
     */
    public WebElement getElementFromName(String elementName) {
        WebElement fieldToGet = driver.findElement(By.xpath(getXpathFromElementName(elementName, 1)));
        for (int i = 1; i < 4; i++) {
            fieldToGet = driver.findElement(By.xpath(getXpathFromElementName(elementName, i)));
            if (fieldToGet.isDisplayed()) {
                return fieldToGet;
            }
        }
        return fieldToGet;
    }

    // ─────────────────────────────────────────────
    //  XPath String Builders (Utility)
    // ─────────────────────────────────────────────

    /** @return XPath for an input within a data-id container */
    public String getInputXPathByDataId(String dataId) {
        return "//*[@data-id='" + dataId + "']//input";
    }

    /** @return XPath for an element with a given data-id */
    public String getElementXPathByDataId(String dataId) {
        return "//*[@data-id='" + dataId + "']";
    }

    /** @return XPath for a button within a data-id container */
    public String getButtonXPathByDataId(String dataId) {
        return "//*[@data-id='" + dataId + "']//button";
    }

    /** @return XPath for a button with a given name attribute */
    public String getButtonXPathByName(String name) {
        return "//button[@name='" + name + "']";
    }

    /** @return XPath for any element with a given title attribute */
    public String getElementXPathByTitle(String title) {
        return "//*[@title='" + title + "']";
    }

    /** @return XPath for any element whose text contains the given value */
    public String getElementXPathByTextContains(String text) {
        return "//*[contains(text(),'" + text + "')]";
    }

    // ─────────────────────────────────────────────
    //  Actions
    // ─────────────────────────────────────────────

    /**
     * Clicks the element matching the given visible name on screen.
     * Waits for the app to fully load after the click.
     *
     * @param elementName Visible label name of the target element
     */
    public void clickElement(String elementName) {
        getElementFromName(elementName).click();
        waitForAppLoad();
    }

    /**
     * Clicks a WebElement using JavaScript — bypasses standard Selenium click.
     * Falls back to zooming out if the element is not initially clickable.
     *
     * @param element WebElement to click via JavaScript
     */
    public void jsClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            setZoom(50);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        try {
            js.executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException e) {
            js.executeScript("arguments[0].click();", element);
        }
        waitForAppLoad();
    }

    /**
     * Clicks a WebElement with StaleElementReferenceException retry handling.
     *
     * @param xPath XPath of the element to click
     */
    public void click(String xPath) {
        try {
            driver.findElement(By.xpath(xPath)).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath(xPath)).click();
        }
        waitForAppLoad();
    }

    /**
     * Reads the visible text of a WebElement with StaleElementReferenceException handling.
     *
     * @param element WebElement to read text from
     * @return String visible text content of the element
     */
    @Override
    public String readText(WebElement element) {
        try {
            return element.getText();
        } catch (StaleElementReferenceException e) {
            return element.getText();
        }
    }

    /**
     * Clears the value of an input field with StaleElementReferenceException handling.
     *
     * @param element WebElement input field to clear
     */
    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (StaleElementReferenceException e) {
            element.clear();
        }
        waitForAppLoad();
    }

    /**
     * Fills an input field only if it is currently empty.
     *
     * @param inputElement The input WebElement to fill
     * @param value        The value to enter
     */
    public void fillInputField(WebElement inputElement, String value) {
        if ((inputElement.getAttribute("value") == null
                || inputElement.getAttribute("value").isEmpty()) && value != null) {
            inputElement.sendKeys(value);
        }
        waitForAppLoad();
    }

    /**
     * Clears all text in the currently focused field using Ctrl+A then Backspace.
     * Useful when Selenium's standard clear() method does not work.
     */
    public void clearText() {
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        action.sendKeys(Keys.BACK_SPACE).perform();
    }

    /**
     * Presses the specified key a given number of times.
     *
     * @param key            Key name: "Tab", "Enter", "Up Arrow", "Down Arrow", etc.
     * @param numberOfClicks Number of times to press the key
     */
    public void pressKey(String key, int numberOfClicks) {
        if (key.equalsIgnoreCase("Tab"))        action.sendKeys(Keys.TAB.toString().repeat(numberOfClicks)).perform();
        if (key.equalsIgnoreCase("Enter"))      action.sendKeys(Keys.ENTER.toString().repeat(numberOfClicks)).perform();
        if (key.equalsIgnoreCase("Up Arrow"))   action.sendKeys(Keys.ARROW_UP.toString().repeat(numberOfClicks)).perform();
        if (key.equalsIgnoreCase("Down Arrow")) action.sendKeys(Keys.ARROW_DOWN.toString().repeat(numberOfClicks)).perform();
        if (key.equalsIgnoreCase("Right Arrow"))action.sendKeys(Keys.ARROW_RIGHT.toString().repeat(numberOfClicks)).perform();
        if (key.equalsIgnoreCase("Left Arrow")) action.sendKeys(Keys.ARROW_LEFT.toString().repeat(numberOfClicks)).perform();
    }

    // ─────────────────────────────────────────────
    //  Dropdown Interactions — Lightning (span[@title] pattern)
    // ─────────────────────────────────────────────

    /**
     * Selects an option from a Lightning dropdown by its visible text.
     *
     * @param dropDownElement The dropdown trigger WebElement
     * @param optionText      The visible text of the option to select
     */
    public void selectDropdownOption(WebElement dropDownElement, String optionText) {
        if (optionText != null && !optionText.isBlank()) {
            dropDownElement.click();
            waitForAppLoad();
            driver.findElement(By.xpath("//span[@title = '" + optionText + "']")).click();
            waitForAppLoad();
        }
    }

    /**
     * Selects a dropdown option using JavaScript clicks — useful when standard
     * click is intercepted by overlapping elements.
     *
     * @param dropDownElement The dropdown trigger WebElement
     * @param optionText      The visible text of the option to select
     */
    public void selectDropdownOptionJSClick(WebElement dropDownElement, String optionText) {
        if (optionText != null) {
            jsClick(dropDownElement);
            WebElement option = driver.findElement(By.xpath("//span[@title = '" + optionText + "']"));
            scrollIntoView(option);
            jsClick(option);
        }
    }

    /**
     * Selects a dropdown option only if the field is currently empty or set to None.
     *
     * @param dropDownElement The dropdown trigger WebElement
     * @param optionText      The option text to select if field is empty
     */
    public void selectDropdownOptionIfEmpty(WebElement dropDownElement, String optionText) {
        String currentValue = dropDownElement.getAttribute("value");
        if (currentValue == null || currentValue.equals("--None--")) {
            selectDropdownOption(dropDownElement, optionText);
        }
    }

    // ─────────────────────────────────────────────
    //  Scroll Utilities
    // ─────────────────────────────────────────────

    /**
     * Scrolls the page down by 250 pixels.
     */
    public void pageScrollDown() {
        waitForAppLoad();
        js.executeScript("window.scrollBy(0, 250)");
        waitForAppLoad();
    }

    /**
     * Scrolls the page up by 400 pixels.
     */
    public void pageScrollUp() {
        waitForAppLoad();
        js.executeScript("window.scrollBy(0, -400)");
        waitForAppLoad();
    }

    /**
     * Scrolls the page to the very top.
     */
    public void pageScrollToTop() {
        waitForAppLoad();
        js.executeScript("window.scrollTo(0, 0);");
        waitForAppLoad();
    }

    /**
     * Scrolls the page to the very bottom.
     */
    public void pageScrollToBottom() {
        waitForAppLoad();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        waitForAppLoad();
    }

    /**
     * Scrolls a WebElement into the visible viewport.
     *
     * @param element WebElement to scroll into view
     */
    public void scrollIntoView(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Scrolls the element matching the given XPath into the visible viewport.
     *
     * @param xPath XPath of the element to scroll into view
     */
    public void scrollIntoView(String xPath) {
        waitForVisibility(xPath);
        WebElement element = driver.findElement(By.xpath(xPath));
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

    /**
     * Sets the browser zoom level using JavaScript.
     *
     * @param zoomPercent Zoom percentage (e.g., 50 for 50%, 100 for default)
     */
    public void setZoom(int zoomPercent) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        js.executeScript("document.body.style.zoom='" + zoomPercent + "%'");
        waitForAppLoad();
    }

    // ─────────────────────────────────────────────
    //  Browser / Tab Management
    // ─────────────────────────────────────────────

    /**
     * Closes the second browser tab and returns focus to the first tab.
     */
    public void closeSecondTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(tabs.get(0));
        waitForAppLoad();
    }

    /**
     * Switches browser focus to the second open tab.
     */
    public void openSecondTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        waitForAppLoad();
    }

    /**
     * Switches the WebDriver focus to a specified iframe by its title attribute.
     *
     * @param iframeTitle The title attribute value of the target iframe
     */
    public void switchFrame(String iframeTitle) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//iframe[@title='" + iframeTitle + "']")));
        sleep(5);
        driver.switchTo().frame(driver.findElement(
                By.xpath("//iframe[@title='" + iframeTitle + "']")));
    }

    // ─────────────────────────────────────────────
    //  Wait Strategies — Salesforce Lightning
    // ─────────────────────────────────────────────

    /**
     * Waits for a loading spinner to disappear before proceeding.
     * Targets the standard Lightning spinner container.
     */
    public void waitForLoadingSpinner() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[@class='slds-spinner_container' and not(@id)]")));
    }

    /**
     * Comprehensive page load wait combining document ready state, Lightning spinner,
     * and toast messages. Runs the spinner check twice to catch secondary spinners.
     */
    public void waitForAppLoad() {
        waitForPageLoad();
        waitForLoadingSpinner();
        waitForInvisibility("//*[@class='loadingText' or @data-aura-class='forceToastMessage']");
        waitForLoadingSpinner();
        waitForInvisibility("//*[@class='loadingText' or @data-aura-class='forceToastMessage']");
    }

    /**
     * Waits for the element matching the XPath to be visible on screen.
     * Scrolls down the page if the element is not immediately visible.
     *
     * @param xPath XPath of the element to wait for
     */
    public void waitForVisibility(String xPath) {
        waitForLoadingSpinner();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        } catch (NoSuchElementException ex) {
            pageScrollToTop();
            while (!isPresent(xPath)) {
                pageScrollDown();
                Long bottom  = (Long) js.executeScript("return document.body.scrollHeight;");
                Long current = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");
                if (current >= bottom && !isPresent(xPath)) break;
            }
        } finally {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        }
    }

    /**
     * Waits for the element matching the given VerificationElement enum to be visible.
     *
     * @param element VerificationElement enum containing the XPath value
     */
    public void waitForVisibility(VerificationElement element) {
        waitForVisibility(element.getXpathValue());
    }

    /**
     * Waits for the element matching the XPath to no longer be visible.
     *
     * @param xPath XPath of the element to wait to disappear
     */
    public void waitForInvisibility(String xPath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    }

    /**
     * Waits for the element matching the VerificationElement enum to no longer be visible.
     *
     * @param element VerificationElement enum containing the XPath value
     */
    public void waitForInvisibility(VerificationElement element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath(element.getXpathValue())));
    }

    /**
     * Waits for a button to become enabled (aria-disabled = false).
     *
     * @param buttonTitle The title attribute text of the button to wait for
     */
    public void waitForButtonToBeEnabled(String buttonTitle) {
        wait.until(ExpectedConditions.attributeToBe(
                By.xpath("//button[@title='" + buttonTitle + "']"), "aria-disabled", "false"));
    }

    /**
     * Waits for a toast message of the specified type to appear.
     *
     * @param toastType Toast message type text (e.g., "Success", "Error")
     */
    public void waitForToastMessage(String toastType) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[.='" + toastType + "']")));
    }

    /**
     * Waits for a toast message of the specified type to disappear.
     *
     * @param toastType Toast message type text (e.g., "Success", "Error")
     */
    public void waitForToastMessageToDisappear(String toastType) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[.='" + toastType + "']")));
    }

    /**
     * Reads the visible text from a toast message.
     *
     * @param toastType Toast message type to read (e.g., "Success", "Error")
     * @return String text content of the toast message
     */
    public String readToastMessage(String toastType) {
        waitForToastMessage(toastType);
        return driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"
                + toastType + "'])[1]/following::span[1]")).getText();
    }

    /**
     * Handles a Success toast message by waiting for it to disappear if present.
     */
    public void handleToastMessage() {
        if (isPresent("//*[.='Success']")) {
            waitForToastMessageToDisappear("Success");
        }
    }

    // ─────────────────────────────────────────────
    //  Presence & State Checks
    // ─────────────────────────────────────────────

    /**
     * Checks if the specified error message text is present on the page.
     *
     * @param expectedErrorMessage The error message text to search for
     * @return String "true" or "false"
     */
    public String isErrorMessagePresent(String expectedErrorMessage) {
        return Boolean.toString(isPresent(".//div[contains(text(),'" + expectedErrorMessage + "')]"));
    }

    /**
     * Checks if all elements in a list are enabled on screen.
     *
     * @param elements List of WebElements to check
     * @return "true" if all enabled, or a message listing which fields are not
     */
    public String isElementEnabled(List<WebElement> elements) {
        String negative = "false. The following field(s) were not enabled: ";
        int count = 0;
        for (WebElement e : elements) {
            waitForAppLoad();
            if (!e.isEnabled()) {
                negative += "• " + e.getAttribute("name") + "\n";
                count++;
            }
        }
        return count == 0 ? "true" : negative;
    }

    /**
     * Checks if all elements in a list are displayed on screen.
     *
     * @param elements List of WebElements to check
     * @return "true" if all displayed, or a message listing which are not
     */
    public String isElementPresent(List<WebElement> elements) {
        String negative = "false. The following field(s) were not found: ";
        int count = 0;
        for (WebElement e : elements) {
            waitForAppLoad();
            if (!e.isDisplayed()) {
                negative += "• " + e.getAttribute("name") + "\n";
                count++;
            }
        }
        return count == 0 ? "true" : negative;
    }

    /**
     * Checks whether all expected string values exist in the actual values list.
     *
     * @param expected List of expected string values
     * @param actual   List of actual string values to compare against
     * @return "true" if all expected values are found, or a message listing missing ones
     */
    public String areAllValuesPresent(List<String> expected, List<String> actual) {
        String negative = "false. The following value(s) were not found: ";
        int count = 0;
        for (String value : expected) {
            waitForAppLoad();
            if (!actual.contains(value)) {
                negative += "• " + value + "\n";
                count++;
            }
        }
        return count == 0 ? "true" : negative;
    }

    /**
     * Checks whether all fields in a list have data populated.
     *
     * @param elements List of WebElements to check for populated values
     * @return "true" if all fields have data, or a message listing empty ones
     */
    public String isAllFieldDataPopulated(List<WebElement> elements) {
        String negative = "False. The following field(s) do not have data: ";
        int count = 0;
        for (WebElement e : elements) {
            waitForAppLoad();
            if (e.getText().isBlank()) {
                negative += "• " + e.getAttribute("name") + "\n";
                count++;
            }
        }
        return count == 0 ? "true" : negative;
    }

    /**
     * Returns whether a checkbox WebElement is currently checked.
     *
     * @param checkBox The checkbox WebElement to inspect
     * @return boolean true if checked, false if unchecked
     */
    public boolean isCheckboxChecked(WebElement checkBox) {
        return checkBox.isSelected();
    }

    // ─────────────────────────────────────────────
    //  Table Utilities
    // ─────────────────────────────────────────────

    /**
     * Reads the value of a specific cell in a table.
     *
     * @param columnHeader  Exact column header name to identify the column
     * @param rowIdentifier Unique text in the row to identify the target row
     * @return String value of the matched table cell
     */
    public String readTableCell(String columnHeader, String rowIdentifier) {
        List<WebElement> headers = getElementsByXPath(
                "//tr//th//span[contains(text(), '" + columnHeader + "')]/ancestor::tr//th");
        Integer colIndex = null;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equalsIgnoreCase(columnHeader)) {
                colIndex = i;
            }
        }
        if (colIndex == null) return "Column header '" + columnHeader + "' was not found.";
        return driver.findElement(By.xpath(
                "(//p[contains(text(), '" + rowIdentifier + "')]/ancestor::tr//p)[" + colIndex + "]")).getText();
    }

    // ─────────────────────────────────────────────
    //  Data Generators
    // ─────────────────────────────────────────────

    /**
     * Generates a random 9-digit identifier string for test data.
     *
     * @return String random identifier value
     */
    public String randomTaxId() {
        Random rand = new Random();
        int num = rand.nextInt(899999999 - 100000000) + 100000000;
        return String.valueOf(num);
    }

    /**
     * Generates a test email address containing today's date.
     * Example output: automation-05-01-2026@email.com
     *
     * @return String formatted test email address
     */
    public static String generateTestEmailAddress() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-uuuu");
        return "automation-" + date.format(format) + "@email.com";
    }

    // ─────────────────────────────────────────────
    //  Misc Utilities
    // ─────────────────────────────────────────────

    /**
     * Returns the count of WebElements in the given list.
     *
     * @param elements List of WebElements to count
     * @return int size of the list
     */
    public int getNumberOfObjects(List<WebElement> elements) {
        return elements.size();
    }
}
