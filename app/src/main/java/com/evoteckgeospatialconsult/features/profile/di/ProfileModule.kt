package com.evoteckgeospatialconsult.features.profile.di

import com.evoteckgeospatialconsult.features.profile.data.remote.ProfileApi
import com.evoteckgeospatialconsult.features.profile.data.local.ProfileDao
import com.evoteckgeospatialconsult.features.profile.data.repository.ProfileRepositoryImpl
import com.evoteckgeospatialconsult.features.profile.domain.ProfileRepository
import com.evoteckgeospatialconsult.features.profile.domain.usecases.GetUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileRepository(
        api: ProfileApi,
        dao: ProfileDao
    ): ProfileRepository {
        return ProfileRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetUserProfileUseCase(
        repository: ProfileRepository
    ): GetUserProfileUseCase {
        return GetUserProfileUseCase(repository)
    }
}