Feature: Mouse Hover functionality
  @First @Last
  Scenario: Hovering over an element
    Given I am on the "MouseHoverExample" section
    When I hover over the element "MouseHoverButton"
    Then The element "SampleMouseHoverElement" should be displayed
