
@tag
Feature: Place Order in Ecommerce website
  I want to use this template for my feature file

  Background:
  Given landed on Ecommerce page
  
  @Regression
  Scenario Outline: Positive test in submit order
    Given logged in with username <userName> and password <password>
    When add to product <productName> to cart
    And  checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is diplayed in confirmation page

    Examples: 
      | userName           | password   | productName        |
      | rampr@gmail.com    | Fluke@5011 | ADIDAS ORIGINAL    |
      |ram888@gmail.com    |Fluke@5011  | ZARA COAT 3        |
