package com.evoteckgeospatialconsult.core.auth

import android.provider.Settings.Global.getString
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.core.auth.extensions.toAuthError
import com.evoteckgeospatialconsult.core.auth.extensions.toDomainUser
import com.evoteckgeospatialconsult.core.auth.model.AuthError
import com.evoteckgeospatialconsult.core.datastore.SecurePreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthManager @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val prefs: SecurePreferences
) {
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            prefs.saveUserId(result.user?.uid) // Save the logged-in user's ID securely
            AuthResult.Success(result.user?.toDomainUser())
        } catch (e: Exception) {
            AuthResult.Error(e.toAuthError())
        }
    }
    suspend fun signup(email: String, password: String, fullname: String): AuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            prefs.saveUserId(result.user?.uid)
            // Save to Firestore
            val uid = result.user?.uid
            if (uid != null) {
                val userData = mapOf(
                    "uid" to uid,
                    "email" to email,
                    "fullname" to fullname,
                    "createdAt" to System.currentTimeMillis()
                )
//                firestore.collection("evoteck_classroom_profiles").document(uid).set(userData).await()
                firestore.collection("evoteck_classroom").document("users")
                    .collection("students").document(uid).set(userData).await()

            }
            AuthResult.Success(result.user?.toDomainUser())
        } catch (e: Exception) {
            AuthResult.Error(e.toAuthError())
        }
    }
    suspend fun loginWithGoogle(idToken: String): AuthResult {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            prefs.saveUserId(result.user?.uid)
            AuthResult.Success(result.user?.toDomainUser())
        } catch (e: Exception) {
            AuthResult.Error(e.toAuthError())
        }
    }
    suspend fun loginWithFacebook(token: AccessToken): AuthResult {
        return try {
            val credential = FacebookAuthProvider.getCredential(token.token)
            val result = firebaseAuth.signInWithCredential(credential).await()
            prefs.saveUserId(result.user?.uid)
            AuthResult.Success(result.user?.toDomainUser())
        } catch (e: Exception) {
            if (e is FirebaseAuthUserCollisionException && e.updatedCredential != null) {
                AuthResult.RequiresLink(
                    "Account exists with a different credential. Please sign in with the original provider to link accounts.",
                    e.updatedCredential!!
                )
            } else {
                AuthResult.Error(e.toAuthError())
            }
        }
    }
    suspend fun linkPendingCredential(pendingCredential: AuthCredential): AuthResult {
        return try {
            val user = firebaseAuth.currentUser
            if (user != null) {
                val result = user.linkWithCredential(pendingCredential).await()
                AuthResult.Success(result.user?.toDomainUser())
            } else {
                AuthResult.Error(AuthError(-1, "You need to be signed in with your original provider before linking."))
            }
        } catch (e: Exception) {
            AuthResult.Error(e.toAuthError())
        }
    }
    suspend fun sendPasswordReset(email: String): AuthResult {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            AuthResult.Success(null)
        } catch (e: Exception) {
            AuthResult.Error(e.toAuthError())
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        prefs.clearUserId() // Clear the logged-in user's ID securely
    }
    fun isLoggedIn(): Boolean = currentUser != null
}