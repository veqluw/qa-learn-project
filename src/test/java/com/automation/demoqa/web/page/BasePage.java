package com.automation.demoqa.web.page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    @Step("Log out from profile")
    public void logOut(String logOutText) {
        $(byText(logOutText)).shouldBe(clickable).click();
    }

}
