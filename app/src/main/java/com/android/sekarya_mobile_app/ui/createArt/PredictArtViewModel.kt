package com.android.sekarya_mobile_app.ui.createArt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sekarya_mobile_app.data.repository.ArtPredictRepository

import com.android.sekarya_mobile_app.model.response.Event
import com.android.sekarya_mobile_app.model.response.PredictArtResponse
import com.android.sekarya_mobile_app.model.response.Response
import kotlinx.coroutines.launch
import java.io.File

class PredictArtViewModel (private val artPredictRepository: ArtPredictRepository) : ViewModel() {

    val predictResult = MutableLiveData<Event<Response<PredictArtResponse>>>()


    fun predictArt (
        artPhoto : File?,
    ){
        viewModelScope.launch {
            artPredictRepository.predictArt(
                artPhoto!!,
            ).collect{ result ->
                predictResult.postValue(Event(result))
            }
        }
    }

}