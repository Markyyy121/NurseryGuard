// presenter/AuthPresenter.kt
package com.example.nurseryguard.presenter

import android.content.Context
import com.example.nurseryguard.contract.AuthContract
import com.example.nurseryguard.model.AuthModel

class AuthPresenter(private val view: AuthContract.View, context: Context) : AuthContract.Presenter {
    private val model: AuthContract.Model = AuthModel(context)

    override fun register(fullName: String, email: String, phone: String, password: String, confirm: String) {
        if (password != confirm) {
            view.showAuthError("Passwords do not match")
            return
        }

        model.register(fullName, email, phone, password) { success, message ->
            if (success) view.showAuthSuccess(message) else view.showAuthError(message)
        }
    }

    override fun login(email: String, password: String) {
        model.login(email, password) { success, message ->
            if (success) view.showAuthSuccess(message) else view.showAuthError(message)
        }
    }
}
