// contract/AuthContract.kt
package com.example.nurseryguard.contract

interface AuthContract {
    interface View {
        fun showAuthSuccess(message: String)
        fun showAuthError(error: String)
    }

    interface Presenter {
        fun register(fullName: String, email: String, phone: String, password: String, confirm: String)
        fun login(email: String, password: String)
    }

    interface Model {
        fun register(fullName: String, email: String, phone: String, password: String, callback: (Boolean, String) -> Unit)
        fun login(email: String, password: String, callback: (Boolean, String) -> Unit)
    }
}
