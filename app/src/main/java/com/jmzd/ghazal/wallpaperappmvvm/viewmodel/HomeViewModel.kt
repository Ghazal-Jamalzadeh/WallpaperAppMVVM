package com.jmzd.ghazal.wallpaperappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponsePhotos
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponseTopics
import com.jmzd.ghazal.wallpaperappmvvm.data.repository.HomeRepository
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.wallpaperappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository : HomeRepository) : ViewModel() {

    //newest
    private val _newestPhotosLiveData = MutableLiveData<NetworkRequest<ResponsePhotos>>()
    var newestPhotosLiveData : LiveData<NetworkRequest<ResponsePhotos>> =  _newestPhotosLiveData
    //topics
    private val _topicsLiveData = MutableLiveData<NetworkRequest<ResponseTopics>>()
    var topicsLiveData : LiveData<NetworkRequest<ResponseTopics>> =  _topicsLiveData


    //--- api call methods ---//
    fun getNewestPhotos() = viewModelScope.launch {
        _newestPhotosLiveData.value = NetworkRequest.Loading()

        val response = repository.getNewestPhotos()

        _newestPhotosLiveData.value = NetworkResponse(response).generateResponse()
    }

    fun getTopics() = viewModelScope.launch {
        _topicsLiveData.value = NetworkRequest.Loading()

        val response = repository.getTopics()

        _topicsLiveData.value = NetworkResponse(response).generateResponse()
    }
}