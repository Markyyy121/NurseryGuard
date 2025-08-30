//// presenter/MonitorPresenter.kt
//package com.example.nurseryguard.presenter
//
//import com.example.nurseryguard.contract.MonitorContract
//import com.example.nurseryguard.model.MonitorModel
//
//class MonitorPresenter(private val view: MonitorContract.View) : MonitorContract.Presenter {
//    private val model: MonitorContract.Model = MonitorModel()
//
//    override fun fetchEnvironmentData() {
//        model.fetchEnvironmentData({ temp, humidity ->
//            view.showTemperature(temp)
//            view.showHumidity(humidity)
//
//            if (temp in 22..26 && humidity in 40..70) {
//                view.showStatus("Environment Status: SAFE", true)
//            } else {
//                view.showStatus("Environment Status: UNSAFE", false)
//            }
//        }, { error ->
//            view.showStatus("Error: $error", false)
//        })
//    }
//}
