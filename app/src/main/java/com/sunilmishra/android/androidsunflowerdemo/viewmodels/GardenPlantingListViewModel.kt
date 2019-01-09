package com.sunilmishra.android.androidsunflowerdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunilmishra.android.androidsunflowerdemo.data.GardenPlantingRepository
import com.sunilmishra.android.androidsunflowerdemo.data.PlantAndGardenPlantings

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val gardenPlantings = gardenPlantingRepository.getGardenPlantings()

    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
            Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings()) { plantings ->
                plantings.filter { it.gardenPlantings.isNotEmpty() }
            }
}