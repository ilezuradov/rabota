package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPageNoCucumber {

    private final SelenideElement firstNameField = $(By.id("first-name"));
    private final SelenideElement lastNameField = $(By.id("last-name"));
    private final SelenideElement postalCodeField = $(By.id("postal-code"));
    private final SelenideElement continueButton = $(By.id("continue"));
    private final SelenideElement finishButton = $(By.id("finish"));
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement errorMessage = $("[data-test='error']");

    public CheckoutPageNoCucumber fillUserInfo(String firstName, String lastName, String postalCode) {
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        postalCodeField.setValue(postalCode);
        return this;
    }

    public CheckoutPageNoCucumber clickContinue() {
        continueButton.click();
        return this;
    }

    public CheckoutPageNoCucumber clickFinish() {
        finishButton.click();
        return this;
    }

    public CheckoutPageNoCucumber checkOrderCompleted() {
        completeHeader.shouldHave(text("Thank you for your order!"));
        return this;
    }

    public CheckoutPageNoCucumber checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
