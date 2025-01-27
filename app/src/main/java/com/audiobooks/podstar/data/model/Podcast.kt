package com.audiobooks.podstar.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Podcast(
    val id: String,
    val image: String,
    val title: String,
    val publisher: String,
    val description: String,
    val isFavorite: Boolean,
) : Parcelable
