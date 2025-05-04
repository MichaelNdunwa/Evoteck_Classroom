package com.evoteckgeospatialconsult.features.courses.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val instructor: String,
    val price: Double,
    val rating: Float,
    val totalStudents: Int,
    val category: String,
    val thumbnailUrl: String,
    val duration: String
)