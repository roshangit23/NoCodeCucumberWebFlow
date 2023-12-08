Feature: Alert functionality
  @First
  Scenario: Interacting with alert
    Given I am on the "SwitchToAlertExample" section
    When I click the "AlertButton"
    Then The alert text should be "Hello , share this practice page and share your knowledge"
    Then I accept the alert

  Scenario: Interacting with confirm
    Given I am on the "SwitchToAlertExample" section
    When I click the "ConfirmButton"
    Then I accept the confirmation alert

  @Last
  Scenario: Interacting with confirm
    Given I am on the "SwitchToAlertExample" section
    When I click the "ConfirmButton"
    Then I dismiss the confirmation alert
