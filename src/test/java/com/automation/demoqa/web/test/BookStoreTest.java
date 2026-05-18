package com.automation.demoqa.web.test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.automation.demoqa.web.data.Books.git_pocket_guide;
import static com.automation.demoqa.web.data.Users.standard_user;
import static com.automation.demoqa.web.page.LoginPage.openLoginPage;

@Story("BookStore")
public class BookStoreTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testGoToBookPageFromMainPage() {
        openLoginPage()
                .successAuth(standard_user)
                .openMainPage()
                .openBookPage(git_pocket_guide)
                .logOut("Log out");

    }

}
