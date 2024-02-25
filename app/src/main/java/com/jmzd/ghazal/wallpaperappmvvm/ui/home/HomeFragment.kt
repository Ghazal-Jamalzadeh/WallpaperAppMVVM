package com.jmzd.ghazal.wallpaperappmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentHomeBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}