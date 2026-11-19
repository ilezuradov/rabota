package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * manual-solution
 * Ручная реализация Page Object для страницы логина saucedemo.com.
 */
public class LoginPageNoCucumber {

    private final SelenideElement usernameField = $(By.id("user-name"));
    private final SelenideElement passwordField = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login-button"));
    private final SelenideElement errorMessage = $("[data-test='error']");

    public LoginPageNoCucumber openPage() {
        open("https://www.saucedemo.com/");
        return this;
    }

    public LoginPageNoCucumber enterUsername(String username) {
        usernameField.setValue(username);
        return this;
    }

    public LoginPageNoCucumber enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPageNoCucumber clickLogin() {
        loginButton.click();
        return this;
    }

    public ProductsPageNoCucumber loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        loginButton.click();
        return new ProductsPageNoCucumber();
    }

    public LoginPageNoCucumber checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
