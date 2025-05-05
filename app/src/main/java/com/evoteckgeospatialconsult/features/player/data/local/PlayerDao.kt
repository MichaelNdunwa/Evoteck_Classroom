package com.evoteckgeospatialconsult.features.player.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM  player_progress WHERE  lessonId = :lessonId")
    fun getLessonProgress(lessonId: String): Flow<PlayerProgressEntity?>

    @Query("SELECT * FROM player_progress WHERE courseId = :courseId")
    fun getCourseProgress(courseId: String): Flow<List<PlayerProgressEntity>>
}