package com.android.sekarya_mobile_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.sekarya_mobile_app.BuildConfig
import com.android.sekarya_mobile_app.data.configuration.ApiConfig
import com.android.sekarya_mobile_app.data.repository.UserRepository
import com.android.sekarya_mobile_app.model.response.AllArtResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    var artList = MutableLiveData<List<AllArtResponse>>()


}