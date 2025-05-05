package com.evoteckgeospatialconsult.features.classroom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClassroomProgressEntity::class], version = 1, exportSchema = false)
abstract class ClassroomDatabase : RoomDatabase() {
    abstract fun classroomDao(): ClassroomDao
}