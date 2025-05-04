package com.evoteckgeospatialconsult.features.classroom.data.models

data class ClassroomResponse(
    val courseId: String,
    val title: String,
    val description: String,
    val progress: Int,
    val lastAccessedLessonId: String?,
    val lastAccessedTimestamp: Long
)