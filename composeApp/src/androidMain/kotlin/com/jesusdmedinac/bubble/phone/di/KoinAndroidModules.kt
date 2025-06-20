package com.jesusdmedinac.bubble.phone.di

import com.jesusdmedinac.bubble.phone.data.store.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.koinAndroidModules() {
    modules(
        frameworkModule()
    )
}

fun frameworkModule() = module {
    single { createDataStore(androidContext()) }
}