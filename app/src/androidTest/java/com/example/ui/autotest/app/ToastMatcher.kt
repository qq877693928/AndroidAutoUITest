package com.example.ui.autotest.app

import android.util.Log
import android.view.WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ToastMatcher: TypeSafeMatcher<Root>() {

    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams.get().type
        Log.d("ToastMatcher", "windowLayoutParams.type = $type")
        if (type == FIRST_APPLICATION_WINDOW) {
            val windowToken = root.decorView.windowToken
            val appToken = root.decorView.applicationWindowToken
            Log.d("ToastMatcher", "windowToken = $windowToken, appToken = $appToken")
            if (windowToken == appToken) {
                return true
            }
        }
        return false
    }
}
