package com.android.sekarya_mobile_app.data.repository

import android.util.Log
import com.android.sekarya_mobile_app.BuildConfig
import com.android.sekarya_mobile_app.data.configuration.ApiConfig
import com.android.sekarya_mobile_app.data.service.ApiService
import com.android.sekarya_mobile_app.model.request.AddArtRequest
import com.android.sekarya_mobile_app.model.response.AddArtResponse
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ArtRepository(private val apiService: ApiService,
                    private val preferenceManager: PreferenceManager
) {

    suspend fun addArt(
        artPhoto : File?,
        artName : String?,
        tags :String?,
        artDescription :String?
    ): Flow<Response<AddArtResponse>> = flow {
        val apiService = ApiConfig.getApiService()
        try {
            emit(Response.Loading)
            val response = apiService.addArt(
                BuildConfig.API_KEY,
                artPhoto?.toMultipart(),
                artName?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                tags?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                artDescription?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                preferenceManager.getUserId()?.toRequestBody("multipart/form-data".toMediaTypeOrNull())
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
                name = "photo",
                filename = this.name,
                body = this.asRequestBody()
            )
    }


    companion object {
        @Volatile
        private var instance: ArtRepository? = null
        fun getInstance(
            apiService: ApiService,
            preferenceManager: PreferenceManager
        ) = instance ?: synchronized(this) {
            instance ?: ArtRepository(apiService, preferenceManager)
        }.also { instance = it }
    }
}
