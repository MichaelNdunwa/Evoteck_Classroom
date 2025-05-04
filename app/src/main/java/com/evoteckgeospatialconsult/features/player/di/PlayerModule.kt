package com.evoteckgeospatialconsult.features.player.di

import com.evoteckgeospatialconsult.features.player.data.remote.PlayerApi
import com.evoteckgeospatialconsult.features.player.data.local.PlayerDao
import com.evoteckgeospatialconsult.features.player.data.repository.PlayerRepositoryImpl
import com.evoteckgeospatialconsult.features.player.domain.PlayerRepository
import com.evoteckgeospatialconsult.features.player.domain.usecases.GetLessonContentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    @Singleton
    fun providePlayerRepository(
        api: PlayerApi,
        dao: PlayerDao
    ): PlayerRepository {
        return PlayerRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetLessonContentUseCase(
        repository: PlayerRepository
    ): GetLessonContentUseCase {
        return GetLessonContentUseCase(repository)
    }
}