package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

/**
 * Page Object: Cart Page
 * URL: https://www.saucedemo.com/cart.html
 */
public class CartPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutBtn;

    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingBtn;

    // ── Actions ──────────────────────────────────────────────
    public CheckoutPage clickCheckout() {
        click(checkoutBtn);
        return new CheckoutPage();
    }

    public InventoryPage continueShopping() {
        click(continueShoppingBtn);
        return new InventoryPage();
    }

    public CartPage removeItem(String productName) {
        driver.findElement(
                By.xpath("//div[@class='inventory_item_name' and text()='" + productName
                        + "']/ancestor::div[@class='cart_item']"
                        + "//button[starts-with(@data-test,'remove')]"))
                .click();
        return this;
    }

    // ── Assertions ───────────────────────────────────────────
    public CartPage assertOnCartPage() {
        assertUrlContains("/cart.html");
        Assert.assertEquals(getText(pageTitle), "Your Cart", "Cart page title mismatch");
        return this;
    }

    public CartPage assertProductInCart(String productName) {
        boolean found = cartItems.stream()
                .anyMatch(item -> item.getText().contains(productName));
        Assert.assertTrue(found, "Product '" + productName + "' not found in cart");
        return this;
    }

    public CartPage assertCartIsEmpty() {
        Assert.assertTrue(cartItems.isEmpty(), "Cart should be empty but has items");
        return this;
    }

    public CartPage assertItemCount(int expected) {
        Assert.assertEquals(cartItems.size(), expected,
                "Cart item count mismatch");
        return this;
    }
}
