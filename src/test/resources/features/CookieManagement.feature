Feature: Cookie Management functionality
  @First @Last
  Scenario: Adding and verifying a cookie
    When I add a cookie with name "testCookie" and value "12345"
    Then A cookie with name "testCookie" should exist
    When I delete all cookies
    Then A cookie with name "testCookie" should not exist
