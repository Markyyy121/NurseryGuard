package com.example.nurseryguard.view

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun showLoginSuccess(message: String)
    fun showLoginError(error: String)
}
