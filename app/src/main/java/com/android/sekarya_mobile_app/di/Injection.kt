package com.android.sekarya_mobile_app.di

import android.content.Context
import com.android.sekarya_mobile_app.data.configuration.ApiConfig
import com.android.sekarya_mobile_app.data.repository.ArtRepository
import com.android.sekarya_mobile_app.data.repository.UserRepository
import com.android.sekarya_mobile_app.utils.PreferenceManager
import com.android.sekarya_mobile_app.utils.dataStore
import kotlinx.coroutines.runBlocking

object Injection {
    fun userRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val preferenceManager = PreferenceManager.getInstance(context.dataStore)
        return UserRepository.getInstance(apiService, preferenceManager)
    }

    fun artRepository(context: Context) : ArtRepository {
        val apiService = ApiConfig.getApiService()
        val preferenceManager = PreferenceManager.getInstance(context.dataStore)
        return ArtRepository.getInstance(apiService, preferenceManager)
    }
}