package com.example.espressoappformentoring;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TransitionTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testScreenTransitionsAndSystemBackNavigation() {
        System.out.println("Step 1: Confirming initial state on First Fragment...");
        onView(withId(R.id.button_first)).check(matches(isDisplayed()));

        System.out.println("Step 2: Navigating from First to Second Fragment...");
        onView(withId(R.id.button_first)).perform(click());
        onView(withId(R.id.button_second)).check(matches(isDisplayed()));

        System.out.println("Step 3: Returning to First Fragment using UI button...");
        onView(withId(R.id.button_second)).perform(click());
        onView(withId(R.id.button_first)).check(matches(isDisplayed()));

        System.out.println("Step 4: Navigating again to Second Fragment...");
        onView(withId(R.id.button_first)).perform(click());

        System.out.println("Step 5: Navigating back to First Fragment using system Back button...");
        pressBack();

        System.out.println("Step 6: Confirming successful return to First Fragment...");
        onView(withId(R.id.button_first)).check(matches(isDisplayed()));
    }
}