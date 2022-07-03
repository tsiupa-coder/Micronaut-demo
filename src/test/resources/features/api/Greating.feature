Feature: Test greeting controller

  Scenario: Make request with existing name
    When I make request
    Then Response code is correct
    Then Response body is correct