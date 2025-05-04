package com.evoteckgeospatialconsult.features.classroom.data.repository

import com.evoteckgeospatialconsult.features.classroom.data.local.ClassroomDao
import com.evoteckgeospatialconsult.features.classroom.data.models.ClassroomResponse
import com.evoteckgeospatialconsult.features.classroom.data.remote.ClassroomApi
import com.evoteckgeospatialconsult.features.classroom.domain.ClassroomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(
    private val api: ClassroomApi,
    private val dao: ClassroomDao
) : ClassroomRepository{
    override fun getEnrolledCourses(): Flow<List<ClassroomResponse>> {
        TODO("Not yet implemented")
    }

    override fun getCourseProgress(courseId: String): Flow<ClassroomResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourseProgress(courseId: String, progress: Int) {
        TODO("Not yet implemented")
    }

}