package com.wip.tech.visitcroatia.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.wip.tech.visitcroatia.databinding.FragmentHomeBinding
import com.wip.tech.visitcroatia.ui.fragments.BaseFragment


class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdpter = HomeFragmentAdpter { attractionId ->
            val navDirections = HomeFragmentDirections.actionHomeFragmentToAttractionDetailsFragment(attractionId)
            navController.navigate(navDirections)

        }
        binding.recyclerView.adapter = homeAdpter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireActivity(),RecyclerView.VERTICAL))

        homeAdpter.setData(attractions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}