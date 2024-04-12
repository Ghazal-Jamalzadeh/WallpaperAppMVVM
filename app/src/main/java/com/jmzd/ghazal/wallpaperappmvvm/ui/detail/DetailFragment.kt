package com.jmzd.ghazal.wallpaperappmvvm.ui.detail

import android.view.LayoutInflater
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentDetailBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate


}