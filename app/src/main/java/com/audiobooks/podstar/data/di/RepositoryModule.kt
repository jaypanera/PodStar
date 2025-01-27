package com.audiobooks.podstar.data.di

import com.audiobooks.podstar.data.repo.DefaultPodcastRepository
import com.audiobooks.podstar.data.repo.PodcastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindPodcastRepository(repository: DefaultPodcastRepository): PodcastRepository
}