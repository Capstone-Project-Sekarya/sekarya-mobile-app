package com.android.sekarya_mobile_app.model.request

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String,
    val phone: String,
    val gender: String,
    val age: Int,
    val jobCategory: String,
    val dateOfBirth: String,
    val bio: String
)
