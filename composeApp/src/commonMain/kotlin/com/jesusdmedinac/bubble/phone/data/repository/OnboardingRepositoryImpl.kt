package com.jesusdmedinac.bubble.phone.data.repository

import com.jesusdmedinac.bubble.phone.data.framework.GetAppsInstalledOnPlatform
import com.jesusdmedinac.bubble.phone.data.store.BubblePhoneDataStore
import com.jesusdmedinac.bubble.phone.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
    private val bubblePhoneDataStore: BubblePhoneDataStore,
    private val getAppsInstalledOnPlatform: GetAppsInstalledOnPlatform
) : OnboardingRepository {
    override suspend fun getStarted() {
        bubblePhoneDataStore.setIsFirstTime(false)
    }

    override suspend fun getInstalledApps(): List<String> =
        getAppsInstalledOnPlatform()
}