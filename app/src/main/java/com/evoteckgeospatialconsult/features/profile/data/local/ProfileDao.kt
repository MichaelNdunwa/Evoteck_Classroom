package com.evoteckgeospatialconsult.features.profile.data.local

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProfileDao {
    @Query("SELECT * FROM user_profile")
    fun getUserProfile(): Flow<UserProfileEntity?>
}