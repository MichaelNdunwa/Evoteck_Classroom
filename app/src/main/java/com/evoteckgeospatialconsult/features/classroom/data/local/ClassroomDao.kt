package com.evoteckgeospatialconsult.features.classroom.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassroomDao {
    @Query("SELECT * FROM classroom_progress")
    fun getClassroomProgress(): Flow<List<ClassroomProgressEntity>>
}