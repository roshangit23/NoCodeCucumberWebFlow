Feature: Element Display functionality
 @First
  Scenario: Checking if an element is displayed
    Given I am on the "ElementDisplayedExample" section
    Then I click the "ShowButton"
    Then The element "SampleElement" should be displayed
@Last
  Scenario: Checking if an element is not displayed
    Given I am on the "ElementDisplayedExample" section
    Then I click the "HideButton"
    Then The element "SampleElement" should not be displayed