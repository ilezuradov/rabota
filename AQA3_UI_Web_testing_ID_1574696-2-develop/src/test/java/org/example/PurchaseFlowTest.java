package org.example;

import org.example.pages.CartPageNoCucumber;
import org.example.pages.CheckoutPageNoCucumber;
import org.example.pages.LoginPageNoCucumber;
import org.example.pages.ProductsPageNoCucumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class PurchaseFlowTest extends BaseTest {

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Полный путь покупки через Page Object")
    void fullPurchasePath() {
        ProductsPageNoCucumber productsPage = new LoginPageNoCucumber()
                .openPage()
                .loginAs("standard_user", "secret_sauce")
                .checkPageOpened()
                .addFirstProductToCart()
                .checkCartBadgeCounter("1");

        CartPageNoCucumber cartPage = productsPage.goToCart().checkPageOpened();
        CheckoutPageNoCucumber checkoutPage = cartPage.clickCheckout();

        checkoutPage
                .fillUserInfo("John", "Doe", "12345")
                .clickContinue()
                .clickFinish()
                .checkOrderCompleted();
    }

    @Test
    @DisplayName("Негативный: неверный пароль")
    void loginWithWrongPassword() {
        new LoginPageNoCucumber()
                .openPage()
                .enterUsername("standard_user")
                .enterPassword("wrong_password")
                .clickLogin()
                .checkErrorMessage("Username and password do not match");
    }

    @Test
    @DisplayName("Негативный: оформление без заполнения полей")
    void checkoutWithoutFillingFields() {
        CheckoutPageNoCucumber checkoutPage = new LoginPageNoCucumber()
                .openPage()
                .loginAs("standard_user", "secret_sauce")
                .addFirstProductToCart()
                .goToCart()
                .clickCheckout();

        checkoutPage
                .clickContinue()
                .checkErrorMessage("First Name is required");
    }
}
