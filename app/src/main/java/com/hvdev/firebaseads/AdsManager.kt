package com.hvdev.firebaseads

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.map

class AdsManager(context : Context) {

    private val dataStore = context.createDataStore(name = "ads_prefs")

    companion object {
        val BANNER_ID = preferencesKey<String>("B_ID")
        val BANNER_STATUS = preferencesKey<Boolean>("B_STATUS")

        val INTERSTITIAL_ID = preferencesKey<String>("I_ID")
        val INTERSTITIAL_STATUS = preferencesKey<Boolean>("I_STATUS")

        val NATIVE_ID = preferencesKey<String>("N_ID")
        val NATIVE_STATUS = preferencesKey<Boolean>("N_STATUS")
    }

    suspend fun storeIDS(bId: String, bStatus: Boolean, iId: String, iStatus: Boolean, nId: String, nStatus: Boolean) {
        dataStore.edit {
            it[BANNER_ID] = bId
            it[BANNER_STATUS] = bStatus
            it[INTERSTITIAL_ID] = iId
            it[INTERSTITIAL_STATUS] = iStatus
            it[NATIVE_ID] = nId
            it[NATIVE_STATUS] = nStatus
        }
    }

    val bannerIdFlow = dataStore.data.map {
        it[BANNER_ID] ?: ""
    }

    val bannerStatusFlow = dataStore.data.map {
        it[BANNER_STATUS] ?: true
    }

    val interstitialIdFlow = dataStore.data.map {
        it[BANNER_ID] ?: ""
    }

    val interstitialStatusFlow = dataStore.data.map {
        it[BANNER_STATUS] ?: true
    }

    val nativeIdFlow = dataStore.data.map {
        it[BANNER_ID] ?: ""
    }

    val nativeStatusFlow = dataStore.data.map {
        it[BANNER_STATUS] ?: true
    }
}