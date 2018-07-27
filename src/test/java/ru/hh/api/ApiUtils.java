package ru.hh.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class ApiUtils {
    static RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();

    public static String getVacancyValueOf(String vacancyID, String propertyName){
       return given().spec(requestSpecification)
                .get(EndPoints.GET_VACANCIES + "/" + vacancyID)
                .getBody()
                .jsonPath().get(propertyName).toString();
    }
}
