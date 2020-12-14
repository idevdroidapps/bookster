package com.idevdroidapps.bookster.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idevdroidapps.bookster.data.models.Volume

/**
 * [PagingDataAdapter] for the list of [Volume].
 */
class VolumesAdapter(private val clickListener: (Volume) -> Unit) :
    PagingDataAdapter<Volume, RecyclerView.ViewHolder>(VOLUME_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VolumeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as VolumeViewHolder).bind(item, clickListener)
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