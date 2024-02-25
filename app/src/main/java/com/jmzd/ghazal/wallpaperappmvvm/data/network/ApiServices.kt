package com.jmzd.ghazal.wallpaperappmvvm.data.network

import com.jmzd.ghazal.wallpaperappmvvm.data.model.splash.ResponseRandom
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("/photos/random")
    suspend fun getRandomPhoto() : Response<ResponseRandom>
}