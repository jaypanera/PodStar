package com.audiobooks.podstar.core.network.di

import com.audiobooks.podstar.core.network.datasource.PodStarApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://listen-api-test.listennotes.com"

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit() : Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        })
                        .build()
                )
                .baseUrl(BASE_URL)
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): PodStarApiService {
            return retrofit.create(PodStarApiService::class.java)
        }
    }
}