package com.jesusdmedinac.bubble.phone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusdmedinac.bubble.phone.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class OnboardingViewModel(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    private val _sideEffect = MutableStateFlow<OnboardingSideEffect>(OnboardingSideEffect.Idle)
    val sideEffect = _sideEffect.asStateFlow()

    fun onGetStartedClick() {
        viewModelScope.launch {
            _state.update { state -> state.copy(loading = true) }
            onboardingRepository.getStarted()
            advanceToNextStep()
            _state.update { state -> state.copy(loading = false) }
        }
    }

    private fun advanceToNextStep() {
        _state.update { state -> state.copy(currentStep = state.currentStep?.nextStep) }
    }

    fun onSearchTermChange(searchTerm: String) {
        _state.update { state -> state.copy(searchTerm = searchTerm) }
    }
}

data class OnboardingState(
    val loading: Boolean = false,
    val currentStep: OnboardingStep? = OnboardingStep.Welcome,
    val searchTerm: String = ""
)

@Serializable
sealed class OnboardingStep(
    open val nextStep: OnboardingStep? = null
) {

    @Serializable
    data object Welcome : OnboardingStep(nextStep = PickEssentialApps)

    @Serializable
    data object PickEssentialApps : OnboardingStep(nextStep = Paywall)

    @Serializable
    data object Paywall : OnboardingStep(nextStep = AddTheWidget)

    @Serializable
    data object AddTheWidget : OnboardingStep(nextStep = SetTheWallpaper)

    @Serializable
    data object SetTheWallpaper : OnboardingStep(nextStep = EnableDarkMode)

    @Serializable
    data object EnableDarkMode : OnboardingStep(nextStep = ReduceAnimations)

    @Serializable
    data object ReduceAnimations : OnboardingStep(nextStep = CreateAMinimalistHomeScreen)

    @Serializable
    data object CreateAMinimalistHomeScreen : OnboardingStep(nextStep = ScrollableList)

    @Serializable
    data object ScrollableList : OnboardingStep(nextStep = AllSet)

    @Serializable
    data object AllSet : OnboardingStep()
}

sealed interface OnboardingSideEffect {
    data object Idle : OnboardingSideEffect
}