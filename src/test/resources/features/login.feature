Feature: Login feature

  Scenario: Login Success
    Given I open browser
    And I open Login page
    When I enter email "supattra.tangsombutpaiboon@testpro.io"
    And I enter password "te$t$tudent1"
    And I submit
    Then I am logged into the website

  Scenario: Login Incorrect Password
    Given I open browser
    And I open Login page
    When I enter email "not@correct.com"
    And I enter password "te$t$tudent1"
    And I submit
    Then I am not logged in

  Scenario: Empty Login and Password
    Given I open browser
    And I open Login page
    When I enter email ""
    And I enter password ""
    And I submit
    Then I am not logged in