package org.example.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;

import static com.codeborne.selenide.Selenide.open;

public class PurchaseSteps {

    private final LoginPage loginPage = new LoginPage();
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Дано("Пользователь на странице логина")
    public void openLoginPage() {
        open("https://www.saucedemo.com/");
    }

    @Когда("Он вводит логин {string} и пароль {string}")
    public void enterCredentials(String login, String password) {
        loginPage.enterUsername(login).enterPassword(password);
    }

    @Когда("Он нажимает кнопку Login")
    public void clickLogin() {
        // Реальная навигация — внутри Page Object
        loginPage.clickLogin();
        // После нажатия Login мы либо на странице товаров, либо остаёмся на логине
        // Проверка «Открывается страница товаров» будет следующим шагом
        productsPage = new ProductsPage();
    }

    @Тогда("Открывается страница товаров")
    public void checkProductsPageOpened() {
        productsPage.checkPageOpened();
    }

    @Когда("Он добавляет первый товар в корзину")
    public void addFirstProduct() {
        productsPage.addFirstProductToCart();
    }

    @Тогда("Счётчик корзины показывает {string}")
    public void checkCartBadge(String count) {
        productsPage.checkCartBadgeCounter(count);
    }

    @Когда("Он переходит в корзину")
    public void goToCart() {
        cartPage = productsPage.goToCart();
    }

    @Тогда("Открывается страница корзины")
    public void checkCartPageOpened() {
        cartPage.checkPageOpened();
    }

    @Когда("Он нажимает Checkout")
    public void clickCheckout() {
        checkoutPage = cartPage.clickCheckout();
    }

    @Когда("Он заполняет данные: имя {string}, фамилия {string}, индекс {string}")
    public void fillUserInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.fillUserInfo(firstName, lastName, postalCode);
    }

    @Когда("Он нажимает Continue")
    public void clickContinue() {
        checkoutPage.clickContinue();
    }

    @Когда("Он нажимает Finish")
    public void clickFinish() {
        checkoutPage.clickFinish();
    }

    @Тогда("Заказ успешно оформлен")
    public void checkOrderCompleted() {
        checkoutPage.checkOrderCompleted();
    }

    @Тогда("Он видит сообщение об ошибке {string}")
    public void checkErrorMessage(String expectedText) {
        if (checkoutPage != null) {
            checkoutPage.checkErrorMessage(expectedText);
        } else {
            loginPage.checkErrorMessage(expectedText);
        }
    }

    @Когда("Он нажимает Continue без заполнения")
    public void clickContinueWithoutFilling() {
        checkoutPage.clickContinue();
    }
}
