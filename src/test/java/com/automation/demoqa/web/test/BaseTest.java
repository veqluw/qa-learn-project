package com.automation.demoqa.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

import static com.automation.config.Prop.PROP;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        Configuration.remote =
                System.getProperty(
                        "remote",
                        "http://localhost:4444/wd/hub"
                );

        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));//logger
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = PROP.getDemoqaBaseUrl();
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20000; //20 seconds

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("128.0");

        Map<String, Object> selenoid = new HashMap<>();
        selenoid.put("enableVNC", true);
        selenoid.put("enableVideo", true);

        options.setCapability("selenoid:options", selenoid);

        Configuration.browserCapabilities = options;

    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
