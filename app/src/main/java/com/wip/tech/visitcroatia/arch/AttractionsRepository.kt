package com.wip.tech.visitcroatia.arch

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wip.tech.visitcroatia.R
import com.wip.tech.visitcroatia.data.Attraction
import com.wip.tech.visitcroatia.data.AttractionsResponse

class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): List<Attraction> {
        val textFromFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        val adapter: JsonAdapter<AttractionsResponse> =
            moshi.adapter(AttractionsResponse::class.java)
        
        return adapter.fromJson(textFromFile)!!.attractions
    }
}