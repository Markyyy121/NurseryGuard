package com.example.nurseryguard.view

import android.os.Handler
import android.os.Looper

class DashboardModel {

    // Simulate fetching IoT data
    fun fetchEnvironmentData(callback: (Boolean, String, String, String) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            val temperature = "24°C"
            val humidity = "65%"
            val status = "Environment Status: SAFE\nTemperature and humidity levels are optimal for baby"
            callback(true, temperature, humidity, status)
        }, 2000) // 2s delay to simulate API/IoT fetch
    }
}
