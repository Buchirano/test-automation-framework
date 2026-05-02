package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * DriverManager — manages the lifecycle of the WebDriver instance.
 *
 * Uses a ThreadLocal pattern to support parallel test execution safely.
 * Browser type is configured via config.properties (BROWSER=CHROME|FIREFOX|EDGE).
 *
 * @author  Portfolio Framework
 * @version 1.0
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {}

    /**
     * Returns the current thread's WebDriver instance.
     * Initializes a new driver if one does not exist.
     *
     * @return WebDriver instance for the current thread
     */
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }

    /**
     * Initializes a new WebDriver based on the BROWSER property in config.properties.
     * Supports CHROME (with headless option), FIREFOX, and EDGE.
     */
    public static void initDriver() {
        String browser = ConfigProperties.getValue("BROWSER", "CHROME").toUpperCase();
        WebDriver driver;

        switch (browser) {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            case "CHROME":
            default:
                ChromeOptions options = new ChromeOptions();
                String headless = ConfigProperties.getValue("HEADLESS", "true");
                if ("true".equalsIgnoreCase(headless)) {
                    options.addArguments("--headless=new");
                }
                String width  = ConfigProperties.getValue("SCREEN_WIDTH",  "1920");
                String height = ConfigProperties.getValue("SCREEN_HEIGHT", "1080");
                options.addArguments("--window-size=" + width + "," + height);
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
                break;
        }

        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }

    /**
     * Quits the WebDriver and removes it from the ThreadLocal store.
     * Should be called in @AfterMethod or @AfterClass cleanup.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
