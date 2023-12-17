package com.android.sekarya_mobile_app.data.service

import com.android.sekarya_mobile_app.model.request.AddArtRequest
import com.android.sekarya_mobile_app.model.request.LoginRequest
import com.android.sekarya_mobile_app.model.request.RegisterRequest
import com.android.sekarya_mobile_app.model.response.AddArtResponse
import com.android.sekarya_mobile_app.model.response.LoginResponse
import com.android.sekarya_mobile_app.model.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("/register")
    suspend fun registerUser(
        @Header("api-key") apiKey: String,
        @Body request: RegisterRequest): RegisterResponse

    @POST("/login")
    suspend fun loginUser(
        @Header("api-key") apiKey: String,
        @Body loginRequest: LoginRequest): LoginResponse

    @POST("/addArt")
    @Multipart
    suspend fun addArt(
        @Header("api-key") apiKey: String,
        @Part artPhoto : MultipartBody.Part?,
        @Part artName : RequestBody? = null,
        @Part tags :RequestBody? = null,
        @Part artDescription :RequestBody? = null,
        @Part userId : RequestBody? = null
    ): AddArtResponse
}
