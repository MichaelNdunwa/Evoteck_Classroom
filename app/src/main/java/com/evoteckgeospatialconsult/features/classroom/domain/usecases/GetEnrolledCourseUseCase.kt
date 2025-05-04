package com.evoteckgeospatialconsult.features.classroom.domain.usecases

import com.evoteckgeospatialconsult.features.classroom.data.models.ClassroomResponse
import com.evoteckgeospatialconsult.features.classroom.domain.ClassroomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEnrolledCourseUseCase @Inject constructor(
    private val repository: ClassroomRepository
){
    operator fun invoke(): Flow<List<ClassroomResponse>> {
        return repository.getEnrolledCourses()
    }
}