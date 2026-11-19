package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private final SelenideElement firstNameField = $(By.id("first-name"));
    private final SelenideElement lastNameField = $(By.id("last-name"));
    private final SelenideElement postalCodeField = $(By.id("postal-code"));
    private final SelenideElement continueButton = $(By.id("continue"));
    private final SelenideElement finishButton = $(By.id("finish"));
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement errorMessage = $("[data-test='error']");

    public CheckoutPage fillUserInfo(String firstName, String lastName, String postalCode) {
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        postalCodeField.setValue(postalCode);
        return this;
    }

    public CheckoutPage clickContinue() {
        continueButton.click();
        return this;
    }

    public CheckoutPage clickFinish() {
        finishButton.click();
        return this;
    }

    public CheckoutPage checkOrderCompleted() {
        completeHeader.shouldHave(text("Thank you for your order!"));
        return this;
    }

    public CheckoutPage checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
