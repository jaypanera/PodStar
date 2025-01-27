package com.audiobooks.podstar.core.network.datasource

import com.audiobooks.podstar.data.model.PodcastsResposneDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodStarApiService {
    @GET("api/v2/best_podcasts")
    suspend fun getPodcasts(
        @Query("page") page: Int,
    ) : PodcastsResposneDto
}