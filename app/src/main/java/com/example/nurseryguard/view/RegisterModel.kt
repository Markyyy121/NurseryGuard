package com.example.nurseryguard.view

import android.content.Context
import android.content.SharedPreferences

class RegisterModel(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("TempUserData", Context.MODE_PRIVATE)

    fun saveUser(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        try {
            val editor = sharedPreferences.edit()
            editor.putString("FULL_NAME", fullName)
            editor.putString("EMAIL", email)
            editor.putString("PHONE", phone)
            editor.putString("PASSWORD", password)
            editor.apply()

            callback(true, "User registered successfully")
        } catch (e: Exception) {
            callback(false, e.message ?: "Failed to save user data")
        }
    }
}
