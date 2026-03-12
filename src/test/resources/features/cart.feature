@cart
Feature: Add Product to Cart
  As a logged-in SauceDemo user
  I want to add and manage products in my cart
  So that I can prepare my order for checkout

  Background:
    Given I am logged in with username "standard_user" and password "secret_sauce"

  @smoke @TC-02
  Scenario: TC-02 - Add a single product to the cart
    When  I add "Sauce Labs Backpack" to the cart
    Then  the cart badge should display "1"
    And   "Sauce Labs Backpack" should be present in the cart

  @TC-02b
  Scenario: TC-02b - Add multiple products to the cart
    When  I add "Sauce Labs Backpack" to the cart
    And   I add "Sauce Labs Bike Light" to the cart
    Then  the cart badge should display "2"
    And   "Sauce Labs Backpack" should be present in the cart
    And   "Sauce Labs Bike Light" should be present in the cart

  @TC-02c
  Scenario: TC-02c - Remove a product from the inventory page
    When  I add "Sauce Labs Backpack" to the cart
    And   I remove "Sauce Labs Backpack" from the inventory
    Then  the cart badge should not be visible

  @TC-02d
  Scenario: TC-02d - Remove a product from inside the cart
    When  I add "Sauce Labs Fleece Jacket" to the cart
    And   I navigate to the cart
    And   I remove "Sauce Labs Fleece Jacket" from the cart
    Then  the cart should be empty
