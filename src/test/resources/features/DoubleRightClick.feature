Feature: Double Click and Right Click functionality
  @First @Last
  Scenario: Double clicking and right-clicking on an element
    Given I am on the "MouseHoverExample" section
    When I double click the element "MouseHoverButton"
    And I right click the element "MouseHoverButton"
