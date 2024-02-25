package com.jmzd.ghazal.wallpaperappmvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jmzd.ghazal.wallpaperappmvvm.R
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentSplashBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.SPLASH_DELAY
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.wallpaperappmvvm.utils.loadImage
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.setStatusBarIconsColor
import com.jmzd.ghazal.wallpaperappmvvm.utils.showSnackBar
import com.jmzd.ghazal.wallpaperappmvvm.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    //viewModel
    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(false)
        //Call api
        viewModel.getRandomPhoto()
        //observers
        loadRandomPhoto()
    }


    private fun loadRandomPhoto() {
        binding.apply {
            viewModel.randomLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.isVisible = true
                    }

                    is NetworkRequest.Success -> {
                        response.data?.let { data ->
                            data.urls?.regular?.let { animateBgImg.loadImage(it) }
                            loading.isVisible = false
                            //Navigate with delay
                            lifecycleScope.launch {
                                delay(SPLASH_DELAY)
                                findNavController().popBackStack(R.id.splashFragment, true)
//                                findNavController().navigate(R.id.actionToHome)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.isVisible = false
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }

}