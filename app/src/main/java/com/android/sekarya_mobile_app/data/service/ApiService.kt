package com.android.sekarya_mobile_app.data.service

import com.android.sekarya_mobile_app.model.RegisterRequest
import com.android.sekarya_mobile_app.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

}