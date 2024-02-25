package com.jmzd.ghazal.wallpaperappmvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentSplashBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {  }
    }

}