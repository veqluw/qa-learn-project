package com.automation.demoqa.web.page;

import com.automation.demoqa.web.model.Book;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    @Step("Verify main page is displayed")
    public MainPage shouldBeLoaded() {
        $("#userName-value").shouldBe(visible);
        $("table").shouldBe(visible);
        return this;
    }

    @Step("Open page with book = {book}")
    public BookPage openBookPage(Book book) {
        $(byText(book.getTitle()))
                .shouldBe(clickable)
                .click();
        return new BookPage().shouldBeLoaded();
    }

}
