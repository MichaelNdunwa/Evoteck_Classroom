package com.evoteckgeospatialconsult.core.auth.model

data class AuthUser(
    val uid: String,
    val email: String?,
    val name: String?,
    val photoUrl: String? = null,
    val isEmailVerified: Boolean = false
)