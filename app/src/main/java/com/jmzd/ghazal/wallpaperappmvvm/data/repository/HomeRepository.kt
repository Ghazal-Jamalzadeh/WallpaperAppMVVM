package com.jmzd.ghazal.wallpaperappmvvm.data.repository

import com.jmzd.ghazal.wallpaperappmvvm.data.network.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api : ApiServices) {
    suspend fun getNewestPhotos() = api.getNewestPhotos()
    suspend fun getTopics() = api.getTopics()
}