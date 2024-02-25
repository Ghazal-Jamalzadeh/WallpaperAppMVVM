package com.jmzd.ghazal.wallpaperappmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponsePhotos.ResponsePhotosItem
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentHomeBinding
import com.jmzd.ghazal.wallpaperappmvvm.ui.home.adapters.NewestPhotosAdapter
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.setStatusBarIconsColor
import com.jmzd.ghazal.wallpaperappmvvm.utils.setupRecyclerview
import com.jmzd.ghazal.wallpaperappmvvm.utils.showSnackBar
import com.jmzd.ghazal.wallpaperappmvvm.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    //viewModel
    private val viewModel by viewModels<HomeViewModel>()

    //adapters
    @Inject
    lateinit var newestPhotosAdapter: NewestPhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(true)
        //call api
        viewModel.getNewestPhotos()
        //observers
        loadNewestPhotosLiveData()
    }

    //--- observers ---//
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
                                initNewestRecycler(data)
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

    //--- recycler views ---//
    private fun initNewestRecycler(list: List<ResponsePhotosItem>) {
        newestPhotosAdapter.setData(list)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.newestList.setupRecyclerview(layoutManager, newestPhotosAdapter)
        //Click
        newestPhotosAdapter.setOnItemClickListener {
//            val direction = HomeFragmentDirections.actionToDetail(it)
//            findNavController().navigate(direction)
        }
    }

}