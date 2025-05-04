package com.evoteckgeospatialconsult.features.profile.data.models

data class ProfileResponse(
    val userId: String,
    val name: String,
    val email: String,
    val profilePictureUrl: String?,
    val role: String,
    val bio: String?,
    val joinedDate: Long,
    val lastUpdated: Long,
    val enrolledCourses: List<EnrolledCourse>,
    val teachingCourses: List<TeachingCourse>
) {
    data class EnrolledCourse(
        val courseId: String,
        val title: String,
        val progress: Int,
        val lastAccessed: Long
    )

    data class TeachingCourse(
        val courseId: String,
        val title: String,
        val totalStudents: Int,
        val rating: Float,
        val lastUpdated: Long
    )
}
