package com.android.sekarya_mobile_app.model.response

data class LoginResponse(
    val message: String,
    val user: User
)

data class User(
    val userId: String,
    val username: String
)