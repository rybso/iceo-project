package org.iceo.automationtests.stepsdefinition;

import io.cucumber.java8.En;
import org.iceo.fixer.service.FixerService;

import static org.iceo.fixer.service.FixerService.*;

public class ApiSteps implements En {

    public ApiSteps() {
        When("As a authorized user get time series response with start date {string} and end date {string}",
                (startDate, endDate) -> getTimeseriesResponse(getApiKey(), BASE_URL, (String) startDate, (String) endDate));
        When("As a authorized user with expired api key get time series response with start date {string} and end date {string}",
                (startDate, endDate) -> getTimeseriesResponse(getExpiredApiKey(), BASE_URL, (String) startDate, (String) endDate));
        When("As a authorized user get time series response from {string}",
                (url) -> getTimeseriesResponse(getApiKey(), (String) url, null, null));
        When("As a authorized user get time series response only with start date {string}",
                (startDate) -> getTimeseriesResponse(getApiKey(), BASE_URL, (String) startDate, null));
        When("As a authorized user get time series response with no dates",
                () -> getTimeseriesResponse(getApiKey(), BASE_URL, null, null));
        When("As a authorized user try to post time series response", FixerService::postTimeseriesResponse);
        When("As a unauthorized user get time series response with start date {string} and end date {string}",
                (startDate, endDate) -> getTimeseriesResponse("1234", BASE_URL, (String) startDate, (String) endDate));

        Then("I have {int} response code", (responseCode) -> assertResponseCode((int) responseCode));
        Then("I have {int} error code", (errorCode) -> assertErrorCode((int) errorCode));
    }
}
