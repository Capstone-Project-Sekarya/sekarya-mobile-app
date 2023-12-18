package com.android.sekarya_mobile_app.model.response

data class AllArtResponse(
    val artId: String?,
    val artName: String?,
    val artPhoto: String?,
    val artPrice: Int?,
    val likedBy: List<Any?>?,
    val likes: Int?,
    val tags: String?,
    val uploadDate: String?,
    val userId: String?,
    val viewedBy: List<Any?>?,
    val views: Int?
)