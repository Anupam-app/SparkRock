package com.saucedemo.steps;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definitions: Checkout Feature
 */
public class CheckoutSteps {

    private final InventoryPage inventoryPage = new InventoryPage();
    private final CartPage      cartPage      = new CartPage();
    private final CheckoutPage  checkoutPage  = new CheckoutPage();

    // ── Given ─────────────────────────────────────────────────
    @Given("I have added {string} to the cart")
    public void iHaveAddedProductToCart(String productName) {
        inventoryPage.addToCart(productName);
    }

    // ── When ──────────────────────────────────────────────────
    @When("I click the Checkout button")
    public void iClickCheckoutButton() {
        cartPage.assertOnCartPage().clickCheckout();
    }

    @When("I enter first name {string}, last name {string}, postal code {string}")
    public void iEnterCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.assertOnStepOne()
                    .fillUserInfo(firstName, lastName, postalCode);
    }

    @When("I click Continue")
    public void iClickContinue() {
        checkoutPage.clickContinue();
    }

    @When("I click Finish")
    public void iClickFinish() {
        checkoutPage.assertOnOverview().clickFinish();
    }

    // ── Then ──────────────────────────────────────────────────
    @Then("the overview page should show {string}")
    public void theOverviewPageShouldShow(String productName) {
        checkoutPage.assertOnOverview()
                    .assertOverviewHasProduct(productName);
    }

    @Then("the order summary totals should be visible")
    public void theOrderSummaryTotalsShouldBeVisible() {
        checkoutPage.assertSummaryTotalsVisible();
    }

    @Then("the order confirmation page should be displayed")
    public void theOrderConfirmationPageShouldBeDisplayed() {
        checkoutPage.assertOrderCompleteUI();
    }

    @Then("the confirmation message should be {string}")
    public void theConfirmationMessageShouldBe(String message) {
        checkoutPage.assertConfirmationHeader(message);
    }

    @Then("a checkout error should contain {string}")
    public void aCheckoutErrorShouldContain(String errorText) {
        checkoutPage.assertStepOneError(errorText);
    }
}
