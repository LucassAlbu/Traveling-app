package com.wip.tech.visitcroatia.ui.fragments

import androidx.fragment.app.Fragment
import com.wip.tech.visitcroatia.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }
}