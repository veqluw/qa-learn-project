package com.automation.reqres.api.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.automation.reqres.api.util.CustomApiListener.allureRequest;
import static com.automation.config.Prop.PROP;

public class ReqresSpec {

    public static RequestSpecification reqresRequest() {
        return allureRequest()
                .baseUri(PROP.getReqresBaseUrl())
                .contentType(ContentType.JSON)
                .header("x-api-key", PROP.getReqresToken());
    }

    public static ResponseSpecification notFoundResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }
}