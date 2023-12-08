Feature: Browser Manipulation functionality
  @First
  Scenario: Resizing and moving the browser
    When I resize the browser to width 800 and height 600
    And I move the browser to x 100 and y 100
    When I zoom the page to 1.5
    And I scroll by x 0 and y 500
    When I refresh the browser
    When I navigate to URL "https://www.example.com"
    Then The current page URL should be "https://www.example.com/"
    And The current page title should be "Example Domain"

  @Last
  Scenario: Waiting for the page to load completely
    When I navigate to URL "https://www.rahulshettyacademy.com/AutomationPractice/"
    And I wait for the page to load completely
    Then The current page title should be "Practice Page"
