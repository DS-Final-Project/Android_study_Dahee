package com.example.prac_android.step4

import android.app.Application
import timber.log.Timber

class ClickerApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}