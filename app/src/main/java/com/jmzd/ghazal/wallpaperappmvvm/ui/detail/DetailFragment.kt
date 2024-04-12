package com.jmzd.ghazal.wallpaperappmvvm.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentDetailBinding
import com.jmzd.ghazal.wallpaperappmvvm.ui.search.SearchFragmentArgs
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.wallpaperappmvvm.utils.setStatusBarIconsColor
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(false)
        //Args
        args.let {
            //Call api
            viewModel.getDetailPhoto(it.id)
        }

    }


}