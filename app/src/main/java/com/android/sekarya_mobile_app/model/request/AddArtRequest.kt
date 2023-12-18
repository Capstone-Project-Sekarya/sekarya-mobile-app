package com.android.sekarya_mobile_app.model.request

data class AddArtRequest (
    val artPhoto : String,
    val artName : String?,
    val tags :String?,
    val artDescription :String?,
    val userId : String?
    )