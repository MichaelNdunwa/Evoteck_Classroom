package com.evoteckgeospatialconsult.core.auth.extensions

import com.evoteckgeospatialconsult.core.auth.models.AuthUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomainUser(): AuthUser {
    return AuthUser(
        uid = uid,
        email = email,
        name = displayName,
        photoUrl = photoUrl?.toString()
    )
}

suspend fun FirebaseAuth.signOutAndClear() {
    this.signOut()
    // Clear any other data that needs to be cleared
}