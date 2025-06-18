Feature: SIMBAT Inventory Management Flow
  As a user
  I want to manage inventory through the complete flow
  So that I can track drugs from warehouse to clinic and checkout

  @inventory_flow @positive
  Scenario: Complete inventory flow from warehouse to clinic checkout
    Given I am logged in as admin
    When I navigate to the inventory page
    And I add new stock with the following details
      | vendor      | medicineName       | paymentMethod | quantity | unit | price  | expiryDate |
      | Vendor Test | Paracetamol 500mg  | Cash          | 100      | Pcs  | 500000 | 2025-12-31 |
    And I save the inventory

    Then I should be on the checkout page
    And I select checkout type "Inventory"
    And I add item "Paracetamol 500mg" with quantity "20"
    And I proceed with checkout

    When I navigate to the history page
    When I logout from the system
    Then I should be logged out successfully