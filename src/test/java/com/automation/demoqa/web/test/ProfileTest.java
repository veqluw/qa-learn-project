package com.automation.demoqa.web.test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.automation.demoqa.web.data.Users.standard_user;
import static com.automation.demoqa.web.page.LoginPage.openLoginPage;

@Story("Profile")
public class ProfileTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void testGoMainPageFromProfilePage() {
        openLoginPage()
                .successAuth(standard_user)
                .openMainPage()
                .logOut("Log out");

    }

}
