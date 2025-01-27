package com.audiobooks.podstar.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.audiobooks.podstar.data.model.Podcast
import com.audiobooks.podstar.data.repo.PodcastRepository
import com.audiobooks.podstar.ui.PodcastDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val podcastRepository: PodcastRepository,
): ViewModel() {

    private val podcastId = savedStateHandle.toRoute<PodcastDetailsRoute>().podcastId

    val podcast = podcastRepository.getPodcast(podcastId)

    private val _uiState = MutableStateFlow(PodcastDetailsUiState(podcast))
    val uiState = _uiState.asStateFlow()

    fun toggleFavorite() {
        viewModelScope.launch {
            podcastRepository.toggleFavorite(podcastId)
            _uiState.update {
                it.copy(
                    podcast = podcastRepository.getPodcast(podcastId)
                )
            }
        }
    }

}

data class PodcastDetailsUiState(
    val podcast: Podcast,
)