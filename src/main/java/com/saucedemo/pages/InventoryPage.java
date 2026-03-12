package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

/**
 * Page Object: Inventory / Products Page
 * URL: https://www.saucedemo.com/inventory.html
 */
public class InventoryPage extends BasePage {

    // ── Locators ─────────────────────────────────────────────
    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(css = ".bm-menu-wrap")
    private WebElement menuWrapper;

    // ── Private helpers ──────────────────────────────────────
    private WebElement getItemContainer(String productName) {
        return driver.findElement(
                By.xpath("//div[@class='inventory_item'][.//div[text()='"+ productName + "']]"));
    }

    private WebElement getAddToCartBtn(String productName) {
        return getItemContainer(productName)
                .findElement(By.xpath(".//button[contains(text(),'Add to cart')]"));
    }

    private WebElement getRemoveBtn(String productName) {
        return getItemContainer(productName)
                .findElement(By.xpath(".//button[contains(text(),'Remove')]"));
    }

    // ── Actions ──────────────────────────────────────────────
    public InventoryPage addToCart(String productName) {
        scrollIntoView(getAddToCartBtn(productName));
        getAddToCartBtn(productName).click();
        return this;
    }

    public InventoryPage removeFromCart(String productName) {
        scrollIntoView(getRemoveBtn(productName));
        getRemoveBtn(productName).click();
        return this;
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage();
    }

    public InventoryPage openMenu() {
        click(burgerMenuBtn);
        // wait for menu to slide open
        try { Thread.sleep(400); } catch (InterruptedException ignored) {}
        return this;
    }

    public LoginPage logout() {
        click(logoutLink);
        return new LoginPage();
    }

    // ── Assertions ───────────────────────────────────────────
    public InventoryPage assertOnInventoryPage() {
        assertUrlContains("/inventory.html");
        Assert.assertEquals(getText(pageTitle), "Products", "Page title mismatch");
        return this;
    }

    public InventoryPage assertInventoryLoaded() {
        Assert.assertTrue(inventoryList.isDisplayed(), "Inventory list not visible");
        Assert.assertFalse(inventoryItems.isEmpty(), "No inventory items found");
        return this;
    }

    public InventoryPage assertCartBadge(int expected) {
        String actual = getText(cartBadge);
        Assert.assertEquals(actual, String.valueOf(expected),
                "Cart badge count mismatch");
        return this;
    }

    public InventoryPage assertCartBadgeNotVisible() {
        Assert.assertFalse(isDisplayed(By.cssSelector(".shopping_cart_badge")),
                "Cart badge should not be visible but it is");
        return this;
    }
}
