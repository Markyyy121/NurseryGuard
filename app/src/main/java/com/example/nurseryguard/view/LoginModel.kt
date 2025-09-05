package com.example.nurseryguard.view

import android.content.Context
import android.content.SharedPreferences

class LoginModel(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("TempUserData", Context.MODE_PRIVATE)

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        val savedEmail = sharedPreferences.getString("EMAIL", "")
        val savedPassword = sharedPreferences.getString("PASSWORD", "")

        if (email == savedEmail && password == savedPassword) {
            callback(true, "Login successful!")
        } else {
            callback(false, "Invalid email or password")
        }
    }
}
