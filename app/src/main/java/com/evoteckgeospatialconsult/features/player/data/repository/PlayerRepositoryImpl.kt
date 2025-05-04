package com.evoteckgeospatialconsult.features.player.data.repository

import com.evoteckgeospatialconsult.features.player.data.local.PlayerDao
import com.evoteckgeospatialconsult.features.player.data.local.PlayerProgressEntity
import com.evoteckgeospatialconsult.features.player.data.models.PlayerResponse
import com.evoteckgeospatialconsult.features.player.data.remote.PlayerApi
import com.evoteckgeospatialconsult.features.player.domain.PlayerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val api: PlayerApi,
    private val dao: PlayerDao
) : PlayerRepository {
    override fun getLessonContent(lessonId: String): Flow<PlayerResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLessonProgress(
        lessonId: String,
        progress: Int,
        position: Long
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun markLessonCompleted(lessonId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getDownloadUrl(lessonId: String): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getLessonProgress(lessonId: String): Flow<PlayerProgressEntity?> {
        TODO("Not yet implemented")
    }

    override fun getCourseProgress(courseId: String): Flow<List<PlayerProgressEntity>> {
        TODO("Not yet implemented")
    }
}