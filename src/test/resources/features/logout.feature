@logout
Feature: Logout Functionality
  As a logged-in SauceDemo user
  I want to log out of the application
  So that my session is securely terminated

  Background:
    Given I am logged in with username "standard_user" and password "secret_sauce"

  @smoke @TC-04
  Scenario: TC-04 - User can logout from the Products page
    When  I open the burger menu
    And   I click the Logout link
    Then  I should be redirected to the login page
    And   the login form should be visible

  @TC-04b
  Scenario: TC-04b - Session is invalidated after logout
    When  I open the burger menu
    And   I click the Logout link
    And   I navigate directly to "/inventory.html"
    Then  I should be redirected to the login page
