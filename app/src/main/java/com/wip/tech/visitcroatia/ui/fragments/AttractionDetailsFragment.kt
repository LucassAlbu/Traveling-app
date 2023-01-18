package com.wip.tech.visitcroatia.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.wip.tech.visitcroatia.databinding.FragmentAttractionDetailsBinding

class AttractionDetailsFragment : Fragment() {

    private var _binding: FragmentAttractionDetailsBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: AttractionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.text = safeArgs.attractionId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}