package com.wip.tech.visitcroatia.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.wip.tech.visitcroatia.R
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

        val epoxyController = HomeFragmentController { attractionId ->
         activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailsFragment)
        }
        binding.epoxyRecyclerView.setController(epoxyController)
        binding.epoxyRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                RecyclerView.VERTICAL
            )
        )
        epoxyController.isLoading = true
        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner) { attractions ->
            epoxyController.attractions = attractions

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}