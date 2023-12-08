Feature: Dropdown functionality
  @First
  Scenario: Selecting each option in the dropdown
    Given I am on the "DropdownExample" section
    Then I select "Option1" from the "SampleDropdown" dropdown
    Then I select "Option2" from the "SampleDropdown" dropdown
    Then I select "Option3" from the "SampleDropdown" dropdown


  Scenario: Typing and selecting an option in a dynamic dropdown
    Given I am on the "SuggestionClassExample" section
    When I enter "Ind" into the "Country"
    And I select the option "India" for the dynamic dropdown "Country" with option path "OptionElementPath"
    Then The text of thee input field "Country" should be "India"

  Scenario: Checking if a dropdown is enabled
    Given I am on the "DropdownExample" section
    Then The element "SampleDropdown" should be enabled

  Scenario: Scrolling to a dropdown and selecting by value and index
    Given I am on the "DropdownExample" section
    When I scroll to the element "SampleDropdown"
    Then I select value "option2" from the dropdown "SampleDropdown"
    Then I select index 3 from the dropdown "SampleDropdown"

  @Last
  Scenario: Verifying all options in a dropdown
    Given I am on the "DropdownExample" section
    Then I should see all options in dropdown "SampleDropdown"
