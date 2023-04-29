package com.santimattius.template

import android.app.Application
import com.santimattius.template.di.appModule
import com.santimattius.template.di.dataModule
import com.santimattius.template.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule + dataModule + domainModule)
        }
    }
}