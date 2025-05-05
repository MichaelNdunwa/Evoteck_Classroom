package com.evoteckgeospatialconsult.core.di

import android.content.Context
import androidx.room.Room
import com.evoteckgeospatialconsult.features.classroom.data.local.ClassroomDao
import com.evoteckgeospatialconsult.features.classroom.data.local.ClassroomDatabase
import com.evoteckgeospatialconsult.features.courses.data.local.CourseDao
import com.evoteckgeospatialconsult.features.courses.data.local.CourseDatabase
import com.evoteckgeospatialconsult.features.player.data.local.PlayerDao
import com.evoteckgeospatialconsult.features.player.data.local.PlayerDatabase
import com.evoteckgeospatialconsult.features.profile.data.local.ProfileDao
import com.evoteckgeospatialconsult.features.profile.data.local.ProfileDatabase
import com.evoteckgeospatialconsult.features.search.data.local.SearchDao
import com.evoteckgeospatialconsult.features.search.data.local.SearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCourseDatabase(@ApplicationContext context: Context): CourseDatabase {
        return  Room.databaseBuilder(context, CourseDatabase::class.java, "courses_db").build()
    }

    @Provides
    @Singleton
    fun provideCourseDao(database: CourseDatabase): CourseDao = database.courseDao()

    @Provides
    @Singleton
    fun provideClassroomDatabase(@ApplicationContext context: Context): ClassroomDatabase {
        return Room.databaseBuilder(context, ClassroomDatabase::class.java, "classroom_db").build()
    }

    @Provides
    @Singleton
    fun provideClassroomDao(database: ClassroomDatabase): ClassroomDao = database.classroomDao()

    @Provides
    @Singleton
    fun providePlayerDatabase(@ApplicationContext context: Context): PlayerDatabase {
        return Room.databaseBuilder(context, PlayerDatabase::class.java, "player_db").build()
    }

    @Provides
    @Singleton
    fun providePlayerDao(database: PlayerDatabase): PlayerDao = database.playerDao()

    @Provides
    @Singleton
    fun provideProfileDatabase(@ApplicationContext context: Context): ProfileDatabase {
        return Room.databaseBuilder(context, ProfileDatabase::class.java, "profile_db").build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(database: ProfileDatabase): ProfileDao = database.profileDao()

    @Provides
    @Singleton
    fun provideSearchDatabase(@ApplicationContext context: Context): SearchDatabase {
        return Room.databaseBuilder(context, SearchDatabase::class.java, "search_db").build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(database: SearchDatabase): SearchDao = database.searchDao()
}