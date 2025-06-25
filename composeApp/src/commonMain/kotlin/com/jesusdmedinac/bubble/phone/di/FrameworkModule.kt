package com.jesusdmedinac.bubble.phone.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

expect fun frameworkModule(): Module

fun KoinApplication.koinFrameworkModules() {
    modules(
        frameworkModule()
    )
}