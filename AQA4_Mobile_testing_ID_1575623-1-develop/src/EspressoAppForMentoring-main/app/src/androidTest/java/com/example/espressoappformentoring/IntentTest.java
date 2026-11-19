package com.example.espressoappformentoring;

// Импорты для работы с Intents (специальной библиотекой для проверки переходов)
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

// Импорты аннотаций и раннера JUnit
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// Статические импорты методов Espresso для поиска и действий
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

// Статические импорты для проверки именно переходов (Intents)
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

// Статические импорты матчеров для поиска элементов и проверки текста
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

// Указываем JUnit запускать этот класс с помощью AndroidJUnit4 раннера
@RunWith(AndroidJUnit4.class)
public class IntentTest {

    // Правило: автоматически запускает MainActivity и включает перехват Intents
    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testIntentToSecondActivity() {
        // 0. ПРОВЕРКА: Убеждаемся, что мы в первой активности
        onView(withId(R.id.switchActivityButton)).check(matches(isDisplayed()));

        // 1. Клик по кнопке перехода
        onView(withId(R.id.switchActivityButton)).perform(click());

        // 2. Проверка вызова Intent
        intended(hasComponent(SecondActivity.class.getName()));

        // 3. Проверка текста на втором экране
        onView(withId(R.id.secondActText))
                .check(matches(withText("Hello second activity")));
    }
}