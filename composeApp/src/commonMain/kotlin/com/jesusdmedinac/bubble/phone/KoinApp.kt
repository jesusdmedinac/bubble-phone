package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
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
            koinModules()
        },
    ) {
        content()
    }
}

private fun KoinApplication.koinModules() {
    modules(
        appModule(),
    )
}

private fun appModule() = module {

}