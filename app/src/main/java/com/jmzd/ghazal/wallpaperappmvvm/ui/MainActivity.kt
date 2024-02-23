package com.jmzd.ghazal.wallpaperappmvvm.ui

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.jmzd.ghazal.wallpaperappmvvm.databinding.ActivityMainBinding
import com.jmzd.ghazal.wallpaperappmvvm.utils.base.BaseActivity
import com.jmzd.ghazal.wallpaperappmvvm.utils.setStatusBarIconsColor
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

        //Transparent status bar
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            //Change color of status bar icons
            setStatusBarIconsColor(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}