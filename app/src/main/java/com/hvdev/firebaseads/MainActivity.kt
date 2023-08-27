package com.hvdev.firebaseads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var firebase: FirebaseFirestore
    private lateinit var adsManager: AdsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adsManager = AdsManager(this)
        loadBannerAds()

    }

    private fun loadBannerAds() {

        var bannerStatus = true

        this.adsManager.bannerStatusFlow.asLiveData().observe(this) {
            bannerStatus = it
            if (bannerStatus) {
                MobileAds.initialize(this) {}

                mAdView = findViewById(R.id.adView)
                val adRequest = AdRequest.Builder().build()
                mAdView.loadAd(adRequest)

                mAdView.adListener = object : AdListener() {
                    override fun onAdClicked() {
                        // Code to be executed when the user clicks on an ad.
                    }

                    override fun onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }

                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        // Code to be executed when an ad request fails.
                    }

                    override fun onAdImpression() {
                        // Code to be executed when an impression is recorded
                        // for an ad.
                    }

                    override fun onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                    }

                    override fun onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                }
            }
        }
    }
}