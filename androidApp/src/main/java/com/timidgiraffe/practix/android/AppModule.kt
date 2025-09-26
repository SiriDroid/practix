package com.timidgiraffe.practix.android

import DatabaseDriverFactory
import app.cash.sqldelight.db.SqlDriver
import com.timidgiraffe.practix.AppDatabase
import com.timidgiraffe.practix.data.PracticeRepositoryImpl
import com.timidgiraffe.practix.domain.PracticeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { DatabaseDriverFactory(androidContext()) }
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() }
    single { AppDatabase(get()) }
    single<PracticeRepository> { PracticeRepositoryImpl(get()) }
}