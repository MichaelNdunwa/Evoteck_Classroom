package com.evoteckgeospatialconsult.features.search.domain

import com.evoteckgeospatialconsult.features.search.data.local.SearchHistoryEntity
import com.evoteckgeospatialconsult.features.search.data.models.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCourse(query: String): Flow<List<SearchResponse>>
    fun getSearchSuggestions(query: String): Flow<List<String>>
    fun getPopularSearches(): Flow<List<String>>
    fun getRecentSearches(): Flow<List<SearchHistoryEntity>>
    suspend fun saveSearchQuery(query: String)
}