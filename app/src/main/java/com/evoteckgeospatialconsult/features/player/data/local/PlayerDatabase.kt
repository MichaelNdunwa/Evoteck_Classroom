package com.evoteckgeospatialconsult.features.player.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerProgressEntity::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}