package com.evoteckgeospatialconsult.features.courses.di

import com.evoteckgeospatialconsult.features.courses.data.local.CourseDao
import com.evoteckgeospatialconsult.features.courses.data.remote.CourseApi
import com.evoteckgeospatialconsult.features.courses.data.repository.CourseRepositoryImpl
import com.evoteckgeospatialconsult.features.courses.domain.CourseRepository
import com.evoteckgeospatialconsult.features.courses.domain.usecases.GetAllCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CourseModule {

    @Provides
    @Singleton
    fun provideCourseRepository(
        api: CourseApi,
        dao: CourseDao
    ): CourseRepository {
        return CourseRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetAllCoursesUseCase(
        repository: CourseRepository
    ): GetAllCoursesUseCase {
        return GetAllCoursesUseCase(repository)
    }
}