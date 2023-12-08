Feature: Element Presence Check functionality
  @First @Last
  Scenario: Checking if an element is present on the page
    Given I am on the "MouseHoverExample" section
    Then The element "MouseHoverButton" should be present
