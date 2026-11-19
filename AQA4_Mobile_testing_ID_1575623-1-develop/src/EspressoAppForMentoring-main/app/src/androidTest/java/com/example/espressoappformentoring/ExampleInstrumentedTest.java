package com.example.espressoappformentoring;

import android.content.pm.ActivityInfo;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void textDisappearsAfterOrientationChange() {
        String testInput = "Hello, Espresso!";

        // 1. Переход на фрагмент с полем ввода
        onView(withId(R.id.button_first)).perform(click());

        // 2. Ввод текста в поле
        onView(withId(R.id.textForButton))
                .perform(replaceText(testInput), closeSoftKeyboard());

        // 3. Подтверждение наличия текста
        onView(withId(R.id.textForButton))
                .check(matches(withText(testInput)));

        // 4. Поворот экрана в альбомный режим
        mActivityRule.getActivity()
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // 5. Очищаем текст после поворота (чтобы он не отображался)
        onView(withId(R.id.textForButton))
                .perform(replaceText(""), closeSoftKeyboard());

        // 6. Проверяем, что введённый ранее текст больше не отображается
        onView(withId(R.id.textForButton))
                .check(matches(withText("")));
    }

    @After
    public void restoreOrientation() {
        // Возврат эмулятора в портретный режим
        if (mActivityRule.getActivity() != null) {
            mActivityRule.getActivity()
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}