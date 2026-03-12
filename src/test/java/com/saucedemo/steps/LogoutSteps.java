package com.saucedemo.steps;

import com.saucedemo.config.ConfigManager;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions: Logout Feature
 */
public class LogoutSteps {

    private final InventoryPage inventoryPage = new InventoryPage();
    private final LoginPage     loginPage     = new LoginPage();

    @When("I open the burger menu")
    public void iOpenTheBurgerMenu() {
        inventoryPage.openMenu();
    }

    @When("I click the Logout link")
    public void iClickLogoutLink() {
        inventoryPage.logout();
    }

    @When("I navigate directly to {string}")
    public void iNavigateDirectlyTo(String path) {
        String baseUrl = ConfigManager.getInstance().getBaseUrl();
        DriverManager.getDriver().get(baseUrl + path);
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToLoginPage() {
        loginPage.assertOnLoginPage();
    }

    @Then("the login form should be visible")
    public void theLoginFormShouldBeVisible() {
        loginPage.assertLoginFormVisible();
    }
}
