package com.evoteckgeospatialconsult.features.classroom.di

import com.evoteckgeospatialconsult.features.classroom.data.local.ClassroomDao
import com.evoteckgeospatialconsult.features.classroom.data.remote.ClassroomApi
import com.evoteckgeospatialconsult.features.classroom.data.repository.ClassroomRepositoryImpl
import com.evoteckgeospatialconsult.features.classroom.domain.ClassroomRepository
import com.evoteckgeospatialconsult.features.classroom.domain.usecases.GetEnrolledCourseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassroomModule {

    @Provides
    @Singleton
    fun provideClassroomRepository(
        api: ClassroomApi,
        dao: ClassroomDao
    ): ClassroomRepository {
        return ClassroomRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetEnrolledCourseUseCase(
        repository: ClassroomRepository
    ): GetEnrolledCourseUseCase {
        return GetEnrolledCourseUseCase(repository)
    }
}