package com.jesusdmedinac.bubble.phone

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun BubblePhoneApp(
    koinAppDeclaration: KoinAppDeclaration = {},
) {
    BubblePhoneTheme {
        KoinApp(
            koinAppDeclaration = koinAppDeclaration,
        ) {
            BubblePhoneNavHost()
        }
    }
}