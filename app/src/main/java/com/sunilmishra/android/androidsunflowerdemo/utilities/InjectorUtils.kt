package com.sunilmishra.android.androidsunflowerdemo.utilities

import android.content.Context
import com.sunilmishra.android.androidsunflowerdemo.data.AppDatabase
import com.sunilmishra.android.androidsunflowerdemo.data.GardenPlantingRepository
import com.sunilmishra.android.androidsunflowerdemo.data.PlantRepository
import com.sunilmishra.android.androidsunflowerdemo.viewmodels.GardenPlantingListViewModelFactory
import com.sunilmishra.android.androidsunflowerdemo.viewmodels.PlantDetailViewModelFactory
import com.sunilmishra.android.androidsunflowerdemo.viewmodels.PlantListViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
                AppDatabase.getInstance(context).gardenPlantingDao())
    }

    fun provideGardenPlantingListViewModelFactory(
        context: Context
    ): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
                getGardenPlantingRepository(context), plantId)
    }
}
