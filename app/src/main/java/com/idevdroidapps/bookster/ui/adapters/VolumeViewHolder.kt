package com.idevdroidapps.bookster.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.data.models.AccessInfo
import com.idevdroidapps.bookster.data.models.ImageLinks
import com.idevdroidapps.bookster.data.models.Volume
import com.idevdroidapps.bookster.data.models.VolumeInfo
import com.idevdroidapps.bookster.databinding.ListItemVolumeBinding

/**
 * View Holder for a [Volume] RecyclerView list item.
 */
class VolumeViewHolder(private val binding: ListItemVolumeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(volume: Volume?, clickListener: (Volume) -> Unit) {
        if (volume == null) {
            val resources = itemView.resources
            val textUnknown = resources.getString(R.string.unknown)
            val item = Volume(
                "0",
                VolumeInfo(
                    textUnknown, textUnknown, emptyList(), textUnknown, textUnknown,
                    textUnknown, ImageLinks(textUnknown, textUnknown)
                ),
                AccessInfo(textUnknown)
            )
            binding.volume = item
        } else {
            binding.volume = volume
        }
        binding.root.setOnClickListener{
            binding.volume?.apply {
                clickListener(this)
            }
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): VolumeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemVolumeBinding.inflate(layoutInflater, parent, false)
            return VolumeViewHolder(binding)
        }
    }
}