package com.audiobooks.podstar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.podstar.data.model.Podcast
import com.audiobooks.podstar.data.repo.PodcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    private val podcastRepository: PodcastRepository
) : ViewModel() {

    private var pageNo = 0

    private val _uiState = MutableStateFlow<PodcastListUiState>(PodcastListUiState.Loading)
    val uiState: StateFlow<PodcastListUiState> = _uiState.asStateFlow()

    init {

        loadMorePodcasts()

        viewModelScope.launch {
            podcastRepository.podcastsFlow.filterNot { it.isEmpty() }.collectLatest {
                _uiState.value = PodcastListUiState.Content(
                    isLoadingNextPage = false,
                    podcasts = it
                )
            }
        }
    }

    fun loadMorePodcasts() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    if (it is PodcastListUiState.Content) {
                        it.copy(isLoadingNextPage = true)
                    } else {
                        PodcastListUiState.Loading
                    }
                }
                podcastRepository.loadPage(++pageNo)
            } catch (e: Exception) {
                PodcastListUiState.Error
            }
        }
    }
}

sealed interface PodcastListUiState {
    data class Content(
        val isLoadingNextPage: Boolean,
        val podcasts: List<Podcast>
    ) : PodcastListUiState

    data object Error : PodcastListUiState
    data object Loading : PodcastListUiState
}