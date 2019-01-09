package com.sunilmishra.android.androidsunflowerdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.sunilmishra.android.androidsunflowerdemo.data.GardenPlantingRepository
import com.sunilmishra.android.androidsunflowerdemo.data.Plant
import com.sunilmishra.android.androidsunflowerdemo.data.PlantRepository

/**
 * Factory for creating a [PlantDetailViewModel] with a constructor that takes a [PlantRepository]
 * and an ID for the current [Plant].
 */
class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T
    }
}
