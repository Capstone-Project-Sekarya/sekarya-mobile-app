package com.android.sekarya_mobile_app.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sekarya_mobile_app.data.repository.UserRepository
import com.android.sekarya_mobile_app.model.request.LoginRequest
import com.android.sekarya_mobile_app.model.request.RegisterRequest
import com.android.sekarya_mobile_app.model.response.LoginResponse
import com.android.sekarya_mobile_app.model.response.RegisterResponse
import com.android.sekarya_mobile_app.model.response.Response
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel (private val userRepository: UserRepository) : ViewModel() {

    val registrationResult = MutableLiveData<Response<RegisterResponse>>()
    val loginResult = MutableLiveData<Response<LoginResponse>>()
    fun registerUser( username: String,
                      email: String,
                      password: String,
                      fullName: String,
                      phone: String,
                      gender: String,
                      age: Int,
                      jobCategory: String,
                      dateOfBirth: String,
                      bio: String) {
        viewModelScope.launch {
            userRepository.registerUser(
                RegisterRequest(
                    username,
                    email,
                    password,
                    fullName,
                    phone,
                    gender,
                    age,
                    jobCategory,
                    dateOfBirth,
                    bio
                )
            ).collect{ result ->
                registrationResult.value = result
            }
        }
    }

    fun login (email : String, password: String){
        viewModelScope.launch {
            userRepository.login(
                LoginRequest(
                    email,
                    password
                )
            ).collect{ result ->
            loginResult.value = result
            }
        }
    }
}