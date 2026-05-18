package com.automation.demoqa.api.dto;

import io.restassured.response.Response;

public class ApiResponse<T> {

    private final Response response;
    private final T body;

    public ApiResponse(Response response, Class<T> clazz) {
        this.response = response;

        if (hasBody(response) && clazz != Void.class) {
            this.body = response.as(clazz);
        } else {
            this.body = null;
        }
    }

    public ApiResponse(Response response) {
        this.response = response;
        this.body = null;
    }

    private boolean hasBody(Response response) {
        return response.getBody() != null
                && response.getBody().asString() != null
                && !response.getBody().asString().isEmpty()
                && response.getContentType() != null;
    }

    public int statusCode() {
        return response.statusCode();
    }

    public T body() {
        return body;
    }

    public Response raw() {
        return response;
    }

}
