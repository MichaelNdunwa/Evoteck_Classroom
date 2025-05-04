package com.evoteckgeospatialconsult.features.courses.domain

import com.evoteckgeospatialconsult.features.courses.data.models.CourseResponse
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<CourseResponse>>
    fun getCourseById(courseId: String): Flow<CourseResponse>
    fun getCourseByCategory(category: String): Flow<List<CourseResponse>>
    fun searchCourses(query: String): Flow<List<CourseResponse>>
    suspend fun enrollInCourse(courseId: String)
}