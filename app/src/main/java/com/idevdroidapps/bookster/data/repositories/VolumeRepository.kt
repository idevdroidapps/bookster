package com.idevdroidapps.bookster.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.idevdroidapps.bookster.data.models.Volume
import com.idevdroidapps.bookster.data.network.GoogleBooksService
import com.idevdroidapps.bookster.data.network.VolumePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class VolumeRepository(private val service: GoogleBooksService) {

    /**
     * Search for volumes match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getSearchResultStream(query: String): Flow<PagingData<Volume>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { VolumePagingSource(service, query) }
        ).flow
    }

    companion object {

        private const val NETWORK_PAGE_SIZE = 20

        // For Singleton instantiation
        @Volatile
        private var instance: VolumeRepository? = null

        fun getInstance(service: GoogleBooksService) =
            instance ?: synchronized(this) {
                instance ?: VolumeRepository(service).also { instance = it }
            }
    }

}