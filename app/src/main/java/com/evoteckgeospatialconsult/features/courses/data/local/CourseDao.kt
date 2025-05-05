package com.evoteckgeospatialconsult.features.courses.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE category = :category")
    fun getCoursesByCategory(category: String): Flow<List<CourseEntity>>
}