package com.example.kray

import android.app.Application
import com.example.kray.moduls.mainModule
import com.example.kray.data.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KrayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KrayApplication)
            modules(listOf(mainModule, retrofitModule))
        }
    }
}