package com.idevdroidapps.bookster.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idevdroidapps.bookster.data.models.Volume
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModel

/**
 * Adapter for the list of volumes.
 */
class VolumesAdapter(private val viewModel: SharedViewModel?) :
    PagingDataAdapter<Volume, RecyclerView.ViewHolder>(VOLUME_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VolumeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as VolumeViewHolder).bind(repoItem, viewModel)
        }
    }

    companion object {
        private val VOLUME_COMPARATOR = object : DiffUtil.ItemCallback<Volume>() {
            override fun areItemsTheSame(oldItem: Volume, newItem: Volume): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Volume, newItem: Volume): Boolean =
                oldItem.id == newItem.id
        }
    }
}