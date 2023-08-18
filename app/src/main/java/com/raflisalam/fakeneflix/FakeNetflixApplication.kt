package com.raflisalam.fakeneflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FakeNetflixApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}