package com.evoteckgeospatialconsult.features.auth.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoteckgeospatialconsult.core.auth.AuthManager
import com.evoteckgeospatialconsult.core.auth.AuthResult
import com.facebook.AccessToken
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthManager
): ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow<Boolean>(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _authResult = MutableStateFlow<AuthResult?>(null)
    val authResult: StateFlow<AuthResult?> = _authResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.login(email, password)
            _authResult.value = result
            _isUserLoggedIn.value = result is AuthResult.Success
        }
    }

    fun signup(email: String, password: String, fullname: String) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.signup(email, password, fullname)
            _authResult.value = result
            _isUserLoggedIn.value = result is AuthResult.Success
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.loginWithGoogle(idToken)
            _authResult.value = result
            _isUserLoggedIn.value = result is AuthResult.Success
        }
    }

    fun loginWithFacebook(token: AccessToken) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.loginWithFacebook(token)
            _authResult.value = result
            _isUserLoggedIn.value = result is AuthResult.Success
        }
    }

    fun linkPendingCredential(pendingCredential: AuthCredential) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.linkPendingCredential(pendingCredential)
            _authResult.value = result
            _isUserLoggedIn.value = result is AuthResult.Success
        }
    }

    fun sendPasswordReset(email: String) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.sendPasswordReset(email)
            _authResult.value = result
        }
    }

    fun logout() {
        authManager.logout()
        _isUserLoggedIn.value = false
        _authResult.value = null
    }

}