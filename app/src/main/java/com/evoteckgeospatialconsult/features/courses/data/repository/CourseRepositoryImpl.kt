package com.evoteckgeospatialconsult.features.courses.data.repository

import com.evoteckgeospatialconsult.features.courses.data.local.CourseDao
import com.evoteckgeospatialconsult.features.courses.data.models.CourseResponse
import com.evoteckgeospatialconsult.features.courses.data.remote.CourseApi
import com.evoteckgeospatialconsult.features.courses.domain.CourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseRepositoryImpl  @Inject constructor(
    private val api: CourseApi,
    private val dao: CourseDao
) : CourseRepository {
    override fun getAllCourses(): Flow<List<CourseResponse>> {
        TODO("Not yet implemented")
    }

    override fun getCourseById(courseId: String): Flow<CourseResponse> {
        TODO("Not yet implemented")
    }

    override fun getCourseByCategory(category: String): Flow<List<CourseResponse>> {
        TODO("Not yet implemented")
    }

    override fun searchCourses(query: String): Flow<List<CourseResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun enrollInCourse(courseId: String) {
        TODO("Not yet implemented")
    }
}