package com.smartHealth.recipefinder

import android.app.Application
import com.smartHealth.recipefinder.di.DIManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DIManager.initialize(this)
    }
}