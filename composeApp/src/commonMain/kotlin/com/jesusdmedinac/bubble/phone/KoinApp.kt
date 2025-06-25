package com.jesusdmedinac.bubble.phone

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jesusdmedinac.bubble.phone.data.repository.OnboardingRepositoryImpl
import com.jesusdmedinac.bubble.phone.data.store.BubblePhoneDataStore
import com.jesusdmedinac.bubble.phone.domain.repository.OnboardingRepository
import com.jesusdmedinac.bubble.phone.presentation.viewmodel.OnboardingViewModel
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
fun KoinApp(
    koinAppDeclaration: KoinAppDeclaration = {},
    content: @Composable () -> Unit,
) {
    KoinApplication(
        application = {
            koinAppDeclaration()
            koinAppModules()
        },
    ) {
        content()
    }
}

private fun KoinApplication.koinAppModules() {
    modules(
        koinDataModule(),
        koinViewModelModule(),
    )
}

private fun koinDataModule() = module {
    single { BubblePhoneDataStore(get<DataStore<Preferences>>()) }
    single<OnboardingRepository> { OnboardingRepositoryImpl(get<BubblePhoneDataStore>()) }
}

private fun koinViewModelModule() = module {
    viewModel { OnboardingViewModel(get<OnboardingRepository>()) }
}