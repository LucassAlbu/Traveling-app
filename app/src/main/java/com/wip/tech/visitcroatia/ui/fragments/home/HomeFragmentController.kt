package com.wip.tech.visitcroatia.ui.fragments.home

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.wip.tech.visitcroatia.R
import com.wip.tech.visitcroatia.data.Attraction
import com.wip.tech.visitcroatia.databinding.ViewHolderAttractionBinding
import com.wip.tech.visitcroatia.ui.epoxy.LoadingEpoxyModel
import com.wip.tech.visitcroatia.ui.epoxy.ViewBindingKotlinModel

class HomeFragmentController(
    private val onClikedCallBack: (String) -> Unit
) : EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }
        if (attractions.isEmpty()) {
            return
        }
        attractions.forEach { attraction ->
            AttractionsEpoxyModel(attraction, onClikedCallBack).id(attraction.id).addTo(this)
        }
    }

    data class AttractionsEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {

        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_urls).into(headerImageView)
            monthsToVisitTextView.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }


}