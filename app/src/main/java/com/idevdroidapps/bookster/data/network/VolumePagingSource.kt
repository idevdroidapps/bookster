package com.idevdroidapps.bookster.data.network

import androidx.paging.PagingSource
import com.idevdroidapps.bookster.data.models.Volume
import retrofit2.HttpException
import java.io.IOException

private const val BOOKS_STARTING_PAGE_INDEX = 0

class VolumePagingSource(
    private val service: GoogleBooksService,
    private val query: String
) : PagingSource<Int, Volume>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Volume> {
        val position = params.key ?: BOOKS_STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val response = service.searchVolumes(apiQuery, position, params.pageSize)
            val volumes = response.items
            LoadResult.Page(
                data = volumes,
                prevKey = if (position == BOOKS_STARTING_PAGE_INDEX) null else position,
                nextKey = if (volumes.isEmpty()) null else (position + params.pageSize)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}