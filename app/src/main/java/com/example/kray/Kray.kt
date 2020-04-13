package com.example.kray

import android.app.Application
import com.example.kray.moduls.mainModule
import com.example.kray.moduls.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Kray : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Kray)
            modules(listOf(mainModule, retrofitModule))
        }
    }
}