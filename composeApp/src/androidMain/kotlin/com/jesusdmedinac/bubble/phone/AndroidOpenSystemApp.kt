package com.jesusdmedinac.bubble.phone

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class AndroidOpenSystemApp(
    private val context: Context
) : OpenSystemApp {
    override fun invoke(systemApp: SystemApp) {
        val intent = when (systemApp) {
            SystemApp.MESSAGES -> Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_APP_MESSAGING)
            }
            SystemApp.PHOTOS -> Intent(Intent.ACTION_VIEW).apply {
                addCategory(Intent.CATEGORY_APP_GALLERY)
            }
            SystemApp.BROWSER -> Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            SystemApp.SETTINGS -> Intent(Settings.ACTION_SETTINGS)
        }

        intent.let {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(it)
        }
    }
}