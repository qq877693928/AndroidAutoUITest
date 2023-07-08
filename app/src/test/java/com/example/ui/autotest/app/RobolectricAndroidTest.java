package com.example.ui.autotest.app;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.test.annotation.UiThreadTest;

/**
 * 区别 {@link JavaUnitTest} 测试可以带上Android 资源
 */
@RunWith(RobolectricTestRunner.class)
public class RobolectricAndroidTest {

    @Test
    @UiThreadTest
    public void should_pass_valid_when_username_and_password_is_valid() {
        boolean result = new LoginChecker().checkEmailAndPassword("123456@qq.com", "123456");
        Log.d("RobolectricAndroidTest", "result = " + result);
        Assert.assertTrue(result);
    }

    @Test
    @UiThreadTest
    public void should_return_invalid_username_state_when_username_is_invalid() {
        boolean result = new LoginChecker().checkEmailAndPassword("1", "123456");
        Log.d("RobolectricAndroidTest", "result = " + result);
        Assert.assertFalse(result);
    }

    @Test
    @UiThreadTest
    public void should_return_invalid_password_state_when_password_is_invalid() {
        boolean result = new LoginChecker().checkEmailAndPassword("123@163.com", "1234");
        Log.d("RobolectricAndroidTest", "result = " + result);
        Assert.assertFalse(result);
    }
}
