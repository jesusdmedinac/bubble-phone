package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jesusdmedinac.bubble.phone.data.store.BubblePhoneDataStore
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
fun KoinApp(
    koinAppDeclaration: KoinAppDeclaration = {},
    content: @Composable () -> Unit,
) {
    KoinApplication(
        application = {
            koinAppDeclaration()
            koinAppModules()
        },
    ) {
        content()
    }
}

private fun KoinApplication.koinAppModules() {
    modules(
        koinDataModule(),
    )
}

private fun koinDataModule() = module {
    single { BubblePhoneDataStore(get<DataStore<Preferences>>()) }
}