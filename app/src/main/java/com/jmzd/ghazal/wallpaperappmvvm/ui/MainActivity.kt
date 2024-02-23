package com.jmzd.ghazal.wallpaperappmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jmzd.ghazal.wallpaperappmvvm.databinding.ActivityMainBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    //binding
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}