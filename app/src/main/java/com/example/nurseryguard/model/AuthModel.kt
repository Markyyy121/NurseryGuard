// model/AuthModel.kt
package com.example.nurseryguard.model

import android.content.Context
import com.example.nurseryguard.contract.AuthContract
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class AuthModel(private val context: Context) : AuthContract.Model {

    private val fileName = "users.json"

    override fun register(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        val file = File(context.filesDir, fileName)
        val usersArray = if (file.exists()) {
            JSONArray(file.readText())
        } else {
            JSONArray()
        }

        // ❌ Check if email already exists
        for (i in 0 until usersArray.length()) {
            val user = usersArray.getJSONObject(i)
            if (user.getString("email") == email) {
                callback(false, "Email already registered")
                return
            }
        }

        // ✅ Save new user
        val newUser = JSONObject().apply {
            put("fullName", fullName)
            put("email", email)
            put("phone", phone)
            put("password", password) // ⚠️ In production, hash passwords!
        }
        usersArray.put(newUser)

        file.writeText(usersArray.toString())
        callback(true, "Account created successfully")
    }

    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            callback(false, "No registered users")
            return
        }

        val usersArray = JSONArray(file.readText())

        for (i in 0 until usersArray.length()) {
            val user = usersArray.getJSONObject(i)
            if (user.getString("email") == email && user.getString("password") == password) {
                callback(true, "Login successful")
                return
            }
        }

        callback(false, "Invalid email or password")
    }
}
