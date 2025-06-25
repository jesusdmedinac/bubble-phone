package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.jesusdmedinac.bubble.phone.di.koinFrameworkModules

fun MainViewController() = ComposeUIViewController {
    OpenSystemAppCompositionProvider {
        BubblePhoneApp(
            koinAppDeclaration = {
                koinFrameworkModules()
            }
        )
    }
}

@Composable
private fun OpenSystemAppCompositionProvider(
    content: @Composable () -> Unit,
) {
    val openSystemApp = remember {
        IOSOpenSystemApp()
    }
    CompositionLocalProvider(LocalOpenSystemApp provides openSystemApp) {
        content()
    }
}
