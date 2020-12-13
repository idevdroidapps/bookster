package com.idevdroidapps.bookster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idevdroidapps.bookster.data.network.GoogleBooksService
import com.idevdroidapps.bookster.data.repositories.VolumeRepository
import com.idevdroidapps.bookster.ui.viewmodels.SharedViewModelFactory

object Injection {

    /**
     * Creates an instance of [VolumeRepository]
     */
    private fun provideVolumeRepo(): VolumeRepository {
        return VolumeRepository.getInstance(GoogleBooksService.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideMainActViewModelFactory(): ViewModelProvider.Factory {
        return SharedViewModelFactory(provideVolumeRepo())
    }

}