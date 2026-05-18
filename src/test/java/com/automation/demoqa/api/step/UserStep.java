package com.automation.demoqa.api.step;

import com.automation.demoqa.api.dto.ApiResponse;
import com.automation.demoqa.api.model.JwtToken;
import com.automation.demoqa.api.model.User;
import com.automation.demoqa.api.model.UserInfo;
import com.automation.demoqa.api.service.UserService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.automation.demoqa.api.util.Wrapper.wrap;

public class UserStep {

    private final UserService userService = new UserService();

    @Step("Create a user")
    public ApiResponse<UserInfo> createUser(User user) {
        return wrap(userService.createUser(user), UserInfo.class);
    }

    @Step("Get a user by id")
    public ApiResponse<UserInfo> getUserById(String id, JwtToken token) {
        return wrap(userService.getUserById(id, token), UserInfo.class);
    }

    @Step("Generate token")
    public ApiResponse<JwtToken> generateToken(User user) {
        return wrap(userService.generateToken(user), JwtToken.class);
    }


}
