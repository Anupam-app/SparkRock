package com.saucedemo.utils;

import com.saucedemo.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * DriverManager — ThreadLocal WebDriver factory.
 *
 * Supports Chrome, Firefox, Edge.
 * Thread-safe for potential parallel execution.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    /** Initialise a new driver based on config and store in ThreadLocal. */
    public static void initDriver() {
        ConfigManager cfg = ConfigManager.getInstance();
        String browser   = cfg.getBrowser().toLowerCase().trim();
        boolean headless = cfg.isHeadless();

        WebDriver driver;

        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions opts = new FirefoxOptions();
                if (headless) opts.addArguments("--headless");
                driver = new FirefoxDriver(opts);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions opts = new EdgeOptions();
                if (headless) opts.addArguments("--headless");
                driver = new EdgeDriver(opts);
            }
            default -> {  // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions opts = new ChromeOptions();
                if (headless) {
                    opts.addArguments("--headless=new");
                    opts.addArguments("--no-sandbox");
                    opts.addArguments("--disable-dev-shm-usage");
                }
                opts.addArguments("--window-size=1280,800");
                opts.addArguments("--disable-notifications");
                driver = new ChromeDriver(opts);
            }
        }

        driver.manage().window().maximize();
        driverThread.set(driver);
    }

    /** Returns the WebDriver for the current thread. */
    public static WebDriver getDriver() {
        WebDriver d = driverThread.get();
        if (d == null) throw new IllegalStateException(
                "Driver not initialised. Call DriverManager.initDriver() first.");
        return d;
    }

    /** Quits and removes the driver for the current thread. */
    public static void quitDriver() {
        WebDriver d = driverThread.get();
        if (d != null) {
            d.quit();
            driverThread.remove();
        }
    }
}
