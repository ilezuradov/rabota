package org.example.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.webdriver;

public class CucumberHooks {

    @Before
    public void setUp() {
        Configuration.browser     = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1600x900");
        Configuration.headless    = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout     = Long.parseLong(System.getProperty("timeout", "4000"));

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webdriver().driver().getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", "image/png",
                    new ByteArrayInputStream(screenshot), ".png");
        }
        SelenideLogger.removeListener("AllureSelenide");
        closeWebDriver();
    }
}
