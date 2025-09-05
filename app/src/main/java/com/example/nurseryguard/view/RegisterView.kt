package com.example.nurseryguard.view

interface RegisterView {
    fun showLoading()
    fun hideLoading()
    fun showAuthSuccess(message: String)
    fun showAuthError(error: String)
}
