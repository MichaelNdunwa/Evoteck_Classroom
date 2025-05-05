package com.evoteckgeospatialconsult.features.profile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserProfileEntity::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}