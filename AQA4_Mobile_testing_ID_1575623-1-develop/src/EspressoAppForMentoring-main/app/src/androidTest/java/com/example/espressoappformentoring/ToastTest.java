package com.example.espressoappformentoring;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ToastTest {

    // Инициализируем правило для запуска MainActivity перед каждым тестом
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testToastMessage() {
        // Шаг 1: Кликаем по плавающей кнопке (FAB) в интерфейсе
        onView(withId(R.id.fab)).perform(click());

        // Шаг 2: Убеждаемся, что Snackbar с ожидаемым текстом успешно отобразился на экране
        onView(withText("May be some teast text"))
                .check(matches(isDisplayed()));
    }
}