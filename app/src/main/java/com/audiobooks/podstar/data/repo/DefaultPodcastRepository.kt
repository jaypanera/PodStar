package com.audiobooks.podstar.data.repo

import com.audiobooks.podstar.core.network.datasource.LocalDataStore
import com.audiobooks.podstar.core.network.datasource.PodStarApiService
import com.audiobooks.podstar.data.model.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class DefaultPodcastRepository @Inject constructor(
    private val podcastApiService: PodStarApiService,
    private val localDataStore: LocalDataStore,
) : PodcastRepository {

    private val favoriteIdsFlow = localDataStore.favoritePodcastIds

    override val podcastsFlow = MutableStateFlow<List<Podcast>>(emptyList())

    override suspend fun toggleFavorite(id: String) {
        val podcast = podcastsFlow.value.first { it.id == id }
        if (podcast.isFavorite) {
            localDataStore.removeFromFavorite(id)
        } else {
            localDataStore.addToFavorite(id)
        }
        val favoriteIds = favoriteIdsFlow.first()
        podcastsFlow.tryEmit(
            podcastsFlow.value.map {
                it.copy(isFavorite = favoriteIds.contains(it.id))
            }
        )
    }

    override fun getPodcast(id: String): Podcast {
        return podcastsFlow.value.first { it.id == id }
    }

    override suspend fun loadPage(page: Int) {
        if (page == 1) {
            podcastsFlow.update { emptyList() }
        }
        val favoriteIds = favoriteIdsFlow.first()
        val podcasts = podcastApiService.getPodcasts(page).podcasts.map {
            Podcast(
                id = it.id,
                image = it.image,
                title = it.title,
                publisher = it.publisher,
                description = it.description,
                isFavorite = favoriteIds.contains(it.id),
            )
        }
        podcastsFlow.tryEmit(podcastsFlow.value + podcasts)
    }

}