package com.rumosoft.twittermirroring.presentation

import android.app.Application
import com.rumosoft.twittermirroring.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initialiseTimber()
    }

    private fun initialiseTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
