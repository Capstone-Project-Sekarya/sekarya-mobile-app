package com.android.sekarya_mobile_app.data.repository

import android.util.Log
import com.android.sekarya_mobile_app.BuildConfig
import com.android.sekarya_mobile_app.data.configuration.ApiConfig
import com.android.sekarya_mobile_app.data.service.ApiService
import com.android.sekarya_mobile_app.model.response.PredictArtResponse
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ArtPredictRepository(private val apiService: ApiService,
    private val preferenceManager: PreferenceManager){


    suspend fun predictArt(
        artPhoto: File?
    ): Flow<Response<PredictArtResponse>> = flow {
        val apiService = ApiConfig.getApiServiceAI()
        try {
            emit(Response.Loading)
            val response = apiService.predictArt(
                "sekaryamlbackend700",
                artPhoto?.toMultipart()
            )
            emit(Response.Success(response))
        } catch (e: Exception) {
            Log.e("TAG_ADD_ART", "onFailure: ${e.message.toString()}")
            emit(Response.Error(e.message.toString()))
        }
    }

    fun File.toMultipart(): MultipartBody.Part {
        return MultipartBody.Part
            .createFormData(
                name = "image",
                filename = this.name,
                body = this.asRequestBody()
            )
    }

    companion object {
        @Volatile
        private var instance: ArtPredictRepository? = null
        fun getInstance(
            apiService: ApiService,
            preferenceManager: PreferenceManager
        ) = instance ?: synchronized(this) {
            instance ?: ArtPredictRepository(apiService, preferenceManager)
        }.also { instance = it }
    }
}