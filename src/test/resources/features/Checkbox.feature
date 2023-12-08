Feature: Checkbox functionality
  @First
  Scenario: Checking and unchecking each checkbox
    Given I am on the "CheckboxExample" section
    When I check the checkbox "Option1"
    Then The element "Option1" should be selected
    When I uncheck the checkbox "Option1"
    Then The element "Option1" should not be selected

  Scenario: Checking and unchecking each checkbox
    Given I am on the "CheckboxExample" section
    When I check the checkbox "Option2"
    Then The element "Option2" should be selected
    When I uncheck the checkbox "Option2"
    Then The element "Option2" should not be selected

  @Last
  Scenario: Checking and unchecking each checkbox
    Given I am on the "CheckboxExample" section
    When I check the checkbox "Option3"
    Then The element "Option3" should be selected
    When I uncheck the checkbox "Option3"
    Then The element "Option3" should not be selected
