package projects.tpokora.com.boxingintervals;

import android.support.test.espresso.ViewAssertion;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.action.ViewActions.*;

/**
 * Created by pokor on 25.09.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);


    @Test
    public void test_timerFragmentIsShownOnMainActivityStart_success() {
        onView(withId(R.id.timer_fragment)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_title)).check(matches(withText(TestStrings.TIMER_HEADER)));
        onView(withId(R.id.settings_button)).check(matches(withText(TestStrings.TIMER_SETTINGS_BUTTON)));
    }

    @Test
    public void test_settingsFragmentIsShownOnSettingsButtonClick_success() {
        onView(withId(R.id.settings_button)).perform(click());
        onView(withId(R.id.activity_title)).check(matches(withText(TestStrings.TIMER_SETTINGS_HEADER)));
        onView(withId(R.id.settings_button)).check(matches(withText(TestStrings.TIMER_SETTINGS_BUTTON_DONE)));
        onView(withId(R.id.settings_fragment)).check(matches(isDisplayed()));
    }
}
