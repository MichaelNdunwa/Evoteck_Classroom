package com.evoteckgeospatialconsult.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoteckgeospatialconsult.core.auth.AuthManager
import com.evoteckgeospatialconsult.core.auth.AuthResult
import com.facebook.AccessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authManager: AuthManager
): ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow<Boolean>(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _authResult = MutableStateFlow<AuthResult?>(null)
    val authResult: StateFlow<AuthResult?> = _authResult

    // Expose a public flag to indicate whether graph has been set
    private val _hasSetGraph = MutableStateFlow(false)
    val hasSetGraph: StateFlow<Boolean> = _hasSetGraph

    fun markGraphAsSet() {
        _hasSetGraph.value = true
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.login(email, password)
            _authResult.value = result
            _isUserLoggedIn.value = result is AuthResult.Success
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val result = authManager.signup(email, password)
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

    fun logout() {
        authManager.logout()
        _isUserLoggedIn.value = false
        _authResult.value = null
    }

}