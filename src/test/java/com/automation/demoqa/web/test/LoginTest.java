package com.automation.demoqa.web.test;

import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.automation.demoqa.web.data.Users.locked_user;
import static com.automation.demoqa.web.data.Users.standard_user;
import static com.automation.demoqa.web.page.LoginPage.openLoginPage;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Story("Authorization")
public class LoginTest extends BaseTest {

    @Test
    @Severity(BLOCKER)
    public void successLogin() {
        openLoginPage()
                .successAuth(standard_user)
                .openMainPage()
                .logOut("Log out");

    }

    @Test
    @Severity(BLOCKER)
    public void failLogin() {
        openLoginPage()
                .failedAuth(locked_user);
    }
}
