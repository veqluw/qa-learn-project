package com.automation.demoqa.api.test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

import static com.automation.config.Prop.PROP;

public abstract class BaseTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = PROP.getDemoqaBaseUrl();
        RestAssured.defaultParser = Parser.JSON;
    }
}
