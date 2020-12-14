package com.idevdroidapps.bookster.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.idevdroidapps.bookster.data.models.Volume
import com.idevdroidapps.bookster.data.repositories.VolumeRepository
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for MainActivity and Fragments.
 * The ViewModel works with the [VolumeRepository] to get the data.
 *
 * @param   volumeRepository    The injected [VolumeRepository]
 */
class SharedViewModel(private val volumeRepository: VolumeRepository) : ViewModel() {

    val currentQuery: LiveData<String> get() = mCurrentQuery
    val currentVolume: LiveData<Volume> get() = mCurrentVolume
    private var mPreviousQuery: String? = null
    private var mCurrentVolume = MutableLiveData<Volume>()
    private var mCurrentQuery = MutableLiveData<String>()
    private var mCurrentSearchResult: Flow<PagingData<Volume>>? = null

    /**
     * Queries the [VolumeRepository] for matching search results
     *
     * @param   queryString  The query term to search, as a [String]
     */
    fun searchVolumes(queryString: String): Flow<PagingData<Volume>> {
        val lastResult = mCurrentSearchResult
        if (queryString == mPreviousQuery && lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Volume>> =
            volumeRepository.getSearchResultStream(queryString)
                .cachedIn(viewModelScope)
        mPreviousQuery = queryString
        mCurrentSearchResult = newResult
        return newResult
    }

    /**
     * Sets the value for current repo LiveData
     *
     * @param   volume  The currently selected [Volume]
     */
    fun setCurrentVolume(volume: Volume) {
        mCurrentVolume.value = volume
    }

    /**
     * Sets the value for current query LiveData
     *
     * @param   queryString  The query term to search, as a [String]
     */
    fun setCurrentQuery(queryString: String) {
        mCurrentQuery.value = queryString
    }

}