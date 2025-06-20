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
import com.jesusdmedinac.bubble.phone.di.koinAndroidModules
import org.koin.android.ext.koin.androidContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            OpenSystemAppCompositionProvider {
                BubblePhoneApp(
                    koinAppDeclaration = {
                        androidContext(this@MainActivity)
                        koinAndroidModules()
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
            AndroidOpenSystemApp(this@MainActivity)
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