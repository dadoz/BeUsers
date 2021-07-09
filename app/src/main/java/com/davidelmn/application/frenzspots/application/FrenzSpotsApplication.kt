package com.davidelmn.application.frenzspots.application

import android.app.Application
import com.davidelmn.application.frenzspots.BuildConfig
import com.google.firebase.FirebaseApp
import timber.log.Timber
import timber.log.Timber.DebugTree


class FrenzSpotsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        takeIf { BuildConfig.DEBUG }
            .let {
                Timber.plant(DebugTree())
            }
        Timber.d("blllallalalllallla")
        Timber.e("eeeeeeeee")
    }
}