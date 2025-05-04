package com.evoteckgeospatialconsult.features.player.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_progress")
data class PlayerProgressEntity(
    @PrimaryKey val lessonId: String,
    val courseId: String,
    val progress: Int, // percentage
    val lastPosition: Long, // in milliseconds
    val isCompleted: Boolean,
    val lastAccessedTimestamp: Long
)