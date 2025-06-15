package com.jesusdmedinac.bubble.phone

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import bubblephone.composeapp.generated.resources.Res
import bubblephone.composeapp.generated.resources.compose_multiplatform
import bubblephone.composeapp.generated.resources.generic_app_name_browser
import bubblephone.composeapp.generated.resources.generic_app_name_messages
import bubblephone.composeapp.generated.resources.generic_app_name_notes
import bubblephone.composeapp.generated.resources.generic_app_name_photos
import bubblephone.composeapp.generated.resources.generic_app_name_settings
import org.jetbrains.compose.resources.stringResource

@Composable
@Preview
fun BubblePhoneApp() {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color.White,
            onPrimary = Color.Black,
            primaryContainer = Color.White,
            onPrimaryContainer = Color.Black,
            inversePrimary = Color.Black,
            secondary = Color.White,
            onSecondary = Color.Black,
            secondaryContainer = Color.White,
            onSecondaryContainer = Color.Black,
            tertiary = Color.White,
            onTertiary = Color.Black,
            tertiaryContainer = Color.White,
            onTertiaryContainer = Color.Black,
            background = Color.Black,
            onBackground = Color.White,
            surface = Color.Black,
            onSurface = Color.White,
            surfaceVariant = Color.Black,
            onSurfaceVariant = Color.White,
            surfaceTint = Color.White,
            inverseSurface = Color.White,
            inverseOnSurface = Color.Black,
            error = Color.White,
            onError = Color.Black,
            errorContainer = Color.White,
            onErrorContainer = Color.Black,
        ),
    ) {
        Scaffold {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                items(
                    listOf(
                        Res.string.generic_app_name_messages,
                        Res.string.generic_app_name_photos,
                        Res.string.generic_app_name_browser,
                        Res.string.generic_app_name_notes,
                        Res.string.generic_app_name_settings
                    )
                ) { labelRes ->
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(labelRes),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}