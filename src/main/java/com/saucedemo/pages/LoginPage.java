package com.saucedemo.pages;

import com.saucedemo.config.ConfigManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Page Object: Login Page
 * URL: https://www.saucedemo.com/
 */
public class LoginPage extends BasePage {

    // ── Locators ─────────────────────────────────────────────
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css = ".login_logo")
    private WebElement loginLogo;

    // ── Actions ──────────────────────────────────────────────
    public LoginPage open() {
        navigateTo(ConfigManager.getInstance().getBaseUrl());
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public void clickLogin() {
        click(loginButton);
    }

    public InventoryPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage();
    }

    public LoginPage clickLoginExpectingError() {
        clickLogin();
        return this;
    }

    // ── Assertions ───────────────────────────────────────────
    public LoginPage assertOnLoginPage() {
        Assert.assertEquals(driver.getCurrentUrl(),
                ConfigManager.getInstance().getBaseUrl() + "/",
                "Not on login page");
        return this;
    }

    public LoginPage assertLoginFormVisible() {
        Assert.assertTrue(usernameInput.isDisplayed(), "Username field not visible");
        Assert.assertTrue(passwordInput.isDisplayed(), "Password field not visible");
        Assert.assertTrue(loginButton.isDisplayed(),   "Login button not visible");
        return this;
    }

    public LoginPage assertErrorContains(String text) {
        String actual = getText(errorMessage);
        Assert.assertTrue(actual.contains(text),
                "Error message expected to contain '" + text + "' but was: '" + actual + "'");
        return this;
    }

    public LoginPage assertErrorVisible() {
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not visible");
        return this;
    }
}
