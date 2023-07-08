package com.example.ui.autotest.app

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JavaUnitTest {

    @Test
    fun should_return_false_when_password_is_null() {
        val result = LoginChecker().checkEmailAndPassword("123456@qq.com", null)
        assertFalse(result)
    }

    @Test
    fun should_return_false_when_password_length_is_less_than_5() {
        val result = LoginChecker().checkEmailAndPassword("123456@qq.com", "1234")
        assertFalse(result)
    }

    @Test
    fun should_return_false_when_password_length_is_equal_5() {
        val result = LoginChecker().checkEmailAndPassword("123456@qq.com", "12345")
        assertFalse(result)
    }

    @Test
    fun should_return_true_when_password_length_greater_than_5() {
        val result = LoginChecker().checkEmailAndPassword("123456@qq.com", "123456")
        assertTrue(result)
    }
}
