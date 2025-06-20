package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
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
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            homeScreen(navController)
            onboardingScreen(navController)
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