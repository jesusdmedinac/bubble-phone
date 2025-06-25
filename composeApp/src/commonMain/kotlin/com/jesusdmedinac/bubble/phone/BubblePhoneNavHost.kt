package com.jesusdmedinac.bubble.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesusdmedinac.bubble.phone.presentation.screen.HomeScreen
import com.jesusdmedinac.bubble.phone.presentation.screen.OnboardingScreen
import kotlinx.serialization.Serializable

sealed interface Page

@Serializable
data object Home : Page

@Serializable
data object Onboarding : Page

@Composable
fun BubblePhoneNavHost() {
    BubblePhoneStartDestination { startDestination ->
        val navController = rememberNavController()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(
                modifier = Modifier
                    .windowInsetsTopHeight(
                        WindowInsets.statusBars
                    )
            )
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.weight(1f)
            ) {
                homeScreen(navController)
                onboardingScreen(navController)
            }
            Spacer(
                modifier = Modifier
                    .windowInsetsBottomHeight(
                        WindowInsets.navigationBars
                    )
            )
        }
    }
}

fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable<Home> {
        HomeScreen()
    }
}

fun NavGraphBuilder.onboardingScreen(navController: NavHostController) {
    composable<Onboarding> {
        OnboardingScreen()
    }
}