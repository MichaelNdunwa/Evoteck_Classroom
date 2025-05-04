package com.evoteckgeospatialconsult.features.profile.data.repository

import com.evoteckgeospatialconsult.features.profile.data.local.ProfileDao
import com.evoteckgeospatialconsult.features.profile.data.models.ProfileResponse
import com.evoteckgeospatialconsult.features.profile.data.remote.ProfileApi
import com.evoteckgeospatialconsult.features.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val dao: ProfileDao
) : ProfileRepository {
    override suspend fun getUserProfile(): Flow<ProfileResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(name: String, bio: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfilePicture(imageUrl: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getEnrolledCourses(): Flow<List<ProfileResponse.EnrolledCourse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeachingCourses(): Flow<List<ProfileResponse.TeachingCourse>> {
        TODO("Not yet implemented")
    }
}