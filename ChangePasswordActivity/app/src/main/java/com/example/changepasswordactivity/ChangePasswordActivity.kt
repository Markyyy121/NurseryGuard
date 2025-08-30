package com.example.changepasswordactivity

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var currentPasswordEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var changePasswordButton: Button
    private lateinit var clearButton: Button

    private lateinit var presenter: ChangePasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        presenter = ChangePasswordPresenter()

        currentPasswordEditText = findViewById(R.id.editTextCurrentPassword)
        newPasswordEditText = findViewById(R.id.editTextNewPassword)
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        changePasswordButton = findViewById(R.id.buttonChangePassword)
        clearButton = findViewById(R.id.buttonClear)

        changePasswordButton.setOnClickListener { validateAndChangePassword() }

        clearButton.setOnClickListener {
            currentPasswordEditText.setText("")
            newPasswordEditText.setText("")
            confirmPasswordEditText.setText("")
        }
    }

    private fun validateAndChangePassword() {
        val currentPassword = currentPasswordEditText.text.toString().trim()
        val newPassword = newPasswordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        currentPasswordEditText.error = null
        newPasswordEditText.error = null
        confirmPasswordEditText.error = null

        val result = presenter.validatePasswords(currentPassword, newPassword, confirmPassword)

        if (result.isValid) {
            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
        } else {
            // Show error on appropriate field
            when (result.message) {
                "Current password is required" -> currentPasswordEditText.error = result.message
                "New password is required",
                "Password must be at least 6 characters",
                "New password must be different from current password" -> newPasswordEditText.error = result.message
                "Please confirm your new password",
                "Passwords do not match" -> confirmPasswordEditText.error = result.message
            }

            Toast.makeText(this, "Please correct the errors", Toast.LENGTH_SHORT).show()
        }
    }
}

