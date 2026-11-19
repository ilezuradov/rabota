package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * ai-assisted-solution
 * Сгенерировано ИИ. НЕ используется в тестах — только для сравнения
 * с ручным вариантом (LoginPageNoCucumber.java).
 */
public class LoginPageAiGenerated {

    private SelenideElement usernameField = $("#user-name");
    private SelenideElement passwordField = $("#password");
    private SelenideElement loginButton = $(".submit-button");
    private SelenideElement errorMessage = $(".error-message-container");

    public void login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
    }

    public void checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
    }
}
