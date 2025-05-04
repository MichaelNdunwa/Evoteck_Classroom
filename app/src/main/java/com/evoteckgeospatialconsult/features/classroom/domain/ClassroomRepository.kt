package com.evoteckgeospatialconsult.features.classroom.domain

import com.evoteckgeospatialconsult.features.classroom.data.models.ClassroomResponse
import kotlinx.coroutines.flow.Flow

interface ClassroomRepository {
    fun getEnrolledCourses(): Flow<List<ClassroomResponse>>
    fun getCourseProgress(courseId: String): Flow<ClassroomResponse>
    suspend fun updateCourseProgress(courseId: String, progress: Int)
}