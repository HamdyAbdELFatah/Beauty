package com.hamdy.pinky.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hamdy.pinky.data.UserPreference.PreferencesKeys.userId
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreference @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val userId = stringPreferencesKey("user_id")
    }

    suspend fun saveUser(id: String) {
        dataStore.edit { preferences ->
            preferences[userId] = id
        }
    }

    suspend fun getUser(): String? = dataStore.data.first()[userId]


    suspend fun removeUser() {
        dataStore.edit { preferences ->
            preferences[userId] = ""
        }
    }
}