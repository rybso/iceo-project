Feature: Fixer time series feature

  Scenario: Happy path
    When As a authorized user get time series response with start date "2020-02-03" and end date "2020-05-03"
    Then I have 200 response code
    And Base is "EUR"

  Scenario: Unauthorized
    When As a unauthorized user get time series response with start date "2020-02-03" and end date "2020-05-03"
    Then I have 401 response code

  Scenario: Forbidden
    When As a authorized user try to post time series response
    Then I have 403 response code

  Scenario: Not found
    When As a authorized user get time series response from "http://api.apilayer.com/fixer/"
    Then I have 404 response code

  Scenario: Too many request
    When As a authorized user with expired api key get time series response with start date "2020-02-03" and end date "2020-05-03"
    Then I have 429 response code