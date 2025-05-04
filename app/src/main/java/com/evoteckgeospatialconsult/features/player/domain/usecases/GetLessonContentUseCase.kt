package com.evoteckgeospatialconsult.features.player.domain.usecases

import com.evoteckgeospatialconsult.features.player.data.models.PlayerResponse
import com.evoteckgeospatialconsult.features.player.domain.PlayerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLessonContentUseCase @Inject constructor(
    private val repository: PlayerRepository
){
    operator fun invoke(lessonId: String): Flow<PlayerResponse> {
        return repository.getLessonContent(lessonId)
    }
}