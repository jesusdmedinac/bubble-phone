package com.jesusdmedinac.bubble.phone.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jesusdmedinac.bubble.phone.data.framework.GetAppsInstalledOnAndroid
import com.jesusdmedinac.bubble.phone.data.framework.GetAppsInstalledOnPlatform
import com.jesusdmedinac.bubble.phone.data.store.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun frameworkModule(): Module = module {
    single<DataStore<Preferences>> { createDataStore(androidContext()) }
    single<GetAppsInstalledOnPlatform> { GetAppsInstalledOnAndroid(androidContext()) }
}