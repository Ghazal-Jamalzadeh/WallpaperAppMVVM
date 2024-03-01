package com.jmzd.ghazal.wallpaperappmvvm.data.repository

import com.jmzd.ghazal.wallpaperappmvvm.data.network.ApiServices
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val api : ApiServices) {
    suspend fun getCategoriesPhotos(id : String , page : Int ) = api.getCategoryPhotos(id , page )
}