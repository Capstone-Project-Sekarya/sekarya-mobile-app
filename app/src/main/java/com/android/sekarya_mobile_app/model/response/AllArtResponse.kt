package com.android.sekarya_mobile_app.model.response

import com.google.gson.annotations.SerializedName

data class AllArtResponse(
	@field:SerializedName("artId")
	val artId: String? = null,

	@field:SerializedName("artName")
	val artName: String? = null,

	@field:SerializedName("uploadDate")
	val uploadDate: String? = null,

	@field:SerializedName("likedBy")
	val likedBy: List<Any?>? = null,

	@field:SerializedName("viewedBy")
	val viewedBy: List<Any?>? = null,

	@field:SerializedName("artDescription")
	val artDescription: String? = null,

	@field:SerializedName("artPhoto")
	val artPhoto: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("tags")
	val tags: String? = null,

	@field:SerializedName("likes")
	val likes: Int? = null,

	@field:SerializedName("photoProfile")
	val photoProfile: String? = null
)


