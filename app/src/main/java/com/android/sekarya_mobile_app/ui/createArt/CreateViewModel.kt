package com.android.sekarya_mobile_app.ui.createArt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sekarya_mobile_app.data.repository.ArtRepository
import com.android.sekarya_mobile_app.model.request.AddArtRequest
import com.android.sekarya_mobile_app.model.response.AddArtResponse
import com.android.sekarya_mobile_app.model.response.Event
import com.android.sekarya_mobile_app.model.response.RegisterResponse
import com.android.sekarya_mobile_app.model.response.Response
import kotlinx.coroutines.launch
import java.io.File

class CreateViewModel(private val artRepository: ArtRepository) : ViewModel() {

    val creatResult = MutableLiveData<Event<Response<AddArtResponse>>>()

         fun addArt (
             artPhoto : File?,
             artName : String?,
             tags :String?,
             artDescription :String?
         ){
             viewModelScope.launch {
                 artRepository.addArt(
                         artPhoto!!,
                         artName,
                         tags,
                         artDescription
                 ).collect{ result ->
                     creatResult.postValue(Event(result))
                     }
             }
         }
}