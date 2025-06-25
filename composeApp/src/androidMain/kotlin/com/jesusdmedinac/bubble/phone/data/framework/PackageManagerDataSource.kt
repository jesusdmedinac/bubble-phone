package com.jesusdmedinac.bubble.phone.data.framework

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import com.jesusdmedinac.bubble.phone.data.model.InstalledAndroidApp

interface AndroidAppDataSource {
    fun listInstalledApps(): List<InstalledAndroidApp>
}

class AndroidAppDataSourceImpl(
    private val context: Context
) : AndroidAppDataSource {
    override fun listInstalledApps(): List<InstalledAndroidApp> {
        val pm: PackageManager = context.packageManager
        val appInfos: List<ApplicationInfo> =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(0L))
            } else {
                pm.getInstalledApplications(0)
            }

        val installedAndroidApps = mutableListOf<InstalledAndroidApp>()
        for (appInfo in appInfos) {
            val appName = pm.getApplicationLabel(appInfo).toString()
            val appIcon = pm.getApplicationIcon(appInfo)
            // Process the appName and appIcon as needed
            installedAndroidApps.add(InstalledAndroidApp(appName, appIcon))
        }
        return installedAndroidApps
    }
}