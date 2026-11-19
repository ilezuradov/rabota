package com.example.espressoappformentoring;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ToolbarTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testOverflowMenuDisplaysSettingsAndDismissesOnClickOutside() {
        // 1. Нажимаем на кнопку выпадающего меню («Три точки» в верхнем правом углу).
        // В системе Android у этой кнопки по умолчанию установлен accessibility-атрибут "More options".
        onView(withContentDescription("More options")).perform(click());

        // 2. Проверяем, что выпадающее меню успешно открылось и в нем отображается пункт "Settings".
        onView(withText("Settings")).check(matches(isDisplayed()));

        // 3. Закрываем выпадающее меню.
        // Так как всплывающее окно (PopupWindow) находится на отдельном слое интерфейса,
        // вызов pressBack() или касание за его пределами генерирует системное событие закрытия текущего фокусного окна.
        pressBack();

        // 4. Проверяем, что меню закрылось и пункт "Settings" больше не присутствует на экране.
        onView(withText("Settings")).check(doesNotExist());
    }
}