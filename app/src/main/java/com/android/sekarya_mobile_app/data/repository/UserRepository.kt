package com.android.sekarya_mobile_app.data.repository

import com.android.sekarya_mobile_app.data.service.ApiService
import com.android.sekarya_mobile_app.model.request.RegisterRequest
import com.android.sekarya_mobile_app.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository (private val apiService: ApiService) {

    fun registerUser(
        registerRequest: RegisterRequest,
        onSuccess: (RegisterResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val call = apiService.registerUser(registerRequest)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        onSuccess.invoke(registerResponse)
                    } else {
                        onError.invoke("Respon tidak diketahui")
                    }
                } else {
                    onError.invoke("Registrasi gagal")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                onError.invoke("Error jaringan: ${t.message}")
            }
        })
    }
}