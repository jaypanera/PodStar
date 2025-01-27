package com.audiobooks.podstar.core.network.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("podStarPrefs")

@Singleton
class LocalDataStore @Inject constructor(@ApplicationContext context: Context) {

    private val localDataStore = context.dataStore
    private val favoritePodcastIdsKey = stringSetPreferencesKey("favoriteIds")

    val favoritePodcastIds: Flow<List<String>> = localDataStore.data.map { preferences ->
        preferences[favoritePodcastIdsKey]?.toList() ?: emptyList()
    }

    suspend fun addToFavorite(id: String) {
        localDataStore.edit { preferences ->
            preferences[favoritePodcastIdsKey] = (preferences[favoritePodcastIdsKey] ?: emptySet()) + id
        }
    }

    suspend fun removeFromFavorite(id: String) {
        localDataStore.edit { preferences ->
            preferences[favoritePodcastIdsKey] = (preferences[favoritePodcastIdsKey] ?: emptySet()) - id
        }
    }

}