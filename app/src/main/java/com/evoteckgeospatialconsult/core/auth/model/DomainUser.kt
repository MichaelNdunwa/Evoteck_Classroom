package com.evoteckgeospatialconsult.core.auth.model

data class DomainUser(
    val uid: String,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
    val isEmailVerified: Boolean
)