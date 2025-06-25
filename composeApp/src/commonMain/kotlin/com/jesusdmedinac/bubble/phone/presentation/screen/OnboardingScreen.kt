package com.jesusdmedinac.bubble.phone.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import bubblephone.composeapp.generated.resources.Res
import bubblephone.composeapp.generated.resources.onboarding_get_started
import bubblephone.composeapp.generated.resources.onboarding_get_started_subtitle
import bubblephone.composeapp.generated.resources.onboarding_get_started_title
import bubblephone.composeapp.generated.resources.screen_shot_of_android
import bubblephone.composeapp.generated.resources.ss_android_es
import com.jesusdmedinac.bubble.phone.presentation.viewmodel.OnboardingViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bubblephone.composeapp.generated.resources.onboarding_pick_essential_apps_description
import bubblephone.composeapp.generated.resources.onboarding_pick_essential_apps_search_placeholder
import bubblephone.composeapp.generated.resources.onboarding_pick_essential_apps_title
import com.jesusdmedinac.bubble.phone.presentation.viewmodel.OnboardingStep

@Composable
fun OnboardingScreen() {
    val viewModel: OnboardingViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(state) {
        state.currentStep?.let { navController.navigate(it) }
    }

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
            startDestination = OnboardingStep.Welcome,
            modifier = Modifier.weight(1f)
        ) {
            composable<OnboardingStep.Welcome> { WelcomeScreen(viewModel) }

            composable<OnboardingStep.PickEssentialApps> { PickEssentialAppsScreen(viewModel) }
        }
        Spacer(
            modifier = Modifier
                .windowInsetsBottomHeight(
                    WindowInsets.navigationBars
                )
        )
    }
}


@Composable
private fun WelcomeScreen(
    onboardingViewModel: OnboardingViewModel = koinViewModel(),
) {
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
                onClick = {
                    onboardingViewModel.onGetStartedClick()
                },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PickEssentialAppsScreen(
    onboardingViewModel: OnboardingViewModel = koinViewModel(),
) {
    val state by onboardingViewModel.state.collectAsState()
    val searchTerm = state.searchTerm
    val installedApps = state.installedApps
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(Res.string.onboarding_pick_essential_apps_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(Res.string.onboarding_pick_essential_apps_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
            OutlinedTextField(
                value = searchTerm,
                onValueChange = { searchTermValue ->
                    onboardingViewModel.onSearchTermChange(searchTermValue)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = {
                    Text(text = stringResource(Res.string.onboarding_pick_essential_apps_search_placeholder))
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                items(installedApps) { appName ->
                    Text(
                        text = appName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}