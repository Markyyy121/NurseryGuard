package com.example.nurseryguard.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.nurseryguard.R
import com.example.nurseryguard.login.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var cbRememberMe: CheckBox
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bind views
        progressBar = ProgressBar(this).apply { visibility = View.GONE } // optional inline progress
        etEmail = findViewById(R.id.etLoginEmail)
        etPassword = findViewById(R.id.etLoginPassword)
        cbRememberMe = findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)

        // Initialize Presenter
        presenter = LoginPresenter(LoginModel(applicationContext))
        presenter.attachView(this)

        btnLogin.setOnClickListener {
            presenter.login(
                etEmail.text.toString().trim(),
                etPassword.text.toString().trim()
            )
        }

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    // View responsibilities
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showLoginSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    override fun showLoginError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
