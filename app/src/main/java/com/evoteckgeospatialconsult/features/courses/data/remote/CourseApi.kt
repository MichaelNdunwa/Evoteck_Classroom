package com.evoteckgeospatialconsult.features.courses.data.remote

import com.evoteckgeospatialconsult.features.courses.data.models.CourseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseApi {

    @GET("courses")
    suspend fun getAllCourses(): List<CourseResponse>

    @GET("courses/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: String): CourseResponse

    @GET("courses/category/{category}")
    suspend fun getCoursesByCategory(@Path("category") category: String): List<CourseResponse>

    @GET("courses/search")
    suspend fun searchCourses(@Query("query") query: String): List<CourseResponse>
}