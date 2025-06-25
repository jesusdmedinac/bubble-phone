package com.jesusdmedinac.bubble.phone.data.repository

import com.jesusdmedinac.bubble.phone.data.store.BubblePhoneDataStore
import com.jesusdmedinac.bubble.phone.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
    private val bubblePhoneDataStore: BubblePhoneDataStore,
) : OnboardingRepository {
    override suspend fun getStarted() {
        bubblePhoneDataStore.setIsFirstTime(false)
    }
}