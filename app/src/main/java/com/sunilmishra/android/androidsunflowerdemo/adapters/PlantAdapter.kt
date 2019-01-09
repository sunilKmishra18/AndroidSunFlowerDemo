package com.sunilmishra.android.androidsunflowerdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sunilmishra.android.androidsunflowerdemo.PlantListFragment
import com.sunilmishra.android.androidsunflowerdemo.PlantListFragmentDirections
import com.sunilmishra.android.androidsunflowerdemo.data.Plant
import com.sunilmishra.android.androidsunflowerdemo.databinding.ListItemPlantBinding

/**
 * Adapter for the [RecyclerView] in [PlantListFragment].
 */
class PlantAdapter : ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        holder.apply {
            bind(createOnClickListener(plant.plantId), plant)
            itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(plantId: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment(plantId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Plant) {
            binding.apply {
                clickListener = listener
                plant = item
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {

    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}