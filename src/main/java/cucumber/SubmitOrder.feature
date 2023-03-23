
@Regression
Feature: Place order from Ecommerce Website
  I want to use this template for my feature file

Background:
 Given I landed on Ecommerce Page 

  @tag2
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product from cart <productName>
    And checkout <productName> and submit order
    Then I verify the "THANKYOU FOR THE ORDER." is displayed on confirmation page

    Examples: 
      | name 				      || password    ||productName     |
      | anshika@gmail.com || Iamking@000 ||ADIDAS ORIGINAL |
      