package com.jesusdmedinac.bubble.phone.data.framework

import platform.Foundation.NSUserDefaults
import platform.UIKit.UIApplication
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import platform.Foundation.NSURL

@Serializable
data class UserAppPreference(
    val name: String,
    val bundleID: String? = null,
    val urlScheme: String? = null,
    var isVisibleInMyApp: Boolean = true
)

private const val PREFS_KEY = "user_app_preferences"

private val defaultApps = listOf(
    UserAppPreference(
        name = "WhatsApp",
        bundleID = "net.whatsapp.WhatsApp",
        urlScheme = "whatsapp://",
        isVisibleInMyApp = true
    ),
    UserAppPreference(
        name = "Instagram",
        bundleID = "com.burbn.instagram",
        urlScheme = "instagram://",
        isVisibleInMyApp = true
    ),
    UserAppPreference(
        name = "Messages",
        bundleID = "com.apple.MobileSMS",
        urlScheme = "sms://",
        isVisibleInMyApp = true
    ),
    UserAppPreference(
        name = "Phone",
        bundleID = "com.apple.mobilephone",
        urlScheme = "tel://",
        isVisibleInMyApp = true
    ),
    UserAppPreference(
        name = "Mail",
        bundleID = "com.apple.mobilemail",
        urlScheme = "mailto:",
        isVisibleInMyApp = true
    )
)

private val json = Json { ignoreUnknownKeys = true }

/**
 * On iOS, due to Apple's strict sandboxing and privacy restrictions,
 * we can't get a list of all installed apps like on Android.
 * 
 * This implementation returns a predefined list of common apps that the user
 * might have installed, checking which URL schemes are available.
 */
class GetAppsInstalledOnIOS : GetAppsInstalledOnPlatform {
    override suspend fun invoke(): List<String> {
        val availableApps = getAvailableApps()
        return availableApps
            .filter { it.isVisibleInMyApp }
            .map { it.name }
    }
}

/**
 * Gets the list of available apps with their preferences
 */
fun getAvailableApps(): List<UserAppPreference> {
    val savedPrefs = loadPreferences()
    
    // Merge with default apps, preserving user preferences
    return defaultApps.map { defaultApp ->
        savedPrefs.find { it.name == defaultApp.name } ?: defaultApp
    }
}

/**
 * Saves the user's app preferences
 */
fun saveAppPreferences(apps: List<UserAppPreference>) {
    val jsonString = json.encodeToString(apps)
    NSUserDefaults.standardUserDefaults.setObject(jsonString, PREFS_KEY)
    NSUserDefaults.standardUserDefaults.synchronize()
}

/**
 * Toggles the visibility of an app in the list
 */
fun toggleAppVisibility(appName: String) {
    val apps = getAvailableApps().map {
        if (it.name == appName) it.copy(isVisibleInMyApp = !it.isVisibleInMyApp) else it
    }
    saveAppPreferences(apps)
}

/**
 * Loads saved preferences from UserDefaults
 */
private fun loadPreferences(): List<UserAppPreference> {
    val jsonString = NSUserDefaults.standardUserDefaults.stringForKey(PREFS_KEY) ?: return emptyList()
    return try {
        json.decodeFromString(jsonString)
    } catch (e: Exception) {
        emptyList()
    }
}

/**
 * Checks if an app can be opened with its URL scheme
 */
fun canOpenApp(app: UserAppPreference): Boolean {
    return app.urlScheme?.let { scheme ->
        val url = scheme.ifEmpty { return@let false }
        UIApplication.sharedApplication.canOpenURL(NSURL.URLWithString(url)!!)
    } ?: false
}

/**
 * Launches an app using its URL scheme
 * @return true if the app was launched successfully, false otherwise
 */
fun launchApp(app: UserAppPreference): Boolean {
    if (!app.isVisibleInMyApp) return false
    
    return app.urlScheme?.let { scheme ->
        val url = NSURL.URLWithString(scheme) ?: return@let false
        val options = mapOf<Any?, Any?>()
        
        UIApplication.sharedApplication.openURL(
            url = url,
            options = options,
            completionHandler = null
        )
        true
    } ?: false
}