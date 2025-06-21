package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.jesusdmedinac.bubble.phone.data.store.BubblePhoneDataStore
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun BubblePhoneStartDestination(
    content: @Composable (Page) -> Unit
) {
    val bubblePhoneDataStore = koinInject<BubblePhoneDataStore>()

    // TODO: Set as true to show onboarding during development. Remove before production.
    var isFirstTime: Boolean? by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        return@LaunchedEffect
        coroutineScope.launch {
            isFirstTime = bubblePhoneDataStore.isFirstTime()

            if (isFirstTime == true) {
                bubblePhoneDataStore.setIsFirstTime(false)
            }
        }
    }

    isFirstTime?.let {
        content(
            when (it) {
                true -> Onboarding
                false -> Home
            }
        )
    }
}