package com.audiobooks.podstar.data.repo

import com.audiobooks.podstar.data.model.Podcast
import kotlinx.coroutines.flow.Flow

interface PodcastRepository {
    val podcastsFlow: Flow<List<Podcast>>
    fun getPodcast(id: String) : Podcast
    suspend fun toggleFavorite(id: String)
    suspend fun loadPage(page: Int)
}