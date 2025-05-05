package com.evoteckgeospatialconsult.features.courses.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evoteckgeospatialconsult.features.courses.data.local.CourseDao
import com.evoteckgeospatialconsult.features.courses.data.local.CourseEntity

@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}