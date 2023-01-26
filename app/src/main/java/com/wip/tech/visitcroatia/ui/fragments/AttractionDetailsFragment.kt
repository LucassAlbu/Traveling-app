package com.wip.tech.visitcroatia.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.wip.tech.visitcroatia.R
import com.wip.tech.visitcroatia.databinding.FragmentAttractionDetailsBinding

class AttractionDetailsFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailsBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.titleTextView.text = attraction.title
            binding.descriptionTextView.text = attraction.description
            Picasso.get().load(attraction.image_urls).into(binding.headerImageView)
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.numberOfFactsTextView.text = "${attraction.facts.size}facts"
            binding.numberOfFactsTextView.setOnClickListener {
                val stringBuilder = StringBuilder("")
                attraction.facts.forEach {
                    stringBuilder.append("\u2022 $it")
                    stringBuilder.append("\n\n")
                }
                val message =
                    stringBuilder.toString()
                        .substring(0, stringBuilder.toString().lastIndexOf("\n\n"))

                AlertDialog.Builder(requireContext(), R.style.MyDialog)
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("ok") { dialog, whitch ->

                    }
                    .show()

            }
        }
        setMenu()
    }


    private fun setMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_attraction_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.menuItemLocation -> {
                        setGoogleMaps()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setGoogleMaps() {
        val attraction = activityViewModel.selectedAttractionLiveData.value ?: return
        activityViewModel.locationSelectedLiveData.postValue(attraction)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
