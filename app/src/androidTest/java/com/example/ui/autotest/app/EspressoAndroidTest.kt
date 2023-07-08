package com.example.ui.autotest.app

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class EspressoAndroidTest {

    @Test
    fun should_start_main_activity_when_execute_login_given_valid_username_and_password() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editTextEmailName)).perform(typeText("123@163.com"))
        onView(withId(R.id.editTextPassword)).perform(typeText("123456"))
        Intents.init()
        onView(withId(R.id.loginButton)).perform(click())
        intended(hasComponent(hasClassName(MainActivity::class.java.name)))
        Intents.release()
    }

    @Test
    fun should_show_failed_toast_when_execute_login_given_invalid_username_and_password() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.editTextEmailName)).perform(typeText("123"))
        onView(withId(R.id.editTextPassword)).perform(typeText("456"))
        Intents.init()
        onView(withId(R.id.loginButton)).perform(click())
        // 检查 Toast 是否显示 (有 bug)
        onView(withText("login failed")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
        Intents.release()
    }
}
