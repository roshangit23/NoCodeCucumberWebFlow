Feature: Web Table functionality
  @First @Last
  Scenario: Reading data from web table
    Given I am on the "WebTableExample" section
    When I get text from cell at row 2 column 1 of table "CoursesTable" which should be "Rahul Shetty"

