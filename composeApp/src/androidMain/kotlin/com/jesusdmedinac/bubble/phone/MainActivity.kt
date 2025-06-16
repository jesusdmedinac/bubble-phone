package com.jesusdmedinac.bubble.phone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            OpenSystemAppCompositionProvider {
                BubblePhoneApp(
                    koinAppDeclaration = {
                        androidContext(this@MainActivity)
                    }
                )
            }
        }
    }

    @Composable
    private fun OpenSystemAppCompositionProvider(
        content: @Composable () -> Unit,
    ) {
        val openSystemApp = remember {
            object : OpenSystemApp {
                override fun invoke(systemApp: SystemApp) {
                    val intent = when (systemApp) {
                        SystemApp.MESSAGES -> Intent(Intent.ACTION_MAIN).apply {
                            addCategory(Intent.CATEGORY_APP_MESSAGING)
                        }
                        SystemApp.PHOTOS -> Intent(Intent.ACTION_VIEW).apply {
                            addCategory(Intent.CATEGORY_APP_GALLERY)
                        }
                        SystemApp.BROWSER -> Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                        SystemApp.NOTES -> {
                            this@MainActivity.packageManager.getLaunchIntentForPackage("com.google.android.keep")
                        }
                        SystemApp.SETTINGS -> Intent(Settings.ACTION_SETTINGS)
                    }

                    intent?.let {
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        this@MainActivity.startActivity(it)
                    }
                }
            }
        }
        CompositionLocalProvider(LocalOpenSystemApp provides openSystemApp) {
            content()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    BubblePhoneApp()
}