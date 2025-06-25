package com.jesusdmedinac.bubble.phone.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bubblephone.composeapp.generated.resources.Res
import bubblephone.composeapp.generated.resources.generic_app_name_browser
import bubblephone.composeapp.generated.resources.generic_app_name_messages
import bubblephone.composeapp.generated.resources.generic_app_name_photos
import bubblephone.composeapp.generated.resources.generic_app_name_settings
import com.jesusdmedinac.bubble.phone.LocalOpenSystemApp
import com.jesusdmedinac.bubble.phone.SystemApp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen() {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            items(
                SystemApp
                    .entries
                    .map { systemApp -> systemApp.toAppAvailable() }
            ) { appAvailable ->
                val openSystemApp = LocalOpenSystemApp.current
                TextButton(
                    onClick = {
                        openSystemApp(appAvailable.systemApp)
                    }
                ) {
                    Text(
                        text = stringResource(appAvailable.labelRes),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

fun SystemApp.toAppAvailable(): AppAvailable {
    val labelRes = when (this) {
        SystemApp.MESSAGES -> Res.string.generic_app_name_messages
        SystemApp.PHOTOS -> Res.string.generic_app_name_photos
        SystemApp.BROWSER -> Res.string.generic_app_name_browser
        SystemApp.SETTINGS -> Res.string.generic_app_name_settings
    }
    return AppAvailable(
        labelRes = labelRes,
        systemApp = this
    )
}

data class AppAvailable(
    val labelRes: StringResource,
    val systemApp: SystemApp
)