package com.idevdroidapps.bookster.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idevdroidapps.bookster.data.repositories.VolumeRepository

class SharedViewModelFactory(
    private val volumeRepository: VolumeRepository
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(volumeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}