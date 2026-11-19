package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPageNoCucumber {

    private final SelenideElement pageTitle = $(".title");
    private final SelenideElement checkoutButton = $(By.id("checkout"));

    public CartPageNoCucumber checkPageOpened() {
        pageTitle.shouldHave(text("Your Cart"));
        return this;
    }

    public CheckoutPageNoCucumber clickCheckout() {
        checkoutButton.click();
        return new CheckoutPageNoCucumber();
    }
}
