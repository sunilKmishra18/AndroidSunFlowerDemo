package com.sunilmishra.android.androidsunflowerdemo.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.sunilmishra.android.androidsunflowerdemo.R
import com.sunilmishra.android.androidsunflowerdemo.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.Locale

class PlantAndGardenPlantingsViewModel(
    context: Context,
    plantings: PlantAndGardenPlantings
) : ViewModel() {

    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]

    private val dateFormat by lazy { SimpleDateFormat("MMM d, yyyy", Locale.US) }
    private val plantDateString by lazy { dateFormat.format(gardenPlanting.plantDate.time) }
    private val waterDateString by lazy { dateFormat.format(gardenPlanting.lastWateringDate.time) }
    private val wateringPrefix by lazy {
        context.getString(
            R.string.watering_next_prefix,
            waterDateString
        )
    }
    private val wateringSuffix by lazy {
        context.resources.getQuantityString(
            R.plurals.watering_next_suffix,
            plant.wateringInterval, plant.wateringInterval
        )
    }

    val imageUrl = ObservableField<String>(plant.imageUrl)

    val plantDate = ObservableField<String>(
        context.getString(
            R.string.planted_date, plant.name,
            plantDateString
        )
    )

    val waterDate = ObservableField<String>("$wateringPrefix - $wateringSuffix")
}