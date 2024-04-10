package com.jmzd.ghazal.wallpaperappmvvm.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jmzd.ghazal.wallpaperappmvvm.data.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: CategoriesRepository, state: SavedStateHandle) : ViewModel() {

    private val currentData = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val categoriesPhotos = currentData.switchMap { id ->
        // in faghat too code ghabli bood
        repository.categoryPhotos(id).cachedIn(viewModelScope)
    }

    fun updateCategoryId(id: String) {
        currentData.value = id
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = ""
    }
}