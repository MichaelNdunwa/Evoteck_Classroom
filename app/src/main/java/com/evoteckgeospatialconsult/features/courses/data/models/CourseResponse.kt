package com.evoteckgeospatialconsult.features.courses.data.models

data class CourseResponse(
    val id: String,
    val title: String,
    val description: String,
    val instructor: String,
    val price: Double,
    val rating: Float,
    val totalStudents: Int,
    val category: String,
    val thumbnailUrl: String,
    val duration: String,
    val curriculum: List<LessonResponse>,
    val requirements: List<String>,
    val whatYouWillLearn: List<String>
)

data class LessonResponse(
    val id: String,
    val title: String,
    val duration: String,
    val type: String, // video, quiz, assignment
    val isPreview: Boolean
)