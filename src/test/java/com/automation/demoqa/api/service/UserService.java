package com.automation.demoqa.api.service;

import com.automation.demoqa.api.config.ApiSpec;
import com.automation.demoqa.api.model.JwtToken;
import com.automation.demoqa.api.model.User;
import io.restassured.response.Response;

public class UserService {

    public Response createUser(User user) {
        return ApiSpec.request()
                .basePath("/Account/v1/User")
                .body(user)
                .when()
                .post();

    }

    public Response getUserById(String id, JwtToken token) {
        return ApiSpec.request()
                .header("Authorization", "Bearer " + token.getToken())
                .basePath("/Account/v1/User/" + id)
                .when()
                .get();

    }

    public Response generateToken(User user) {
        return ApiSpec.request()
                .basePath("/Account/v1/GenerateToken")
                .body(user)
                .when()
                .post();

    }

}
