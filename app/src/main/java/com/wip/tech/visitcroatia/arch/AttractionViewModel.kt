package com.wip.tech.visitcroatia.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wip.tech.visitcroatia.data.Attraction

class AttractionViewModel :ViewModel() {

    private val repository = AttractionsRepository()

    val attractionListLiveData = MutableLiveData<List<Attraction>>()

    fun init (context: Context){
        val attractionsList = repository.parseAttractions(context)
        attractionListLiveData.postValue(attractionsList)
        attractionListLiveData.value = attractionsList
    }
}