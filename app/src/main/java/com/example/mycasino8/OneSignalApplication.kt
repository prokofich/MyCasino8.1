package com.example.mycasino8

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.onesignal.OneSignal

class OneSignalApplication:Application() {

    val ONESIGNAL_APP_ID = "97ae74ec-52d3-4db7-a915-6076be8d3438"

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

    }

}