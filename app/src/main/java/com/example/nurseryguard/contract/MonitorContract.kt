// contract/MonitorContract.kt
package com.example.nurseryguard.contract

interface MonitorContract {
    interface View {
        fun showTemperature(temp: Int)
        fun showHumidity(humidity: Int)
        fun showStatus(message: String, isSafe: Boolean)
    }

    interface Presenter {
        fun fetchEnvironmentData()
    }

    interface Model {
        fun fetchEnvironmentData(callback: (Int, Int) -> Unit, onError: (String) -> Unit)
    }
}
