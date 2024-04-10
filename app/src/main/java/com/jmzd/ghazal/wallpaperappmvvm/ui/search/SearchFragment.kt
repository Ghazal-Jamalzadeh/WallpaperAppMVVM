package com.jmzd.ghazal.wallpaperappmvvm.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentSearchBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }

}