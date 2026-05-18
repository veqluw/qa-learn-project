package com.automation.demoqa.api.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.automation.config.Prop.PROP;
import static io.restassured.RestAssured.given;

public class ApiSpec {

    public static RequestSpecification request() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(PROP.getDemoqaBaseUrl())
                .contentType(ContentType.JSON);
    }

}
