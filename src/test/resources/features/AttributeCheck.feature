Feature: Attribute Check functionality
  @First
  Scenario: Verifying the attribute of an element
    Given I am on the "SwitchTabExample" section
    Then The attribute "href" of element "OpenTab" should be "https://www.qaclickacademy.com/"

  @Last
  Scenario: Verifying the CSS value of an element
    Given I am on the "WebTableFixedHeader" section
    When I get the CSS value "color" of element "FixedTableHeader"
    Then The color of element "FixedTableHeader" should be "rgba(250, 235, 215, 1)"