package com.davidelmn.application.frenzspots.application

import android.app.Application
import com.davidelmn.application.frenzspots.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree


class FrenzSpotsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        takeIf { BuildConfig.DEBUG }
            .let {
                Timber.plant(DebugTree())
            }
    }
}