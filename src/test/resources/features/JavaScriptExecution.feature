Feature: JavaScript Execution functionality
  @First @Last
  Scenario: Executing a JavaScript command
    Given I am on the "Practice" page
    Then I execute JavaScript "window.scrollTo(0, 1000)"
