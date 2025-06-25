package com.jesusdmedinac.bubble.phone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.jesusdmedinac.bubble.phone.di.koinFrameworkModules
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
                        koinFrameworkModules()
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