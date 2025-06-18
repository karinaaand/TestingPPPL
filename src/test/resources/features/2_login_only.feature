@regression @login
Feature: SIMBAT Login Functionality

  As a user
  I want to be able to login to the SIMBAT system
  So that I can access the system's features

  @login @positive
  Scenario: Successful login with valid credentials
    Given The user is on the login page
    When The user enters email "admin@simbat.disyfa.site" and password "admin"
    Then The user should be redirected to the Dashboard page

  @login @negative
  Scenario Outline: Failed login with invalid credentials
    Given The user is on the login page
    When The user enters email "<email>" and password "<password>"
    Then The user should see an error message "GAGAL"

    Examples:
      | email                    | password           |
      | apalah123@gmail.com      | admin              |
      | admin@simbat.disyfa.site | linggangguliguli   |
      |                         | admin              |
      | admin@simbat.disyfa.site |                   |
      |                         |                   | 