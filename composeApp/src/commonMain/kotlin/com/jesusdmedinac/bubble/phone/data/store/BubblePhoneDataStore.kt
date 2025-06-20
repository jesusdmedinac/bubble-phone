package com.jesusdmedinac.bubble.phone.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first

class BubblePhoneDataStore(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun isFirstTime(): Boolean = dataStore
        .data
        .first()[PREFERENCES_KEY_IS_FIRST_TIME]
        ?: true

    suspend fun setIsFirstTime(isFirstTime: Boolean) {
        dataStore
            .edit { preferences ->
                preferences[PREFERENCES_KEY_IS_FIRST_TIME] = isFirstTime
            }
    }

    companion object {
        val PREFERENCES_KEY_IS_FIRST_TIME = booleanPreferencesKey("PREFERENCES_KEY_IS_FIRST_TIME")
    }
}