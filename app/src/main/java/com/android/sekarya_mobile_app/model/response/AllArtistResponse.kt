package com.android.sekarya_mobile_app.model.response

import com.google.gson.annotations.SerializedName

data class AllArtistResponse(

	@field:SerializedName("photoProfile")
	val photoProfile: Any? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
