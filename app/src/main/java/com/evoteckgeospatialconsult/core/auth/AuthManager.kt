package com.evoteckgeospatialconsult.core.auth

import com.evoteckgeospatialconsult.core.auth.extensions.toAuthError
import com.evoteckgeospatialconsult.core.auth.extensions.toDomainUser
import com.evoteckgeospatialconsult.core.datastore.SecurePreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthManager<FirebaseUser> @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val prefs: SecurePreferences
) {
    val currentUser: com.google.firebase.auth.FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success(result.user?.toDomainUser())
        } catch (e: Exception) {
            AuthResult.Error(e.toAuthError())
        }
    }
}