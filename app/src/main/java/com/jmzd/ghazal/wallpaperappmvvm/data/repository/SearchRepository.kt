package com.jmzd.ghazal.wallpaperappmvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.jmzd.ghazal.wallpaperappmvvm.data.network.ApiServices
import com.jmzd.ghazal.wallpaperappmvvm.ui.categories.CategoriesPagingSource
import com.jmzd.ghazal.wallpaperappmvvm.ui.search.SearchPagingSource
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api : ApiServices) {

    fun searchPhotos(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { SearchPagingSource(api, query) }
    ).liveData

}