Feature: Fixer time series feature

  Scenario: Happy path
    When As a authorized user get time series response with start date "2020-02-03" and end date "2020-05-03"
    Then I have 200 response code

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

  Scenario: No timeframe supplied
    When As a authorized user get time series response with no dates
    Then I have 501 error code

  Scenario: Invalid start date
    When As a authorized user get time series response with start date "2" and end date "2012-05-03"
    Then I have 502 error code

  Scenario: Invalid end date
    When As a authorized user get time series response with start date "2020-02-03" and end date "2"
    Then I have 503 error code

  Scenario: Invalid time frame
    When As a authorized user get time series response with start date "2020-02-03" and end date "2000-03-03"
    Then I have 504 error code

  Scenario: Time frame too long
    When As a authorized user get time series response with start date "2000-02-03" and end date "2020-03-03"
    Then I have 505 error code