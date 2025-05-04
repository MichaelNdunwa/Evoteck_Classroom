package com.evoteckgeospatialconsult.features.search.data.remote

import com.evoteckgeospatialconsult.features.search.data.models.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchApi {
    suspend fun searchCourse(query: String): Flow<List<SearchResponse>>
    suspend fun getSearchSuggestions(query: String): Flow<List<String>>
    suspend fun getPopularSearches(): Flow<List<String>>
}