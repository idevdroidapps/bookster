package com.idevdroidapps.bookster.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.databinding.ListItemLoadStateFooterBinding

class VolumeLoadStateViewHolder(
    private val binding: ListItemLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): VolumeLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_load_state_footer, parent, false)
            val binding = ListItemLoadStateFooterBinding.bind(view)
            return VolumeLoadStateViewHolder(binding, retry)
        }
    }
}