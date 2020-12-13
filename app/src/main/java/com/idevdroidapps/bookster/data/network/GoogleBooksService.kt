package com.idevdroidapps.bookster.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Google Books API communication setup via Retrofit.
 */
interface GoogleBooksService {

    /**
     * Get volumes
     *
     * @param   query       The keyword term to search
     * @param   startIndex  The start index of the paginated call
     * @param   maxResults  The max number of results to return
     */
    @GET("v1/volumes")
    suspend fun searchVolumes(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int
    ): VolumeSearchResponse

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/"

        fun create(): GoogleBooksService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GoogleBooksService::class.java)
        }
    }
}