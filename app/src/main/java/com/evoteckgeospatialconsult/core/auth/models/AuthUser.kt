package com.evoteckgeospatialconsult.core.auth.models

data class AuthUser(
    val uid: String,
    val email: String?,
    val name: String?,
    val photoUrl: String? = null,
    val isEmailVerified: Boolean = false
)