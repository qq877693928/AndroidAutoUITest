package com.example.ui.autotest.app

class LoginChecker {

    private fun isUserNameValid(userName: String?): Boolean {
        userName ?: return false
        return if (userName.contains("@")) {
            val emailRegex = Regex("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
            return emailRegex.matches(userName)
        } else {
            false
        }
    }

    private fun isPasswordValid(password: String?): Boolean {
        password ?: return false
        return password.trim().length >= 6
    }

    fun checkEmailAndPassword(userEmail: String?, password: String?): Boolean =
        isUserNameValid(userEmail) && isPasswordValid(password)
}
