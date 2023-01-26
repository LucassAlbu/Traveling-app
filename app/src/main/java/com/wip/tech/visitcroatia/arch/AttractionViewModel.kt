package com.wip.tech.visitcroatia.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wip.tech.visitcroatia.data.Attraction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttractionViewModel :ViewModel() {

    private val repository = AttractionsRepository()

    val attractionListLiveData = MutableLiveData<ArrayList<Attraction>>()

    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    val locationSelectedLiveData = MutableLiveData<Attraction>()


    fun init (context: Context){
     viewModelScope.launch {
         delay(5_000)
         val attractionsList = repository.parseAttractions(context)
         attractionListLiveData.postValue(attractionsList)
     }
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionListLiveData.value?.find {
            it.id  == attractionId
        }?: return

        selectedAttractionLiveData.postValue(attraction)
    }
}