package com.example.nurseryguard.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nurseryguard.R
import com.example.nurseryguard.contract.AuthContract
import com.example.nurseryguard.presenter.AuthPresenter

class LoginActivity : AppCompatActivity(), AuthContract.View {
    private lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = AuthPresenter(this, applicationContext)
        val tvLogin: TextView = findViewById(R.id.tvSignUp)

        tvLogin.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val email = findViewById<EditText>(R.id.etLoginEmail).text.toString().trim()
            val password = findViewById<EditText>(R.id.etLoginPassword).text.toString()

            // ✅ Validation
            when {
                email.isEmpty() -> {
                    showAuthError("Email is required")
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showAuthError("Enter a valid email address")
                }
                password.isEmpty() -> {
                    showAuthError("Password is required")
                }
                else -> {
                    presenter.login(email, password)
                }
            }
        }
    }

    override fun showAuthSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MonitorActivity::class.java))
        finish()
    }

    override fun showAuthError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
