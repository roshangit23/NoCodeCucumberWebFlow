Feature: iFrame functionality
  @First
  Scenario: Interacting with elements inside an iFrame
    Given I am on the "iFrameExample" section
    When I switch to frame with index 0
    Then I scroll to the element "JoinNowButton"
    Then I click the "JoinNowButton"
    Then I switch back to the default content

  @Last
  Scenario: Switching to a frame and interacting with its elements
    Given I am on the "iFrameExample" section
    When I switch to frame with locator "iFrame"
    Then I scroll to the element "JoinNowButton"
    Then I click the "JoinNowButton"
    Then I switch back to the default content