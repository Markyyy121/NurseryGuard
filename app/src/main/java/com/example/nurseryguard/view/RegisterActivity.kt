package com.example.nurseryguard.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.nurseryguard.R
import com.example.nurseryguard.register.RegisterPresenter

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var presenter: RegisterPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirm: EditText
    private lateinit var btnCreate: Button
    private lateinit var tvLogin: TextView // Declared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Bind views
        progressBar = findViewById(R.id.progressBar)
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etConfirm = findViewById(R.id.etConfirmPassword)
        btnCreate = findViewById(R.id.btnCreateAccount)
        tvLogin = findViewById(R.id.tvLogin) // <--- ADD THIS LINE (make sure R.id.tvLogin exists in your XML)

        // Initialize Presenter
        presenter = RegisterPresenter(RegisterModel(applicationContext)) // Assuming RegisterModel exists
        presenter.attachView(this)

        btnCreate.setOnClickListener {
            presenter.register(
                etFullName.text.toString().trim(),
                etEmail.text.toString().trim(),
                etPhone.text.toString().trim(),
                etPassword.text.toString().trim(),
                etConfirm.text.toString().trim()
            )
        }

        tvLogin.setOnClickListener { // Now this is safe
            startActivity(Intent(this, LoginActivity::class.java))
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

    override fun showAuthSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showAuthError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
