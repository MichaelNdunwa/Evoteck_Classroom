package com.evoteckgeospatialconsult.features.profile.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val userId: String,
    val name: String,
    val email: String,
    val profilePictureUrl: String?,
    val role: String, // student, instructor
    val bio: String?,
    val joinedDate: Long,
    val lastUpdated: Long
)
