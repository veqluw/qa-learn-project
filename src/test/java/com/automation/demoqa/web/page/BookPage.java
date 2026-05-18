package com.automation.demoqa.web.page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BookPage extends BasePage {

    @Step("Check book page was loaded")
    public BookPage shouldBeLoaded() {
        $(".profile-wrapper").shouldBe(visible, enabled);
        return this;
    }

    @Step("Add book to collection")
    public BookPage addBookToCollection(){
        $("div.text-right.fullButton button")
                .scrollIntoView(true)
                .shouldBe(clickable)
                .click();
        switchTo().alert().accept();

        return this;
    }

    @Step("Open profile page")
    public ProfilePage openProfilePage() {
        $("a[href='/profile']")
                .scrollIntoView(true)
                .shouldBe(visible)
                .click();
        return new ProfilePage();
    }

    @Step("Open main page")
    public MainPage openMainPage() {
        $(byText("Back To Book Store"))
                .scrollIntoView(true)
                .shouldBe(clickable)
                .click();

        return new MainPage();
    }

}
