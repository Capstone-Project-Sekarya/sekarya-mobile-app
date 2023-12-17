package com.android.sekarya_mobile_app.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.android.sekarya_mobile_app.model.PrefrenceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preference")
class PreferenceManager private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveSession(user: PrefrenceModel) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.username
            preferences[USERID_KEY] = user.userId
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<PrefrenceModel> {
        return dataStore.data.map { preferences ->
            PrefrenceModel(
                preferences[USERNAME_KEY] ?: "",
                preferences[USERID_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun getUserId(): String? {
        return dataStore.data.map { preferences ->
            preferences[USERID_KEY]
        }.firstOrNull()
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PreferenceManager? = null

        private val USERNAME_KEY = stringPreferencesKey("username")
        private val USERID_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>):PreferenceManager {
            return INSTANCE ?: synchronized(this) {
                val instance = PreferenceManager(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}