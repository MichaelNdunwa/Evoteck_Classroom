package com.evoteckgeospatialconsult.features.search.data.models

data class SearchResponse(
    val courseId: String,
    val title: String,
    val description: String,
    val instructor: String,
    val rating: Float,
    val totalStudents: Int,
    val price: Double,
    val thumbnailUrl: String,
    val category: String
)
