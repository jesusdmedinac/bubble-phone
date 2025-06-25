package com.jesusdmedinac.bubble.phone

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.ComposeUIViewController
import com.jesusdmedinac.bubble.phone.di.koinFrameworkModules
import platform.UIKit.UIView
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    val view = LocalView.current
    val window = (view as? UIView)?.window
    
    // Configurar el color de fondo de la ventana principal
    window?.backgroundColor = UIColor(Color.Black)
    
    OpenSystemAppCompositionProvider {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            color = MaterialTheme.colorScheme.background
        ) {
            BubblePhoneApp(
                koinAppDeclaration = {
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
        IOSOpenSystemApp()
    }
    CompositionLocalProvider(LocalOpenSystemApp provides openSystemApp) {
        content()
    }
}

// Extensión para convertir Color de Compose a UIColor
expect fun Color.toUIColor(): Any

// Implementación para iOS
actual fun Color.toUIColor(): Any = when (this) {
    Color.Black -> UIColor.blackColor()
    Color.White -> UIColor.whiteColor()
    Color.Red -> UIColor.redColor()
    Color.Green -> UIColor.greenColor()
    Color.Blue -> UIColor.blueColor()
    Color.Transparent -> UIColor.clear
    else -> UIColor(red = this.red, green = this.green, blue = this.blue, alpha = this.alpha)
}

// Extensión para acceder a los componentes de color
extension Color {
    val red: Float get() = (this.value shr 16 and 0xFF) / 255f
    val green: Float get() = (this.value shr 8 and 0xFF) / 255f
    val blue: Float get() = (this.value and 0xFF) / 255f
    val alpha: Float get() = (this.value shr 24 and 0xFF) / 255f
}