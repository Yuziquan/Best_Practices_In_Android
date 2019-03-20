package com.wuchangi.logindemo;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;


import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

import static org.hamcrest.Matchers.not;


/**
 * Created by WuchangI on 2018/11/12.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void test() throws InterruptedException
    {
        onView(withId(R.id.et_user_name)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // 如果不加延时，可能Toast会检测不到
        Thread.sleep(1000);
        onView(withText(R.string.login_success_hint)).inRoot(withDecorView(not(mMainActivityActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
}
