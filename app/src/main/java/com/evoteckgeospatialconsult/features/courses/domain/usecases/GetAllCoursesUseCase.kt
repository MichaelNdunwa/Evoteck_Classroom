package com.evoteckgeospatialconsult.features.courses.domain.usecases

import com.evoteckgeospatialconsult.features.courses.domain.CourseRepository
import com.evoteckgeospatialconsult.features.courses.data.models.CourseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllCoursesUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    operator fun invoke(): Flow<List<CourseResponse>> {
        return repository.getAllCourses()
    }
}