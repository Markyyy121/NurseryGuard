package com.example.nurseryguard.register

import com.example.nurseryguard.view.RegisterModel
import com.example.nurseryguard.view.RegisterView

class RegisterPresenter(private val model: RegisterModel) {

    private var view: RegisterView? = null

    fun attachView(view: RegisterView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun register(fullName: String, email: String, phone: String, password: String, confirm: String) {
        if (fullName.isEmpty()) {
            view?.showAuthError("Full name is required")
            return
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.showAuthError("Enter a valid email address")
            return
        }
        if (phone.isEmpty() || phone.length < 10) {
            view?.showAuthError("Enter a valid phone number")
            return
        }
        if (password.isEmpty() || password.length < 6) {
            view?.showAuthError("Password must be at least 6 characters")
            return
        }
        if (confirm != password) {
            view?.showAuthError("Passwords do not match")
            return
        }

        view?.showLoading()

        model.saveUser(fullName, email, phone, password) { success, message ->
            view?.hideLoading()
            if (success) {
                view?.showAuthSuccess(message)
            } else {
                view?.showAuthError(message)
            }
        }
    }
}
