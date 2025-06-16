Feature: End-to-End User Flow with Login Validation and Medication Management

  As a system user
  I want to complete the full workflow with login validation and medication management
  So that I can ensure system security and a correct medication flow

  Background:
    Given The user is on the login page

  @Positive
  Scenario: Complete workflow from login to logout
    When The user enters a valid email "admin@simbat.disyfa.site" and password "admin"
    Then The user should be redirected to the Dashboard page

    When The user navigates to the Inventory page
    And The user adds a new stock with:
      | Supplier         | PT Farmasi Sejahtera |
      | Medicine Name    | Paracetamol 500mg    |
      | Payment Type     | Pay Now              |
      | Due Date         | 2025-06-16           |
      | Expiry Date      | 2025-12-31           |
      | Quantity         | 2                    |
      | Unit Price       | 5000                 |
      | Expiry in Stock  | 2025-06-30           |
    And The user clicks "Add" and "Save"
    And The user selects "Inventory" in the popup and confirms
    Then The Inventory list should show "Paracetamol 500mg"

    When The user navigates to the Clinic page
    And The user adds a new stock with the same data as above
    And The user selects "Clinic" in the popup and confirms
    Then The Clinic list should show "Paracetamol 500mg"

    When The user navigates to the Checkout page
    And The user selects "Checkout Inventory"
    And The user sets discount to 1%
    And The user adds "Paracetamol 500mg 1 pcs" with quantity 1
    Then The system should auto-fill price and available stock
    And The user proceeds to checkout and sees an invoice and success popup

    When The user selects "Checkout Clinic"
    And The user sets discount to 1%
    And The user adds "Paracetamol 500mg 1 pcs" with quantity 1
    Then The system should auto-fill price and available stock
    And The user proceeds to checkout and sees an invoice and success popup

    When The user navigates to the History page
    Then The history should show the latest checkout transactions

    When The user logs out
    Then The user should be redirected to the login page

  @Negative
  Scenario: Failed login with incorrect credentials
    When The user enters email "admin@simbat.disyfa.site" and password "wrong-password"
    Then The user should see an error message "GAGAL"

  @Negative
  Scenario: Fail to add inventory with missing required fields
    Given The user is logged in
    When The user navigates to the Inventory page
    And The user attempts to save inventory with:
      | Supplier         | (empty)              |
      | Medicine Name    | Paracetamol 500mg    |
      | Payment Type     | Pay Now              |
      | Due Date         |                      |
      | Expiry Date      |                      |
      | Quantity         |                      |
      | Unit Price       |                      |
      | Expiry in Stock  |                      |
    Then The system should show validation errors and prevent saving

  @Negative
  Scenario: Fail to add clinic stock without selecting "Clinic" in popup
    Given The user is logged in
    When The user navigates to the Clinic page
    And The user enters valid medicine data
    And The user clicks "Add" and "Save"
    And The user closes the popup without selecting "Clinic"
    Then The stock should not be saved to the Clinic list
    And The system should show an error "Please select destination"

  @Negative
  Scenario: Fail to checkout with quantity more than available stock
    Given The user is logged in
    When The user navigates to the Checkout page
    And The user selects "Checkout Inventory"
    And The user adds "Paracetamol 500mg 1 pcs" with quantity 999
    Then The system should show an error about insufficient stock

  @Negative
  Scenario: Fail to checkout without selecting any item
    Given The user is logged in
    When The user navigates to the Checkout page
    And The user directly clicks "Checkout" without adding items
    Then The system should show a warning "No items selected for checkout"
