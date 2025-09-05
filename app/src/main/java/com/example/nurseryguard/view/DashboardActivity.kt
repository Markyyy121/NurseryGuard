package com.example.nurseryguard.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nurseryguard.R
import com.google.android.material.appbar.MaterialToolbar
class DashboardActivity : AppCompatActivity(), DashboardView {

    private lateinit var presenter: DashboardPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var tvTemperature: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvEnvironmentStatus: TextView
    private lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Bind views
        progressBar = ProgressBar(this).apply { visibility = View.GONE }
        tvTemperature = findViewById(R.id.tvTemperature)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvEnvironmentStatus = findViewById(R.id.tvEnvironmentStatus)
        topAppBar = findViewById(R.id.topAppBar)

        // Setup presenter
        presenter = DashboardPresenter(DashboardModel())
        presenter.attachView(this)

        // Load data
        presenter.loadDashboardData()


        topAppBar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    // MVP View methods
    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun updateTemperature(temp: String) {
        tvTemperature.text = temp
    }

    override fun updateHumidity(humidity: String) {
        tvHumidity.text = humidity
    }

    override fun updateEnvironmentStatus(status: String) {
        tvEnvironmentStatus.text = status
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
