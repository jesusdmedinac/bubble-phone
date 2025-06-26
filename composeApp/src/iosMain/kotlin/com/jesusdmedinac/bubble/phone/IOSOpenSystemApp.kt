package com.jesusdmedinac.bubble.phone

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

class IOSOpenSystemApp : OpenSystemApp {
    override fun invoke(systemApp: SystemApp) {
        val scheme = when (systemApp) {
            SystemApp.MESSAGES -> "sms:"
            SystemApp.BROWSER -> "https://www.google.com"
            SystemApp.SETTINGS -> UIApplicationOpenSettingsURLString
            SystemApp.PHOTOS -> "photos-redirect://"
        }

        val url = NSURL(string = scheme)
        if (UIApplication.sharedApplication.canOpenURL(url)) {
            dispatch_async(dispatch_get_main_queue()) {
                val options = mapOf<Any?, Any?>()
                UIApplication.sharedApplication.openURL(url, options) { success ->
                    if (!success) {
                        println("No se pudo abrir la aplicación de mensajes. URL: $url")
                        val fallbackUrl = NSURL(string = "sms:0")
                        UIApplication.sharedApplication.openURL(
                            fallbackUrl,
                            options
                        ) { fallbackSuccess ->
                            if (!fallbackSuccess) {
                                println("También falló el intento con formato alternativo")
                            }
                        }
                    }
                }
            }
        } else {
            println("No se puede manejar el esquema: $scheme")
        }
    }
}