Feature: Radio Button functionality
  @First @Last
  Scenario: Selecting each radio button
    Given I am on the "RadioButtonExample" section
    When I click the radio button "Radio1"
    Then The element "Radio1" should be selected
    When I click the radio button "Radio2"
    Then The element "Radio2" should be selected
    When I click the radio button "Radio3"
    Then The element "Radio3" should be selected
