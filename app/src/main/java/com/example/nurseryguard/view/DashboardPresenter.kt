package com.example.nurseryguard.view

class DashboardPresenter(private val model: DashboardModel) {

    private var view: DashboardView? = null

    fun attachView(view: DashboardView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun loadDashboardData() {
        view?.showLoading()
        model.fetchEnvironmentData { success, temp, humidity, status ->
            view?.hideLoading()
            if (success) {
                view?.updateTemperature(temp)
                view?.updateHumidity(humidity)
                view?.updateEnvironmentStatus(status)
            } else {
                view?.showError("Failed to fetch environment data")
            }
        }
    }
}
