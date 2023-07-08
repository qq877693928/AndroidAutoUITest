package com.example.ui.autotest.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ui.autotest.app.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this@LoginActivity))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.loginButton.setOnClickListener {
            // hide soft 键盘
            binding.editTextEmailName.clearFocus()
            binding.editTextPassword.clearFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            val userEmail = binding.editTextEmailName.text?.toString()
            val password = binding.editTextPassword.text?.toString()
            val result = LoginChecker().checkEmailAndPassword(userEmail, password)
            if (result) {
                this@LoginActivity.let { context ->
                    context.startActivity(Intent(context, MainActivity::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this@LoginActivity, "login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
