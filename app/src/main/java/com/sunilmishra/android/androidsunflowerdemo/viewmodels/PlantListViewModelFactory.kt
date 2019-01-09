package com.sunilmishra.android.androidsunflowerdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.sunilmishra.android.androidsunflowerdemo.data.PlantRepository

/**
 * Factory for creating a [PlantListViewModel] with a constructor that takes a [PlantRepository].
 */
class PlantListViewModelFactory(
    private val repository: PlantRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = PlantListViewModel(repository) as T
}
