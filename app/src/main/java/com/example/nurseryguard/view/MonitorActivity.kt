package com.example.nurseryguard.view

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nurseryguard.R
import com.example.nurseryguard.contract.MonitorContract
//import com.example.nurseryguard.presenter.MonitorPresenter

//class MonitorActivity : AppCompatActivity(), MonitorContract.View {
//    private lateinit var presenter: MonitorPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_monitor)
//
//        presenter = MonitorPresenter(this)
//        presenter.fetchEnvironmentData()
//    }
//
//    override fun showTemperature(temp: Int) {
//        findViewById<TextView>(R.id.tvTemperature).text = "$temp°C"
//    }
//
//    override fun showHumidity(humidity: Int) {
//        findViewById<TextView>(R.id.tvHumidity).text = "$humidity%"
//    }
//
//    override fun showStatus(message: String, isSafe: Boolean) {
//        val tvStatus = findViewById<TextView>(R.id.tvEnvironmentStatus)
//        tvStatus.text = message
//        tvStatus.setTextColor(if (isSafe) Color.GREEN else Color.RED)
//    }
//}

class MonitorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)

        // Dummy values to test UI
        showTemperature(28)
        showHumidity(65)
        showStatus("Safe Environment", true)
    }

    private fun showTemperature(temp: Int) {
        findViewById<TextView>(R.id.tvTemperature).text = "$temp°C"
    }

    private fun showHumidity(humidity: Int) {
        findViewById<TextView>(R.id.tvHumidity).text = "$humidity%"
    }

    private fun showStatus(message: String, isSafe: Boolean) {
        val tvStatus = findViewById<TextView>(R.id.tvEnvironmentStatus)
        tvStatus.text = message
        tvStatus.setTextColor(if (isSafe) Color.GREEN else Color.RED)
    }
}
