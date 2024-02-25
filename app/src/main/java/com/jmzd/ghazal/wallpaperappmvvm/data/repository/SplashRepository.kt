package com.jmzd.ghazal.wallpaperappmvvm.data.repository

import com.jmzd.ghazal.wallpaperappmvvm.data.network.ApiServices
import javax.inject.Inject

class SplashRepository @Inject constructor(private val api : ApiServices) {
    suspend fun getRandomPhoto() = api.getRandomPhoto()
}