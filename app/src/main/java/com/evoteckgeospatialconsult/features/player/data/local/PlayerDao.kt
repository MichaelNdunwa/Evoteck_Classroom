package com.evoteckgeospatialconsult.features.player.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM  player_progress WHERE  lesson_id = :lessonId")
    fun getLessonProgress(lessonId: String): Flow<PlayerProgressEntity?>

    @Query("SELECT * FROM player_progress WHERE course_id = :courseId")
    fun getCourseProgress(courseId: String): Flow<List<PlayerProgressEntity>>
}