package com.evoteckgeospatialconsult.features.classroom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classroom_progress")
data class ClassroomProgressEntity(
    @PrimaryKey val courseId: String,
    val lastAccessLessonId: String?,
    val progressPercentage: Int,
    val lastAccessTimestamp: Long
)