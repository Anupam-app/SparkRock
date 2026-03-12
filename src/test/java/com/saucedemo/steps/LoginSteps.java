package com.saucedemo.steps;

import com.saucedemo.config.ConfigManager;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions: Login Feature
 */
public class LoginSteps {

    private final LoginPage    loginPage    = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    // ── Given ─────────────────────────────────────────────────
    @Given("I am on the SauceDemo login page")
    public void iAmOnTheLoginPage() {
        loginPage.open();
    }

    // ── When ──────────────────────────────────────────────────
    @When("I enter username {string} and password {string}")
    public void iEnterCredentials(String username, String password) {
        loginPage.enterUsername(username)
                 .enterPassword(password);
    }

    @When("I click the Login button")
    public void iClickLoginButton() {
        loginPage.clickLogin();
    }

    // ── Then ──────────────────────────────────────────────────
    @Then("I should be redirected to the Products page")
    public void iShouldBeOnProductsPage() {
        inventoryPage.assertOnInventoryPage();
    }

    @Then("the product list should be displayed")
    public void theProductListShouldBeDisplayed() {
        inventoryPage.assertInventoryLoaded();
    }

    @Then("a login error should contain {string}")
    public void aLoginErrorShouldContain(String errorText) {
        loginPage.assertErrorContains(errorText);
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnLoginPage() {
        loginPage.assertOnLoginPage();
    }
}
