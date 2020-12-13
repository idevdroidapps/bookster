package com.idevdroidapps.bookster.data.network

import com.google.gson.annotations.SerializedName
import com.idevdroidapps.bookster.data.models.Volume

data class VolumeSearchResponse (
    @SerializedName("totalItems") val total: Int = 0,
    @SerializedName("items") val items: List<Volume> = emptyList(),
)