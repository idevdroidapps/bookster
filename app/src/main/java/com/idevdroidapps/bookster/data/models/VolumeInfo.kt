package com.idevdroidapps.bookster.data.models

import com.google.gson.annotations.SerializedName

/**
 * Immutable model class.
 * Objects of this type are received from the GoogleBooks API, therefore all the fields are annotated
 * with the serialized name.
 */
data class VolumeInfo(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("subtitle") val subtitle: String,
    @field:SerializedName("authors") val authors: List<String>,
    @field:SerializedName("publishedDate") val publishedDate: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("averageRating") val averageRating: String,
    @field:SerializedName("imageLinks") val imageLinks: ImageLinks
)