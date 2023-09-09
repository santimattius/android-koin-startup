package com.santimattius.template

import android.app.Application
import com.santimattius.template.di.appModule
import com.santimattius.template.di.dataModule
import com.santimattius.template.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.lazyModules

@OptIn(KoinExperimentalAPI::class)
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
            lazyModules(uiModule, dataModule)
        }
    }
}