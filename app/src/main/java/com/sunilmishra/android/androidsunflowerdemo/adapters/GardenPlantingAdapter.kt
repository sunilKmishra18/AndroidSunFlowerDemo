package com.sunilmishra.android.androidsunflowerdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sunilmishra.android.androidsunflowerdemo.GardenFragmentDirections
import com.sunilmishra.android.androidsunflowerdemo.R
import com.sunilmishra.android.androidsunflowerdemo.data.PlantAndGardenPlantings
import com.sunilmishra.android.androidsunflowerdemo.databinding.ListItemGardenPlantingBinding
import com.sunilmishra.android.androidsunflowerdemo.viewmodels.PlantAndGardenPlantingsViewModel

class GardenPlantingAdapter :
    ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(GardenPlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_garden_planting, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { plantings ->
            with(holder) {
                itemView.tag = plantings
                bind(createOnClickListener(plantings.plant.plantId), plantings)
            }
        }
    }

    private fun createOnClickListener(plantId: String): View.OnClickListener {
        return View.OnClickListener {
                val direction =
                        GardenFragmentDirections.ActionGardenFragmentToPlantDetailFragment(plantId)
                it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemGardenPlantingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, plantings: PlantAndGardenPlantings) {
            with(binding) {
                clickListener = listener
                viewModel = PlantAndGardenPlantingsViewModel(
                    itemView.context,
                    plantings
                )
                executePendingBindings()
            }
        }
    }
}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {

    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant == newItem.plant
    }
}