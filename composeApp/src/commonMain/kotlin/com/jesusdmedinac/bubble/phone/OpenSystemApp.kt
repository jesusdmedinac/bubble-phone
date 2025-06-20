package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

enum class SystemApp {
    MESSAGES, PHOTOS, BROWSER, SETTINGS
}

interface OpenSystemApp {
    operator fun invoke(systemApp: SystemApp)

    companion object : OpenSystemApp {
        override fun invoke(systemApp: SystemApp) {
            TODO("Not yet implemented")
        }
    }
}

val LocalOpenSystemApp: ProvidableCompositionLocal<OpenSystemApp> =
    staticCompositionLocalOf { OpenSystemApp }