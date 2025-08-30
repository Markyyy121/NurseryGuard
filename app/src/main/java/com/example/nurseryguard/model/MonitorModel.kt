//package com.example.nurseryguard.model
//
//import com.example.nurseryguard.contract.MonitorContract
//import com.google.firebase.database.*
//
//class MonitorModel : MonitorContract.Model {
//    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("roomData")
//
//    override fun fetchEnvironmentData(callback: (Int, Int) -> Unit, onError: (String) -> Unit) {
//        dbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val temp = snapshot.child("temperature").getValue(Int::class.java) ?: 0
//                val humidity = snapshot.child("humidity").getValue(Int::class.java) ?: 0
//                callback(temp, humidity)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                onError(error.message)
//            }
//        })
//    }
//}
