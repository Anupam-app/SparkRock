@login
Feature: Login Functionality
  As a registered SauceDemo user
  I want to log in to the application
  So that I can access and purchase products

  Background:
    Given I am on the SauceDemo login page

  @smoke @TC-01
  Scenario: TC-01 - Successful login with valid credentials
    When  I enter username "standard_user" and password "secret_sauce"
    And   I click the Login button
    Then  I should be redirected to the Products page
    And   the product list should be displayed

  @negative @TC-01b
  Scenario: TC-01b - Login fails with invalid credentials
    When  I enter username "invalid_user" and password "wrong_pass"
    And   I click the Login button
    Then  a login error should contain "Username and password do not match"
    And   I should remain on the login page

  @negative @TC-01c
  Scenario: TC-01c - Locked-out user cannot login
    When  I enter username "locked_out_user" and password "secret_sauce"
    And   I click the Login button
    Then  a login error should contain "Sorry, this user has been locked out"

  @negative @TC-01d
  Scenario: TC-01d - Login fails when username is blank
    When  I click the Login button
    Then  a login error should contain "Username is required"
