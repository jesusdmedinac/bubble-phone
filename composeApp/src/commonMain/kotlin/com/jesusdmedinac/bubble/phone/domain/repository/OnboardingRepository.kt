package com.jesusdmedinac.bubble.phone.domain.repository

interface OnboardingRepository {
    suspend fun getStarted()
    suspend fun getInstalledApps(): List<String>
}