package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

/**
 * Page Object: Checkout Pages
 * Covers: step-one, step-two (overview), complete
 */
public class CheckoutPage extends BasePage {

    // ── Step One ─────────────────────────────────────────────
    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(css = "[data-test='continue']")
    private WebElement continueBtn;

    @FindBy(css = "[data-test='cancel']")
    private WebElement cancelBtn;

    @FindBy(css = "[data-test='error']")
    private WebElement stepOneError;

    // ── Step Two (Overview) ──────────────────────────────────
    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> overviewItems;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxLabel;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    @FindBy(css = "[data-test='finish']")
    private WebElement finishBtn;

    // ── Complete ──────────────────────────────────────────────
    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    @FindBy(css = ".complete-text")
    private WebElement completeText;

    @FindBy(css = ".pony_express")
    private WebElement ponyImage;

    @FindBy(css = "[data-test='back-to-products']")
    private WebElement backHomeBtn;

    // ── Actions: Step One ────────────────────────────────────
    public CheckoutPage enterFirstName(String value) {
        type(firstNameInput, value);
        return this;
    }

    public CheckoutPage enterLastName(String value) {
        type(lastNameInput, value);
        return this;
    }

    public CheckoutPage enterPostalCode(String value) {
        type(postalCodeInput, value);
        return this;
    }

    public CheckoutPage fillUserInfo(String firstName, String lastName, String postalCode) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode);
    }
    
    public CheckoutPage clickContinue() {
        click(continueBtn);
        return this;
    }

    public CheckoutPage submitInfo(String firstName, String lastName, String postalCode) {
        return fillUserInfo(firstName, lastName, postalCode).clickContinue();
    }

    // ── Actions: Overview ────────────────────────────────────
    public CheckoutPage clickFinish() {
        click(finishBtn);
        return this;
    }

    // ── Actions: Complete ────────────────────────────────────
    public InventoryPage clickBackToProducts() {
        click(backHomeBtn);
        return new InventoryPage();
    }

    // ── Assertions ───────────────────────────────────────────
    public CheckoutPage assertOnStepOne() {
        assertUrlContains("/checkout-step-one.html");
        return this;
    }

    public CheckoutPage assertOnOverview() {
        assertUrlContains("/checkout-step-two.html");
        Assert.assertEquals(getText(pageTitle), "Checkout: Overview");
        return this;
    }

    public CheckoutPage assertOverviewHasProduct(String productName) {
        boolean found = overviewItems.stream()
                .anyMatch(item -> item.getText().contains(productName));
        Assert.assertTrue(found, "Product '" + productName + "' not in overview");
        return this;
    }

    public CheckoutPage assertSummaryTotalsVisible() {
        Assert.assertTrue(subtotalLabel.isDisplayed(), "Subtotal not visible");
        Assert.assertTrue(taxLabel.isDisplayed(),      "Tax not visible");
        Assert.assertTrue(totalLabel.isDisplayed(),    "Total not visible");
        return this;
    }

    public CheckoutPage assertOnCompletePage() {
        assertUrlContains("/checkout-complete.html");
        return this;
    }

    public CheckoutPage assertConfirmationHeader(String expected) {
        Assert.assertEquals(getText(completeHeader), expected,
                "Confirmation header mismatch");
        return this;
    }

    public CheckoutPage assertOrderCompleteUI() {
        assertOnCompletePage();
        Assert.assertTrue(completeHeader.isDisplayed(), "Complete header not visible");
        Assert.assertTrue(completeText.isDisplayed(),   "Complete text not visible");
        Assert.assertTrue(ponyImage.isDisplayed(),      "Pony image not visible");
        Assert.assertTrue(backHomeBtn.isDisplayed(),    "Back home button not visible");
        return this;
    }

    public CheckoutPage assertStepOneError(String expected) {
        String actual = getText(stepOneError);
        Assert.assertTrue(actual.contains(expected),
                "Step-one error expected to contain '" + expected + "' but was: '" + actual + "'");
        return this;
    }

}
