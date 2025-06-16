package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

fun MainViewController() = ComposeUIViewController {
    OpenSystemAppCompositionProvider {
        BubblePhoneApp()
    }
}

@Composable
private fun OpenSystemAppCompositionProvider(
    content: @Composable () -> Unit,
) {
    val openSystemApp = remember {
        object : OpenSystemApp {
            override fun invoke(systemApp: SystemApp) {
                val scheme = when (systemApp) {
                    SystemApp.MESSAGES -> "sms:"
                    SystemApp.BROWSER -> "https://www.google.com"
                    SystemApp.SETTINGS -> UIApplicationOpenSettingsURLString
                    SystemApp.PHOTOS,
                    SystemApp.NOTES -> null
                }

                scheme?.let {
                    val url = NSURL(string = it)
                    if (UIApplication.sharedApplication.canOpenURL(url)) {
                        UIApplication.sharedApplication.openURL(url)
                    }
                }
            }
        }
    }
    CompositionLocalProvider(LocalOpenSystemApp provides openSystemApp) {
        content()
    }
}