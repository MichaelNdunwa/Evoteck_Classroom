package com.evoteckgeospatialconsult.features.profile.data.remote

import com.evoteckgeospatialconsult.features.profile.data.models.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileApi {
    suspend fun getUserProfile(): Flow<ProfileResponse>
    suspend fun updateProfile(name: String, bio: String?)
    suspend fun updateProfilePicture(imageUrl: String)
    suspend fun getEnrolledCourses(): Flow<List<ProfileResponse.EnrolledCourse>>
    suspend fun getTeachingCourses(): Flow<List<ProfileResponse.TeachingCourse>>
}