package com.jmzd.ghazal.wallpaperappmvvm.ui.detail

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jmzd.ghazal.wallpaperappmvvm.R
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentDetailBinding
import com.jmzd.ghazal.wallpaperappmvvm.ui.search.SearchFragmentArgs
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.wallpaperappmvvm.utils.changeVisibility
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.setStatusBarIconsColor
import com.jmzd.ghazal.wallpaperappmvvm.utils.showSnackBar
import com.jmzd.ghazal.wallpaperappmvvm.viewmodel.DetailViewModel
import com.jmzd.ghazal.wallpaperappmvvm.viewmodel.SearchViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    //viewModel
    private val viewModel by viewModels<DetailViewModel>()

    //args
    private val args by navArgs<DetailFragmentArgs>()

    //wallpaper
    private val wallpaperManager by lazy { WallpaperManager.getInstance(requireContext()) }
    private lateinit var imageBitmap: Bitmap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(false)
        //Args
        args.let {
            //Call api
            viewModel.getDetailPhoto(it.id)
        }

        loadData()

    }


    private fun loadData() {
        binding.apply {
            viewModel.detailLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.changeVisibility(true, container)
                    }

                    is NetworkRequest.Success -> {
                        response.data?.let { data ->
//                            //Init rotate view
//                            initRotateView(data.urls?.regular!!)
//                            activeRotateView()
//                            //Set wallpaper
                            setWallpaperImg.setOnClickListener {
//                                wallpaperManager.setBitmap(imageBitmap)
                                setWallpaperImg.setImageResource(R.drawable.check)
                            }
//                            //Download
//                            downloadLay.setOnClickListener {
//                                requestPermission()
//                                data.urls.full?.let {
//                                    downloadImage(it, data.slug!!)
//                                }
//                            }
//                            //Info
//                            infoImg.setOnClickListener {
//                                val direction = DetailFragmentDirections.actionDetailToInfo(data)
//                                findNavController().navigate(direction)
//                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.changeVisibility(false, container)
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }




}