package com.idevdroidapps.bookster.data.models

import com.google.gson.annotations.SerializedName

/**
 * Immutable model class.
 * Objects of this type are received from the GoogleBooks API, therefore all the fields are annotated
 * with the serialized name.
 */
data class ImageLinks (
    @field:SerializedName("smallThumbnail") val smallThumbnail: String,
    @field:SerializedName("thumbnail") val thumbnail: String,
)