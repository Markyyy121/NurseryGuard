package com.example.nurseryguard.login

import com.example.nurseryguard.view.LoginModel
import com.example.nurseryguard.view.LoginView

class LoginPresenter(private val model: LoginModel) {

    private var view: LoginView? = null

    fun attachView(view: LoginView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun login(email: String, password: String) {
        if (email.isEmpty()) {
            view?.showLoginError("Email is required")
            return
        }
        if (password.isEmpty()) {
            view?.showLoginError("Password is required")
            return
        }

        view?.showLoading()
        model.login(email, password) { success, message ->
            view?.hideLoading()
            if (success) {
                view?.showLoginSuccess(message)
            } else {
                view?.showLoginError(message)
            }
        }
    }
}
