package com.example.changepasswordactivity

class ChangePasswordPresenter {

    fun validatePasswords( currentPassword: String, newPassword: String, confirmPassword: String
    ): ValidationResult {
        if (currentPassword.isEmpty()) {
            return ValidationResult(false, "Current password is required")
        }

        if (newPassword.isEmpty()) {
            return ValidationResult(false, "New password is required")
        }

        if (newPassword.length < 6) {
            return ValidationResult(false, "Password must be at least 6 characters")
        }

        if (newPassword == currentPassword) {
            return ValidationResult(false, "New password must be different from current password")
        }

        if (confirmPassword.isEmpty()) {
            return ValidationResult(false, "Please confirm your new password")
        }

        if (confirmPassword != newPassword) {
            return ValidationResult(false, "Passwords do not match")
        }

        return ValidationResult(true, "Password changed successfully")
    }

    data class ValidationResult(val isValid: Boolean, val message: String)
}
