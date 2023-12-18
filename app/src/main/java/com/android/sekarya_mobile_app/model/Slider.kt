package com.android.sekarya_mobile_app.model

data class Slider(
    var imageId: Int
)

data class TagList(
    var tag: String
)

data class HomeCard(
    var imageId: Int,
    var imgBookmark: Int,
    var imgUser: Int,
    var userName: String
)
