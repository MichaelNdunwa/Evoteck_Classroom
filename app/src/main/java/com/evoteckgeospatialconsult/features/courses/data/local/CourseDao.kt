package com.evoteckgeospatialconsult.features.courses.data.local

import androidx.room.Dao
import androidx.room.Query
import com.evoteckgeospatialconsult.features.classroom.data.local.ClassroomProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM classroom_progress")
    fun getClassroomProgress(): Flow<List<ClassroomProgressEntity>>

    @Query("SELECT * FROM courses WHERE category = :category")
    fun getCoursesByCategory(category: String): Flow<List<CourseEntity>>
}