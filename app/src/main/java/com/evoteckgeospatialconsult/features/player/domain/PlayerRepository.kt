package com.evoteckgeospatialconsult.features.player.domain

import com.evoteckgeospatialconsult.features.player.data.local.PlayerProgressEntity
import com.evoteckgeospatialconsult.features.player.data.models.PlayerResponse
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun getLessonContent(lessonId: String): Flow<PlayerResponse>
    suspend fun updateLessonProgress(lessonId: String, progress: Int, position: Long)
    suspend fun markLessonCompleted(lessonId: String)
    suspend fun getDownloadUrl(lessonId: String): Flow<String>
    fun getLessonProgress(lessonId: String): Flow<PlayerProgressEntity?>
    fun getCourseProgress(courseId: String): Flow<List<PlayerProgressEntity>>
}