package com.saucedemo.pages;

import com.saucedemo.utils.DriverManager;
import com.saucedemo.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

/**
 * BasePage — parent class for all Page Objects.
 * Provides shared driver access, waits, and common interactions.
 */
public abstract class BasePage {

    protected WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    // ── Navigation ──────────────────────────────────────────
    protected void navigateTo(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // ── Element Interactions ────────────────────────────────
    protected void click(By locator) {
        WaitUtils.waitForClickable(driver, locator).click();
    }

    protected void click(WebElement element) {
        WaitUtils.waitForVisible(driver, element).click();
    }

    protected void type(By locator, String text) {
        WebElement el = WaitUtils.waitForVisible(driver, locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void type(WebElement element, String text) {
        WaitUtils.waitForVisible(driver, element).clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return WaitUtils.waitForVisible(driver, locator).getText().trim();
    }

    protected String getText(WebElement element) {
        return WaitUtils.waitForVisible(driver, element).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected List<WebElement> findAll(By locator) {
        return WaitUtils.waitForAllVisible(driver, locator);
    }

    // ── JS Executor ─────────────────────────────────────────
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", element);
    }

    // ── Assertions ───────────────────────────────────────────
    protected void assertUrlContains(String fragment) {
        WaitUtils.waitForUrlContains(driver, fragment);
        Assert.assertTrue(driver.getCurrentUrl().contains(fragment),
                "Expected URL to contain: " + fragment + " but was: " + driver.getCurrentUrl());
    }

    protected void assertTextContains(By locator, String expected) {
        String actual = getText(locator);
        Assert.assertTrue(actual.contains(expected),
                "Expected text to contain '" + expected + "' but was '" + actual + "'");
    }
}
