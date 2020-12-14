package com.idevdroidapps.bookster.ui.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class VolumeLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<VolumeLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: VolumeLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): VolumeLoadStateViewHolder {
        return VolumeLoadStateViewHolder.create(parent, retry)
    }
}