package com.wip.tech.visitcroatia.ui.fragments

import androidx.fragment.app.Fragment
import com.wip.tech.visitcroatia.data.Attraction
import com.wip.tech.visitcroatia.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attractions: List<Attraction>
    get() = (activity as MainActivity).attractionsList
}