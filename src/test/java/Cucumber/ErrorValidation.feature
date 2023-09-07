
@tag
Feature: Error validation on Ecommerect page
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Negative test for login page
    Given landed on Ecommerce page
    When logged in with username <userName> and password <password>
    Then "Incorrect email or password." message is displayed

    
     Examples: 
      | userName           | password   | 
      | rampr@gmail.com    | Fluke@501  | 	
   