package com.android.sekarya_mobile_app.data.repository

import android.util.Log
import com.android.sekarya_mobile_app.BuildConfig
import com.android.sekarya_mobile_app.data.configuration.ApiConfig
import com.android.sekarya_mobile_app.data.service.ApiService
import com.android.sekarya_mobile_app.model.PrefrenceModel
import com.android.sekarya_mobile_app.model.request.LoginRequest
import com.android.sekarya_mobile_app.model.request.RegisterRequest
import com.android.sekarya_mobile_app.model.response.LoginResponse
import com.android.sekarya_mobile_app.model.response.RegisterResponse
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository (
    private val apiService: ApiService,
    private val preferenceManager: PreferenceManager
    ) {

    suspend fun registerUser(user: RegisterRequest): Flow<Response<RegisterResponse>> = flow {
        val apiService = ApiConfig.getApiService()
        try {
            emit(Response.Loading)
            val response = apiService.registerUser(BuildConfig.API_KEY, user)
            emit(Response.Success(response))
        } catch (e: Exception) {
                Log.e("TAG_REGISTER", "onFailure: ${e.message.toString()}")
            emit(Response.Error(e.message.toString()))
        }
    }

    suspend fun login(user: LoginRequest) :Flow<Response<LoginResponse>> = flow {
        val apiService = ApiConfig.getApiService()
        try {
            emit(Response.Loading)
            val response = apiService.loginUser(BuildConfig.API_KEY, user)
            preferenceManager.saveSession(
                PrefrenceModel(
                    response.user.username,
                    response.user.userId,
                    true
                )
            )
            emit(Response.Success(response))
        } catch (e: Exception) {
            Log.e("TAG_LOGIN", "onFailure: ${e.message.toString()}")
            emit(Response.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            preferenceManager: PreferenceManager
        ) = instance ?: synchronized(this) {
            instance ?: UserRepository(apiService, preferenceManager)
        }.also { instance = it }
    }

}