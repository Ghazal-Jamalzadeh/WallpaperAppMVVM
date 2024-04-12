package com.jmzd.ghazal.wallpaperappmvvm.data.repository

import com.jmzd.ghazal.wallpaperappmvvm.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api : ApiServices) {
    suspend fun getDetailPhoto(id : String) = api.getDetailPhoto(id)
}