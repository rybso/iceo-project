package org.iceo.fixer.service;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.iceo.fixer.models.TimeSeries;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.iceo.fixer.utlis.PropertiesHandler.getBaseUrl;

@Slf4j
public class FixerService {

    public FixerService() {
    }

    private static Response response;

    public static String getApiKey() {
        return "4VMF7J98Rp7gm0KhBhX35jOpAy8460aZ";
    }

    public static String getExpiredApiKey() {
        return "GLvdL7IX5hIqn7323ibnx9tKrkay572z";
    }

    public static void postTimeseriesResponse() {
        log.info("Trying to post to: {}", getBaseUrl());
        response = given()
                .header("apikey", getApiKey())
                .queryParam("start_date", "")
                .queryParam("end_date", "")
                .when()
                .post(getBaseUrl());
    }

    public static void getTimeseriesResponse(String apiKey, String url, String startDate, String endDate) {
        log.info("Getting time series response with start date: {} and end date: {}", startDate, endDate);
        response = given()
                .header("apikey", apiKey)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .when()
                .get(url);
    }

    public static void assertResponseCode(int responseCode) {
        assertThat(response.getStatusCode()).isEqualTo(responseCode);
    }

    @SneakyThrows
    public static void assertBase(String base) {
        ObjectMapper mapper = new ObjectMapper();
        TimeSeries timeSeries = mapper.readValue(response.getBody().asString(), TimeSeries.class);
        assertThat(timeSeries.getBase()).isEqualTo(base);
    }
}
