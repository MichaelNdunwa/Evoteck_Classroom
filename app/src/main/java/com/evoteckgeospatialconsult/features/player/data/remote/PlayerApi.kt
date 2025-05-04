package com.evoteckgeospatialconsult.features.player.data.remote

import com.evoteckgeospatialconsult.features.player.data.models.PlayerResponse
import kotlinx.coroutines.flow.Flow

interface PlayerApi {
    suspend fun getLessonContent(lessonId: String): Flow<PlayerResponse>
    suspend fun updateLessonProgress(lessonId: String, progress: Int, position: Long)
    suspend fun markLessonCompleted(lessonId: String)
    suspend fun getDownloadUrl(lessonId: String): Flow<String>
}