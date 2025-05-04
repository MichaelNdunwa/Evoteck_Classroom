package com.evoteckgeospatialconsult.features.courses.data.remote

import com.evoteckgeospatialconsult.features.courses.data.models.CourseResponse
import kotlinx.coroutines.flow.Flow

interface CourseApi {
    suspend fun getAllCourses(): Flow<List<CourseResponse>>
    suspend fun getCourseById(courseId: String): Flow<List<CourseResponse>>
    suspend fun getCoursesByCategory(category: String): Flow<List<CourseResponse>>
    suspend fun searchCourses(query: String): Flow<List<CourseResponse>>
}