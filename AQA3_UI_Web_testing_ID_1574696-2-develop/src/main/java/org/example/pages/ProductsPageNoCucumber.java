package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductsPageNoCucumber {

    private final SelenideElement pageTitle = $(".title");
    private final SelenideElement addFirstProductButton = $(".inventory_item button");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement cartLink = $(".shopping_cart_link");

    public ProductsPageNoCucumber checkPageOpened() {
        pageTitle.shouldHave(text("Products"));
        return this;
    }

    public ProductsPageNoCucumber addFirstProductToCart() {
        addFirstProductButton.click();
        return this;
    }

    public ProductsPageNoCucumber checkCartBadgeCounter(String expectedCount) {
        cartBadge.shouldHave(text(expectedCount));
        return this;
    }

    public CartPageNoCucumber goToCart() {
        cartLink.click();
        return new CartPageNoCucumber();
    }
}
