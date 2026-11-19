package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FirstSelenideTest extends BaseTest {

    @BeforeEach
    void setUp() {
        // Открываем сайт перед каждым тестом
        open("https://www.saucedemo.com/");
    }

    @AfterEach
    void tearDown() {
        // Закрываем браузер после каждого теста
        closeWebDriver();
    }

    @Test
    @DisplayName("Полный путь покупки: логин → добавить товар → оформить заказ")
    void fullPurchasePath() {
        // 1. Логинимся
        $(By.id("user-name")).setValue("standard_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("login-button")).click();

        // 2. Проверяем, что перешли на страницу товаров
        $(".title").shouldHave(text("Products"));

        // 3. Добавляем первый товар в корзину
        $(".inventory_item button").click();

        // 4. Проверяем, что счётчик корзины стал "1"
        $(".shopping_cart_badge").shouldHave(text("1"));

        // 5. Переходим в корзину
        $(".shopping_cart_link").click();
        $(".title").shouldHave(text("Your Cart"));

        // 6. Нажимаем Checkout
        $(By.id("checkout")).click();

        // 7. Заполняем форму
        $(By.id("first-name")).setValue("John");
        $(By.id("last-name")).setValue("Doe");
        $(By.id("postal-code")).setValue("12345");
        $(By.id("continue")).click();

        // 8. Завершаем заказ
        $(By.id("finish")).click();

        // 9. Проверяем успешное оформление
        $(".complete-header").shouldHave(text("Thank you for your order!"));
    }

    @Test
    @DisplayName("Негативный сценарий: неверный пароль")
    void loginWithWrongPassword() {
        $(By.id("user-name")).setValue("standard_user");
        $(By.id("password")).setValue("wrong_password");
        $(By.id("login-button")).click();

        // Проверяем сообщение об ошибке
        $("[data-test='error']").shouldBe(visible)
                .shouldHave(text("Username and password do not match"));
    }

    @Test
    @DisplayName("Негативный сценарий: оформление заказа без заполнения полей")
    void checkoutWithoutFillingFields() {
        // Логин
        $(By.id("user-name")).setValue("standard_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("login-button")).click();

        // Добавляем товар и идём в корзину
        $(".inventory_item button").click();
        $(".shopping_cart_link").click();
        $(By.id("checkout")).click();

        // Нажимаем Continue без заполнения
        $(By.id("continue")).click();

        // Проверяем ошибку
        $("[data-test='error']").shouldHave(text("First Name is required"));
    }
}
