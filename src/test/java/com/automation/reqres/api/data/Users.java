package com.automation.reqres.api.data;

import com.automation.reqres.api.model.user.UserFull;
import com.github.javafaker.Faker;

import java.util.Locale;

public class Users {

    private static final Faker faker = new Faker(new Locale("en"));

    public static UserFull userGenerator() {
        return UserFull.builder()
                .id(null)
                .email(faker.internet().emailAddress())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .avatar(faker.avatar().image())
                .build();
    }
}