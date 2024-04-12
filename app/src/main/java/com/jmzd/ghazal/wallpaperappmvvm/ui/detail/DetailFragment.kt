package com.jmzd.ghazal.wallpaperappmvvm.ui.detail

import academy.nouri.rotateview.RotateView
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    private lateinit var rotateView: RotateView
    private var isEnabledRotateView = false

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

    override fun onStop() {
        super.onStop()
        if (this::rotateView.isInitialized)
            rotateView.unRegisterListener()
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
                            initRotateView(data.urls?.regular!!)
                            activeRotateView()
//                            //Set wallpaper
                            setWallpaperImg.setOnClickListener {
                                wallpaperManager.setBitmap(imageBitmap)
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

    private fun initRotateView(imageUrl: String) {
        binding.apply {
            lifecycleScope.launch {
                //Create bitmap from image
                val loader = ImageLoader(requireContext())
                val request = ImageRequest.Builder(requireContext())
                    .data(imageUrl)
                    .allowHardware(false)
                    .build()
                val result = (loader.execute(request) as SuccessResult).drawable
                imageBitmap = (result as BitmapDrawable).bitmap
                //Delay
                delay(200)
                //Rotate view
                rotateView = RotateView.getInstance(requireContext())!!
                rotateView.apply {
                    setImageWithBitmap(coverImg, imageBitmap)
                    center()
                }
                //Hide loading
                loading.changeVisibility(false, container)
            }
        }
    }

    private fun activeRotateView() {
        binding.apply {
            rotateViewImg.apply {
                setOnClickListener {
                    if (rotateView.isDeviceSupported()) {
                        isEnabledRotateView = if (isEnabledRotateView.not()) {
                            rotateView.registerListener()
                            setBackgroundResource(R.drawable.bg_circle_azure_alpha_selected)
                            true
                        } else {
                            rotateView.unRegisterListener()
                            setBackgroundResource(R.drawable.bg_circle_azure_alpha)
                            false
                        }
                    } else {
                        root.showSnackBar(getString(R.string.notSupportRotateView))
                    }
                }
            }
        }
    }




}