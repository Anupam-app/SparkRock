package com.saucedemo.steps;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions: Cart Feature
 */
public class CartSteps {

    private final LoginPage     loginPage     = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();
    private final CartPage      cartPage      = new CartPage();

    // ── Given ─────────────────────────────────────────────────
    @Given("I am logged in with username {string} and password {string}")
    public void iAmLoggedIn(String username, String password) {
        loginPage.open().loginAs(username, password);
    }

    // ── When ──────────────────────────────────────────────────
    @When("I add {string} to the cart")
    public void iAddProductToCart(String productName) {
        inventoryPage.addToCart(productName);
    }

    @When("I remove {string} from the inventory")
    public void iRemoveProductFromInventory(String productName) {
        inventoryPage.removeFromCart(productName);
    }

    @When("I navigate to the cart")
    public void iNavigateToCart() {
        inventoryPage.goToCart();
    }

    @When("I remove {string} from the cart")
    public void iRemoveProductFromCart(String productName) {
        cartPage.removeItem(productName);
    }

    // ── Then ──────────────────────────────────────────────────
    @Then("the cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String count) {
        inventoryPage.assertCartBadge(Integer.parseInt(count));
    }

    @Then("the cart badge should not be visible")
    public void theCartBadgeShouldNotBeVisible() {
        inventoryPage.assertCartBadgeNotVisible();
    }

    @Then("{string} should be present in the cart")
    public void productShouldBePresentInCart(String productName) {
        inventoryPage.goToCart();
        cartPage.assertOnCartPage()
                .assertProductInCart(productName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        cartPage.assertCartIsEmpty();
    }
}
