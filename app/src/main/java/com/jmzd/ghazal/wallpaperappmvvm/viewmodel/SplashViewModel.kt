package com.jmzd.ghazal.wallpaperappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.wallpaperappmvvm.data.model.splash.ResponseRandom
import com.jmzd.ghazal.wallpaperappmvvm.data.repository.SplashRepository
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository : SplashRepository) : ViewModel() {

    //random
    private val _randomLiveData = MutableLiveData<NetworkRequest<ResponseRandom>>()
    var randomLiveData : LiveData<NetworkRequest<ResponseRandom>> =  _randomLiveData

    //--- api call methods ---//
    fun getRandomPhoto() = viewModelScope.launch {
        _randomLiveData.value = NetworkRequest.Loading()

        val response = repository.getRandomPhoto()

        _randomLiveData.value = NetworkResponse(response).generateResponse()
    }
}