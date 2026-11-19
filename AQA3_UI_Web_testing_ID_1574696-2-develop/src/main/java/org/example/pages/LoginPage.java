package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement usernameField = $(By.id("user-name"));
    private final SelenideElement passwordField = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login-button"));
    private final SelenideElement errorMessage = $("[data-test='error']");

    public LoginPage openPage() {
        open("https://www.saucedemo.com/");
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameField.setValue(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickLogin() {
        loginButton.click();
        return this;
    }

    public ProductsPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        loginButton.click();
        return new ProductsPage();
    }

    public LoginPage checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
