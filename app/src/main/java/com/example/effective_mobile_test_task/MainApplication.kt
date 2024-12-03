package com.example.effective_mobile_test_task

import android.app.Application
import com.example.effective_mobile_test_task.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(koinModule)
        }
    }
}