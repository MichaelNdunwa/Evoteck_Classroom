package com.evoteckgeospatialconsult.core.di

import com.evoteckgeospatialconsult.features.classroom.data.remote.ClassroomApi
import com.evoteckgeospatialconsult.features.courses.data.remote.CourseApi
import com.evoteckgeospatialconsult.features.player.data.remote.PlayerApi
import com.evoteckgeospatialconsult.features.profile.data.remote.ProfileApi
import com.evoteckgeospatialconsult.features.search.data.remote.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://evoteckgeospatialconsult.com/evoteck-classroom/"

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideClassroomApi(retrofit: Retrofit): ClassroomApi {
        return retrofit.create(ClassroomApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlayerApi(retrofit: Retrofit): PlayerApi {
        return retrofit.create(PlayerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}