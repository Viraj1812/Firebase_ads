package com.hvdev.firebaseads

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirebaseAdsApplicationClass: Application(), LifecycleObserver {

    lateinit var adsManager: AdsManager
    private lateinit var firebase: FirebaseFirestore

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        firebase = FirebaseFirestore.getInstance()
        firebase.collection("Ads").document("o16nT04B4kGO49Y55y74").addSnapshotListener(
            EventListener<DocumentSnapshot?> { value, error ->
                if (value != null) {
                    GlobalScope.launch{
                        AdsManager(this@FirebaseAdsApplicationClass).storeIDS(
                            value["banner_g"].toString(),
                            value["banner_status"] as Boolean,
                            value["interstitial"].toString(),
                            value["interstitial_status"] as Boolean,
                            value["native_g"].toString(),
                            value["native_status"] as Boolean
                        )
                    }
                }
                else{
                    Log.d("Error", "$error")
                }
            }
        )
    }
}