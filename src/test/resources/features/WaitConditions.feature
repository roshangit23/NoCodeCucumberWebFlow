Feature: Wait Conditions functionality
  @First @Last
  Scenario: Typing and selecting an option in a dynamic dropdown
    Given I am on the "SuggestionClassExample" section
    When I enter "Ind" into the "Country"
    Then I wait for element "OptionElementPath" to be visible for 10 seconds
    And I select the option "India" for the dynamic dropdown "Country" with option path "OptionElementPath"
    Then The text of the input field "Country" should be "India"
