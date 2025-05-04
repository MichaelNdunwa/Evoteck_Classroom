package com.evoteckgeospatialconsult.features.player.data.models

data class PlayerResponse(
    val lessonId: String,
    val courseId: String,
    val title: String,
    val contentUrl: String,
    val duration: Long,
    val type: String, // video, quiz, assignment
    val isPreview: Boolean,
    val downloadUrl: String?,
    val nextLessonId: String?,
    val previousLessonId: String?
)
