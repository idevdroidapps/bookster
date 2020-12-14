package com.idevdroidapps.bookster.ui.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.idevdroidapps.bookster.R

@BindingAdapter("authors")
fun TextView.authors(authors: List<String>?) {
    authors?.let {
        text = authors.joinToString(separator = ", ") {
            it.trim()
        }
    }
}

@BindingAdapter("thumbnailLarge")
fun ImageView.thumbnailLarge(thumbnailUrl: String?) {
    thumbnailUrl?.let { url ->
        val options = RequestOptions()
            .override(128, 128)
            .placeholder(R.drawable.ic_placeholder_book_96)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .fitCenter()
        try {
            Glide
                .with(this.context)
                .load(url)
                .apply(options)
                .into(this)
        } catch (e: Exception) {
            Log.e("Glide", "Large Thumbnail Failed in Glide")
        }
    }
}

@BindingAdapter("thumbnailSmall")
fun ImageView.thumbnailSmall(thumbnailUrl: String?) {
    thumbnailUrl?.let { url ->
        val options = RequestOptions()
            .override(48, 48)
            .placeholder(R.drawable.ic_placeholder_book_48)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .fitCenter()
        try {
            Glide
                .with(this.context)
                .load(url)
                .apply(options)
                .into(this)
        } catch (e: Exception) {
            Log.e("Glide", "Small Thumbnail Failed in Glide")
        }
    }
}