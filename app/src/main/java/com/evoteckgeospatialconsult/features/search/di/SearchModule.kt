package com.evoteckgeospatialconsult.features.search.di

import com.evoteckgeospatialconsult.features.search.data.local.SearchDao
import com.evoteckgeospatialconsult.features.search.data.remote.SearchApi
import com.evoteckgeospatialconsult.features.search.data.repository.SearchRepositoryImpl
import com.evoteckgeospatialconsult.features.search.domain.SearchRepository
import com.evoteckgeospatialconsult.features.search.domain.usecases.SearchCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchApi,
        dao: SearchDao
    ): SearchRepository {
        return SearchRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideSearchCoursesUseCase(
        repository: SearchRepository
    ): SearchCoursesUseCase {
        return SearchCoursesUseCase(repository)
    }
}