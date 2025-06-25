package com.jesusdmedinac.bubble.phone.data.framework

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class GetAppsInstalledOnAndroid(
    private val context: Context
) : GetAppsInstalledOnPlatform {
    override suspend fun invoke(): List<String> {
        val pm = context.packageManager
        return pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { appInfo ->
                // Filter out system apps if needed
                (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0
            }
            .map { it.loadLabel(pm).toString() }
            .sorted()
    }
}