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

class CreateAccountActivity : AppCompatActivity(), AuthContract.View {
    private lateinit var presenter: AuthPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        presenter = AuthPresenter(this, applicationContext)

        val tvLogin: TextView = findViewById(R.id.tvLogin)

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btnCreateAccount).setOnClickListener {
            val fullName = findViewById<EditText>(R.id.etFullName).text.toString().trim()
            val email = findViewById<EditText>(R.id.etEmail).text.toString().trim()
            val phone = findViewById<EditText>(R.id.etPhone).text.toString().trim()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            val confirm = findViewById<EditText>(R.id.etConfirmPassword).text.toString()

            // ✅ Validation
            when {
                fullName.isEmpty() -> {
                    showAuthError("Full name is required")
                }
                email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showAuthError("Enter a valid email address")
                }
                phone.isEmpty() || phone.length < 10 -> {
                    showAuthError("Enter a valid phone number")
                }
                password.isEmpty() || password.length < 6 -> {
                    showAuthError("Password must be at least 6 characters")
                }
                confirm.isEmpty() || confirm != password -> {
                    showAuthError("Passwords do not match")
                }
                else -> {
                    // If all validations pass → proceed with register
                    presenter.register(fullName, email, phone, password, confirm)
                }
            }
        }
    }

    override fun showAuthSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showAuthError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
