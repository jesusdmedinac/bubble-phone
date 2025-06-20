package com.jesusdmedinac.bubble.phone

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

class IOSOpenSystemApp : OpenSystemApp {
    override fun invoke(systemApp: SystemApp) {
        val scheme = when (systemApp) {
            SystemApp.MESSAGES -> "sms:1234567890"
            SystemApp.BROWSER -> "https://www.google.com"
            SystemApp.SETTINGS -> UIApplicationOpenSettingsURLString
            SystemApp.PHOTOS -> null
        }

        scheme?.let {
            val url = NSURL(string = it)
            if (UIApplication.sharedApplication.canOpenURL(url)) {
                UIApplication.sharedApplication.openURL(url)
            }
        }
    }
}