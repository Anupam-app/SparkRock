package com.saucedemo.utils;

import com.saucedemo.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitUtils — reusable explicit-wait helpers built on WebDriverWait.
 */
public class WaitUtils {

    private static final int TIMEOUT = ConfigManager.getInstance().getTimeout();

    private WaitUtils() {}

    private static WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisible(WebDriver driver, WebElement element) {
        return wait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> waitForAllVisible(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static boolean waitForUrlContains(WebDriver driver, String fragment) {
        return wait(driver).until(ExpectedConditions.urlContains(fragment));
    }

    public static boolean waitForUrlToBe(WebDriver driver, String url) {
        return wait(driver).until(ExpectedConditions.urlToBe(url));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForPresence(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
