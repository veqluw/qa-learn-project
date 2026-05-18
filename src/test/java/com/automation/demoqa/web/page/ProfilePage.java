package com.automation.demoqa.web.page;

import com.automation.demoqa.web.model.Book;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage {

    @Step("Verify profile page is displayed")
    public MainPage openMainPage() {
        $("#gotoStore")
                .should(appear)
                .scrollIntoView(true)
                .click();
        return new MainPage();
    }

    @Step("Click trash button on book = {book}")
    public ProfilePage clickTrashButton(Book book) {
        String deleteSelector = "#delete-record-" + book.getIsbn();

        $(deleteSelector)
                .scrollIntoView(true)
                .shouldBe(clickable)
                .click();

        return this;
    }

    @Step("Confirm delete book with expected title on modal window = {expectedTitle}")
    public ProfilePage confirmDelete(String expectedTitle) {
        $("#example-modal-sizes-title-sm")
                .shouldBe(visible)
                .shouldHave(exactText(expectedTitle));

        SelenideElement ok = $("#closeSmallModal-ok");

        ok.shouldBe(clickable).click();

        return this;
    }

    public ProfilePage removeAllBooks() {
        $(byText("Delete All Books"))
                .scrollIntoView(true)
                .shouldBe(enabled)
                .click();
        return this;
    }

}
