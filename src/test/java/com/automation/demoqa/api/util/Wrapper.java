package com.automation.demoqa.api.util;

import com.automation.demoqa.api.dto.ApiResponse;
import io.restassured.response.Response;

public class Wrapper {

    public static <T> ApiResponse<T> wrap(Response response, Class<T> clazz) {
        return new ApiResponse<>(response, clazz);
    }

}
