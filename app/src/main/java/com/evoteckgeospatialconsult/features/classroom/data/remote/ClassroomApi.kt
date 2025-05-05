package com.evoteckgeospatialconsult.features.classroom.data.remote

import com.evoteckgeospatialconsult.features.classroom.data.models.ClassroomResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClassroomApi {
    @GET("courses/enrolled")
    suspend fun getEnrolledCourses(): Response<List<ClassroomResponse>>

    @GET("courses/{courseId}/progress")
    suspend fun getCourseProgress(@Path("courseId") courseId: String): Response<ClassroomResponse>
}