package com.example.ui.autotest.app

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Collections

@RunWith(AndroidJUnit4::class)
@LargeTest
class UIAutomatorAndroidTest {
    private var mDevice: UiDevice? = null

    @Before
    fun startActivityFromHomeScreen() {
        // 初始化UiDevice
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        // 回到主界面
        mDevice?.pressHome()
        // 等待launcher
        val launcherPackage = mDevice?.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        mDevice?.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )
        // 启动目标APP
        val context: Context = ApplicationProvider.getApplicationContext()
        val intent = Intent().apply {
            setPackage(APP_PACKAGE)
        }
        val pm: PackageManager = context.packageManager
        val resolveActivities = pm.queryIntentActivities(intent, 0)
        Collections.sort(resolveActivities, ResolveInfo.DisplayNameComparator(pm))
        if (resolveActivities.size > 0) {
            val launchFirstActivity = resolveActivities[0]
            val activity = launchFirstActivity.activityInfo
            val name = ComponentName(activity.applicationInfo.packageName, activity.name)
            val i = Intent(Intent.ACTION_MAIN).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                component = name
            }
            context.startActivity(i)
        }
        // 等待应用启动
        mDevice?.wait(
            Until.hasObject(By.pkg(APP_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )
    }

    // 账户密码登录成功后主界面显示用户名
    @Test
    fun should_show_username_in_main_activity_when_login_success() {
        // 输入账户名
        mDevice?.findObject(By.res(APP_PACKAGE, "editTextEmailName"))?.text = "123@163.com"
        // 输入密码
        mDevice?.findObject(By.res(APP_PACKAGE, "editTextPassword"))?.text = "123456"
        // 点击登录
        mDevice?.findObject(By.res(APP_PACKAGE, "loginButton"))?.click()
        // 验证主界面上显示用户名信息
        val text = mDevice?.wait(Until.findObject(By.res(APP_PACKAGE, "mainContentText")), 500)
        Assert.assertEquals("Hello World!", text?.text)
    }

    companion object {
        private const val APP_PACKAGE: String = "com.example.ui.autotest.app"
        private const val LAUNCH_TIMEOUT = 5000
    }
}
