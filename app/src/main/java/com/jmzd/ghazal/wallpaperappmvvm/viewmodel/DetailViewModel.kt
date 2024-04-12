package com.jmzd.ghazal.wallpaperappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.wallpaperappmvvm.data.model.detail.ResponseDetail
import com.jmzd.ghazal.wallpaperappmvvm.data.model.splash.ResponseRandom
import com.jmzd.ghazal.wallpaperappmvvm.data.repository.DetailRepository
import com.jmzd.ghazal.wallpaperappmvvm.data.repository.SplashRepository
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository : DetailRepository) : ViewModel() {

    //detail
    private val _detailLiveData = MutableLiveData<NetworkRequest<ResponseDetail>>()
    var detailLiveData : LiveData<NetworkRequest<ResponseDetail>> =  _detailLiveData

    //--- api call methods ---//
    fun getDetailPhoto(id : String) = viewModelScope.launch {
        _detailLiveData.value = NetworkRequest.Loading()

        val response = repository.getDetailPhoto(id)

        _detailLiveData.value = NetworkResponse(response).generateResponse()
    }
}