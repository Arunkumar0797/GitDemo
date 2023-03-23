
@ErrorValidation
Feature: Error Validation
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page 
    When Logged in with username <name> and password <password>
    Then I verify the "Incorrect email or password." error message display

     Examples: 
      | name 				      || password    |
      | anshika@gmail.com || Iamking@		 |