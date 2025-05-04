package com.evoteckgeospatialconsult.features.profile.domain.usecases

import com.evoteckgeospatialconsult.features.profile.domain.ProfileRepository
import com.evoteckgeospatialconsult.features.profile.data.models.ProfileResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
){
    suspend operator fun invoke(): Flow<ProfileResponse> {
        return repository.getUserProfile()
    }
}