package com.jmzd.ghazal.wallpaperappmvvm.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentCategoriesBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCategoriesBinding
        get() = FragmentCategoriesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }

}