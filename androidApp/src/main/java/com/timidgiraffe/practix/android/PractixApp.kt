package com.timidgiraffe.practix.android

import DatabaseDriverFactory
import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.compose.getKoin
import org.koin.core.context.startKoin
import platformModule


class PractixApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PractixApp)
            modules(appModule)
        }
//        initKoin()
    }
}