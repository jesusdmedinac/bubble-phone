package com.jesusdmedinac.bubble.phone.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import bubblephone.composeapp.generated.resources.Res
import bubblephone.composeapp.generated.resources.onboarding_get_started
import bubblephone.composeapp.generated.resources.onboarding_get_started_subtitle
import bubblephone.composeapp.generated.resources.onboarding_get_started_title
import bubblephone.composeapp.generated.resources.screen_shot_of_android
import bubblephone.composeapp.generated.resources.ss_android_es
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingScreen() {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier.height(64.dp)
            )
            Image(
                painter = painterResource(Res.drawable.ss_android_es),
                contentDescription = stringResource(Res.string.screen_shot_of_android),
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = stringResource(Res.string.onboarding_get_started_subtitle),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(Res.string.onboarding_get_started_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp, vertical = 20.dp)
                    .height(136.dp),
                shape = MaterialTheme.shapes.extraLarge,
            ) {
                Text(
                    text = stringResource(Res.string.onboarding_get_started),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}