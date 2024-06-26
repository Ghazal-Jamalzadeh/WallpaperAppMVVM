package com.jmzd.ghazal.wallpaperappmvvm.data.network

import com.jmzd.ghazal.wallpaperappmvvm.data.model.detail.ResponseDetail
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponsePhotos
import com.jmzd.ghazal.wallpaperappmvvm.data.model.home.ResponseTopics
import com.jmzd.ghazal.wallpaperappmvvm.data.model.search.ResponseSearch
import com.jmzd.ghazal.wallpaperappmvvm.data.model.splash.ResponseRandom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("photos/random")
    suspend fun getRandomPhoto() : Response<ResponseRandom>

    @GET("photos")
    suspend fun getNewestPhotos() : Response<ResponsePhotos>

    @GET("topics")
    suspend fun getTopics() : Response<ResponseTopics>

    @GET("topics/{id_or_slug}/photos")
    suspend fun getCategoryPhotos(@Path("id_or_slug") id : String , @Query("page") page : Int) : Response<ResponsePhotos>

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query : String , @Query("page") page : Int) : Response<ResponseSearch>

    @GET("photos/{id}")
    suspend fun getDetailPhoto(@Path("id") id : String) : Response<ResponseDetail>
}