package org.iceo.automationtests.stepsdefinition;

import io.cucumber.java8.En;
import org.iceo.fixer.service.FixerService;

import static org.iceo.fixer.service.FixerService.*;
import static org.iceo.fixer.utlis.PropertiesHandler.getBaseUrl;

public class ApiSteps implements En {

    public ApiSteps() {
        When("As a authorized user get time series response with start date {string} and end date {string}",
                (startDate, endDate) -> getTimeseriesResponse(getApiKey(), getBaseUrl(), (String) startDate, (String) endDate));
        When("As a authorized user with expired api key get time series response with start date {string} and end date {string}",
                (startDate, endDate) -> getTimeseriesResponse(getExpiredApiKey(), getBaseUrl(), (String) startDate, (String) endDate));
        When("As a authorized user get time series response from {string}",
                (url) -> getTimeseriesResponse(getApiKey(), (String) url, null, null));
        When("As a authorized user get time series response only with start date {string}",
                (startDate) -> getTimeseriesResponse(getApiKey(), getBaseUrl(), (String) startDate, null));
        When("As a authorized user get time series response with no dates",
                () -> getTimeseriesResponse(getApiKey(), getBaseUrl(), null, null));
        When("As a authorized user try to post time series response", FixerService::postTimeseriesResponse);
        When("As a unauthorized user get time series response with start date {string} and end date {string}",
                (startDate, endDate) -> getTimeseriesResponse("1234", getBaseUrl(), (String) startDate, (String) endDate));

        Then("I have {int} response code", (errorCode) -> assertResponseCode((int) errorCode));
        Then("Base is {string}", (base) -> assertBase((String) base));
    }
}
