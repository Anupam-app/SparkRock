@checkout
Feature: Checkout Process
  As a logged-in SauceDemo user
  I want to complete the full checkout process
  So that I can successfully place an order

  Background:
    Given I am logged in with username "standard_user" and password "secret_sauce"

  @smoke @e2e @TC-03
  Scenario: TC-03 - Complete end-to-end checkout with a single product
    Given I have added "Sauce Labs Backpack" to the cart
    When  I navigate to the cart
    And   I click the Checkout button
    And   I enter first name "John", last name "Doe", postal code "10001"
    And   I click Continue
    Then  the overview page should show "Sauce Labs Backpack"
    And   the order summary totals should be visible
    When  I click Finish
    Then  the order confirmation page should be displayed
    And   the confirmation message should be "Thank you for your order!"

  @TC-03b
  Scenario Outline: TC-03b - Checkout with various products
    Given I have added "<product>" to the cart
    When  I navigate to the cart
    And   I click the Checkout button
    And   I enter first name "Jane", last name "Smith", postal code "90210"
    And   I click Continue
    And   I click Finish
    Then  the order confirmation page should be displayed
    And   the confirmation message should be "Thank you for your order!"

    Examples:
      | product                  |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Onesie        |

  @negative @TC-03c
  Scenario: TC-03c - Checkout fails when first name is missing
    Given I have added "Sauce Labs Backpack" to the cart
    When  I navigate to the cart
    And   I click the Checkout button
    And   I click Continue
    Then  a checkout error should contain "First Name is required"