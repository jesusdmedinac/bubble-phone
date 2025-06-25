package com.jesusdmedinac.bubble.phone.data.framework

interface GetAppsInstalledOnPlatform {
    suspend operator fun invoke(): List<String>
}