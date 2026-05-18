package com.automation.demoqa.web.page;

import com.automation.demoqa.web.model.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private static final String usernameField = "#userName";
    private static final String passwordField = "#password";
    private static final String loginButton = "#login";

    @Step("Открыть страницу авторизации")
    public static LoginPage openLoginPage() {
        open("/login");
        $("#userForm").shouldBe(visible);
        return new LoginPage();
    }

//    public static void alertTest() {
//        open("https://the-internet.herokuapp.com/javascript_alerts");
//        $(By.tagName("button")).click();
//
//        String alert = switchTo().alert().getText();
//        System.out.println(alert);
//    }

    @Step("Успешная авторизация")
    public ProfilePage successAuth(User user) {
        $(usernameField).setValue(user.getUsername());
        $(passwordField).setValue(user.getPassword());
        $(loginButton).click();

        $("#gotoStore").should(clickable);

        return new ProfilePage();
    }

    @Step("Неуспешная авторизация")
    public void failedAuth(User user) {
        $(usernameField).setValue(user.getUsername());
        $(passwordField).setValue(user.getPassword());
        $(loginButton).click();
        $(".mb-1")
                .shouldHave(text("Invalid username or password!"));
    }
}
