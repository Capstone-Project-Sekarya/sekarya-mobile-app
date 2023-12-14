package com.android.sekarya_mobile_app.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sekarya_mobile_app.data.repository.UserRepository
import com.android.sekarya_mobile_app.model.request.RegisterRequest

class SignUpViewModel (private val userRepository: UserRepository) : ViewModel() {

    val registrationResult = MutableLiveData<String?>()
    fun registerUser(registerRequest: RegisterRequest) {
        userRepository.registerUser(
            registerRequest,
            onSuccess = { response ->
                registrationResult.postValue(response.msg)
            },
            onError = { error ->
                registrationResult.postValue("Error: $error")
            }
        )
    }
}