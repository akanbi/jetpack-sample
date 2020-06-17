package com.akanbi.jetpacksample.application

import android.app.Application
import com.akanbi.jetpacksample.infrastructure.di.moduleByApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserApplication)
            modules(moduleByApp)
        }
    }

}