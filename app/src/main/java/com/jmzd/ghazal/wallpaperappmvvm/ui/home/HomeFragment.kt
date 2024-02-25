package com.jmzd.ghazal.wallpaperappmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentHomeBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.setStatusBarIconsColor
import com.jmzd.ghazal.wallpaperappmvvm.utils.showSnackBar
import com.jmzd.ghazal.wallpaperappmvvm.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    //viewModel
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(true)
        //call api
        viewModel.getNewestPhotos()
        //observers
        loadNewestPhotosLiveData()
    }

    //Newest
    private fun loadNewestPhotosLiveData() {
        binding.apply {
            viewModel.newestPhotosLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        newestList.showShimmer()
                    }

                    is NetworkRequest.Success -> {
                        newestList.hideShimmer()
                        response.data?.let { data ->
                            if (data.isNotEmpty()) {
//                                initNewestRecycler(data)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        newestList.hideShimmer()
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }

}