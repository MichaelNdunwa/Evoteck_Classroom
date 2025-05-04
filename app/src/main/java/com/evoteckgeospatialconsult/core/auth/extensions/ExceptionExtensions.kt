package com.evoteckgeospatialconsult.core.auth.extensions

import com.evoteckgeospatialconsult.core.auth.model.AuthError
import com.google.firebase.auth.FirebaseAuthException

fun Exception.toAuthError(): AuthError {
    return when (this) {
        is FirebaseAuthException -> AuthError(
            code = this.errorCode.hashCode(),
            message = this.message ?: "Firebase auth error"
        )
        else -> AuthError(
            code = 0,
            message = this.message ?: "Unknown authentication error"
        )
    }
}