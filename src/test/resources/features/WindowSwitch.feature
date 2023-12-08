Feature: Window switch functionality
  @First
  Scenario: Open and switch to new window
    Given I am on the "SwitchWindowExample" section
    When I click the "OpenWindowButton"
    Then I switch to window with url "https://www.qaclickacademy.com/"
    And I am on the "qaclickacademy" page
    Then I switch to window with url "https://rahulshettyacademy.com/AutomationPractice/"

  @Last
  Scenario: Open and switch to new tab
    Given I am on the "SwitchTabExample" section
    When I click the "OpenTabButton"
    Then I switch to window with url "https://www.qaclickacademy.com/"
    And I am on the "qaclickacademy" page
    Then I switch to window with url "https://rahulshettyacademy.com/AutomationPractice/"
