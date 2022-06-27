package com.app.rocket

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @HiltAndroidApp triggers Hilt's code generation, including a base class for your
 * application that serves as the application-level dependency container.
 */

@HiltAndroidApp
class RocketApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}