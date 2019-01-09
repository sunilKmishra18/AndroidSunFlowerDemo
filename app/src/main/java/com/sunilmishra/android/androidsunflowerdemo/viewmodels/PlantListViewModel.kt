package com.sunilmishra.android.androidsunflowerdemo.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunilmishra.android.androidsunflowerdemo.PlantListFragment
import com.sunilmishra.android.androidsunflowerdemo.data.Plant
import com.sunilmishra.android.androidsunflowerdemo.data.PlantRepository

/**
 * The ViewModel for [PlantListFragment].
 */
class PlantListViewModel internal constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val growZoneNumber = MutableLiveData<Int>()

    private val plantList = MediatorLiveData<List<Plant>>()

    init {
        growZoneNumber.value = NO_GROW_ZONE

        val livePlantList = Transformations.switchMap(growZoneNumber) {
            if (it == NO_GROW_ZONE) {
                plantRepository.getPlants()
            } else {
                plantRepository.getPlantsWithGrowZoneNumber(it)
            }
        }
        plantList.addSource(livePlantList, plantList::setValue)
    }

    fun getPlants() = plantList

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }
}
