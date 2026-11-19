package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

/**
 * Базовый класс для всех UI-тестов.
 * Читает параметры из системных свойств (проброшены из build.gradle)
 * и настраивает Selenide один раз перед всеми тестами.
 */
public class BaseTest {

    @BeforeAll
    static void setUpSelenide() {
        Configuration.browser     = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1600x900");
        Configuration.headless    = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.timeout     = Long.parseLong(System.getProperty("timeout", "4000"));
    }
}
