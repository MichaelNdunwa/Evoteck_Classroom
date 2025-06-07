package com.evoteckgeospatialconsult.core.auth

import com.evoteckgeospatialconsult.core.auth.model.AuthError
import com.evoteckgeospatialconsult.core.auth.model.AuthUser
import com.google.firebase.auth.AuthCredential

sealed class AuthResult {
    data class Success(val user: AuthUser?) : AuthResult()
    data class Error(val error: AuthError) : AuthResult()
    data class RequiresLink(val message: String, val pendingCredential: AuthCredential) : AuthResult()
    object Loading : AuthResult()
}