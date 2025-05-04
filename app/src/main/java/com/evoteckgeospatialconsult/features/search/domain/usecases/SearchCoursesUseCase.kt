package com.evoteckgeospatialconsult.features.search.domain.usecases

import com.evoteckgeospatialconsult.features.search.data.models.SearchResponse
import com.evoteckgeospatialconsult.features.search.domain.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCoursesUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<List<SearchResponse>> {
        return repository.searchCourse(query)
    }
}