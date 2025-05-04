package com.evoteckgeospatialconsult.features.search.data.repository

import com.evoteckgeospatialconsult.features.search.data.local.SearchDao
import com.evoteckgeospatialconsult.features.search.data.local.SearchHistoryEntity
import com.evoteckgeospatialconsult.features.search.data.models.SearchResponse
import com.evoteckgeospatialconsult.features.search.data.remote.SearchApi
import com.evoteckgeospatialconsult.features.search.domain.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi,
    private val dao: SearchDao
) : SearchRepository {
    override fun searchCourse(query: String): Flow<List<SearchResponse>> {
        TODO("Not yet implemented")
    }

    override fun getSearchSuggestions(query: String): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getPopularSearches(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getRecentSearches(): Flow<List<SearchHistoryEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSearchQuery(query: String) {
        TODO("Not yet implemented")
    }
}