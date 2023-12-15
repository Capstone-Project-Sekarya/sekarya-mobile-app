package com.android.sekarya_mobile_app.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("msg")
	val msg: String? = null
)
