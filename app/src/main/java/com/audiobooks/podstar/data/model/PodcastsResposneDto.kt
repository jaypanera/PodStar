package com.audiobooks.podstar.data.model

import com.google.gson.annotations.SerializedName

data class PodcastsResposneDto(
    @SerializedName("podcasts")
    val podcasts: List<PodcastDto>,
)

data class PodcastDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("description")
    val description: String,
)