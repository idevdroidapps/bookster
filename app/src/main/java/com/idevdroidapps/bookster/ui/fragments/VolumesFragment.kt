package com.idevdroidapps.bookster.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.idevdroidapps.bookster.R
import com.idevdroidapps.bookster.data.models.Volume
import com.idevdroidapps.bookster.databinding.FragmentVolumesBinding
import com.idevdroidapps.bookster.ui.adapters.VolumeLoadStateAdapter
import com.idevdroidapps.bookster.ui.adapters.VolumesAdapter
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VolumesFragment : Fragment() {

    private lateinit var adapter: VolumesAdapter
    private val viewModel: SharedViewModel by activityViewModels()
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentVolumesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_volumes, container, false)

        val clickListener: (Volume) -> Unit = {
            viewModel.setCurrentVolume(it)
            this.findNavController().navigate(
                VolumesFragmentDirections.actionVolumesFragmentToDetailsFragment()
            )
        }

        initAdapter(binding, clickListener)

        binding.retryButton.setOnClickListener {
            this.findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.currentQuery.value?.let { query ->
                viewModel.searchVolumes(query).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    /**
     * Initializes the RecyclerView Adapter and LoadStateListener
     *
     * @param   binding The [FragmentVolumesBinding] received
     */
    private fun initAdapter(binding: FragmentVolumesBinding, clickListener: (Volume) -> Unit) {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerViewVolumes.addItemDecoration(decoration)

        adapter = VolumesAdapter(clickListener)
        binding.recyclerViewVolumes.adapter = adapter.withLoadStateHeaderAndFooter(
            header = VolumeLoadStateAdapter { adapter.retry() },
            footer = VolumeLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.recyclerViewVolumes.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    context,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}