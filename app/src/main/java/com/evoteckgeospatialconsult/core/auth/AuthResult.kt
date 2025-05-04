package com.evoteckgeospatialconsult.core.auth

import com.evoteckgeospatialconsult.core.auth.model.AuthError
import com.evoteckgeospatialconsult.core.auth.models.AuthUser

sealed class AuthResult {
    data class Success(val user: AuthUser?) : AuthResult()
    data class Error(val error: AuthError) : AuthResult()
    object Loading : AuthResult()
}