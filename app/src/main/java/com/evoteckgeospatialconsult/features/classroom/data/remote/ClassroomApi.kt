package com.evoteckgeospatialconsult.features.classroom.data.remote

import com.evoteckgeospatialconsult.features.classroom.data.models.ClassroomResponse
import kotlinx.coroutines.flow.Flow

interface ClassroomApi {
    suspend fun getEnrolledCourses(): Flow<List<ClassroomResponse>>
    suspend fun getCourseProgress(courseId: String): Flow<ClassroomResponse>
}