package com.automation.reqres.api.step;

import com.automation.reqres.api.model.user.UserList;
import com.automation.reqres.api.model.user.UserFull;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static com.automation.reqres.api.spec.ReqresSpec.notFoundResponse;
import static com.automation.reqres.api.spec.ReqresSpec.reqresRequest;
import static io.restassured.RestAssured.given;

public class UserControllerStep {

    @Step("Get users list on the page {page} with users quantity = {limit}")
    public static UserList getUsersList(int page, int limit) {
        return given()
                .spec(reqresRequest())
                .basePath("/users")
                .param("page", page)
                .param("limit", limit)
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(UserList.class);
    }

    @Step("Create a new user")
    public static UserFull createUser(UserFull user) {
        return given()
                .spec(reqresRequest())
                .basePath("/users")
                .body(user).log().all()
                .post()
                .then().log().all()
                .statusCode(201)
                .extract().as(UserFull.class);
    }

    private static Response getUserById(Integer userId) {
        return given()
                .spec(reqresRequest())
                .basePath("/users/" + userId)
                .get();
    }

    @Step("Get exists user with id = {userId}")
    public static UserFull successGetUserById(Integer userId) {
        return getUserById(userId)
                .then().log().all()
                .statusCode(200)
                .extract().as(UserFull.class);
    }

    @Step("Get a non-existent user with id = {userId}")
    public static void failedGetUserById(Integer userId) {
        getUserById(userId)
                .then().log().all()
                .spec(notFoundResponse());
    }

    /**
     * email is forbidden to update
     */
    @Step("Edit user с id = {userId}")
    public static UserFull updateUserById(Integer userId, Map<String, String> fieldsForChange) {
        return given()
                .spec(reqresRequest())
                .basePath("/users/" + userId)
                .body(fieldsForChange).log().all()
                .put()
                .then().log().all()
                .statusCode(200)
                .extract().as(UserFull.class);
    }

    @Step("Delete user with id = {userId}")
    public static String deleteUserById(Integer userId) {
        return given()
                .spec(reqresRequest())
                .basePath("/users/" + userId)
                .delete()
                .then().log().all()
                .statusCode(204)
                .extract().asString();
    }

}
