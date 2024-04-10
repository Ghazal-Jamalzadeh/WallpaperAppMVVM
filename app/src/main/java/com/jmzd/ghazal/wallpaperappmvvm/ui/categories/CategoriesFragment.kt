package com.jmzd.ghazal.wallpaperappmvvm.ui.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jmzd.ghazal.wallpaperappmvvm.R
import com.jmzd.ghazal.wallpaperappmvvm.databinding.FragmentCategoriesBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.wallpaperappmvvm.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCategoriesBinding
        get() = FragmentCategoriesBinding::inflate

    //viewModel
    private val viewModel by viewModels<CategoriesViewModel>()

    //args
    private val args by navArgs<CategoriesFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Args
        args.let {
            //Call api
            viewModel.updateCategoryId(it.topicId)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //Back
            backImg.setOnClickListener { findNavController().popBackStack() }
            //Title
            args.let {
                categoriesTitle.text = "${it.title} ${getString(R.string.photos)}"
            }
        }
    }

}