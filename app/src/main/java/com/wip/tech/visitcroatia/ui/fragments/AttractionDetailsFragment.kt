package com.wip.tech.visitcroatia.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.wip.tech.visitcroatia.data.Attraction
import com.wip.tech.visitcroatia.databinding.FragmentAttractionDetailsBinding

class AttractionDetailsFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailsBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: AttractionDetailsFragmentArgs by navArgs()

    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = attraction.title
        binding.descriptionTextView.text = attraction.description
        Picasso.get().load(attraction.image_urls).into(binding.headerImageView)
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.numberOfFactsTextView.text = "${attraction.facts.size}facts"
        binding.numberOfFactsTextView.setOnClickListener{
            //TODO
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}