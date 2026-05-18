package com.automation.demoqa.api.data.factory;

import com.automation.demoqa.api.model.User;
import com.github.javafaker.Faker;

import java.util.Random;

public class UserDataFactory {

    private static final Faker faker = new Faker();

    public static User.UserBuilder defaultUser() {
        return User.builder()
                .userName(faker.name().firstName())
                .password(generateValidPassword());
    }

    private static String generateValidPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";

        Random random = new Random();

        String password = "" +
                upper.charAt(random.nextInt(upper.length())) +
                lower.charAt(random.nextInt(lower.length())) +
                digits.charAt(random.nextInt(digits.length())) +
                special.charAt(random.nextInt(special.length()));

        password += faker.internet().password(4, 8);

        return password;
    }
}
