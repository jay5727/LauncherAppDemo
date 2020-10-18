package com.jay.launcherappdemo.base

import android.app.Application

/**
 * NOTE : Make sure to register BaseApplication in [AndroidManifest.xml]
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}