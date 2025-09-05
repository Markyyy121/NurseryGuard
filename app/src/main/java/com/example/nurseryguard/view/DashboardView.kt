package com.example.nurseryguard.view

interface DashboardView {
    fun showLoading()
    fun hideLoading()
    fun updateTemperature(temp: String)
    fun updateHumidity(humidity: String)
    fun updateEnvironmentStatus(status: String)
    fun showError(message: String)
}
