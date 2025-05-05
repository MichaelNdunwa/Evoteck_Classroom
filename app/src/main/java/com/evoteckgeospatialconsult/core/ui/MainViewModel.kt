package com.evoteckgeospatialconsult.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoteckgeospatialconsult.core.auth.AuthManager
import com.evoteckgeospatialconsult.core.auth.AuthResult
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

    // Expose a public flag to indicate whether graph has been set
    private val _hasSetGraph = MutableStateFlow(false)
    val hasSetGraph: StateFlow<Boolean> = _hasSetGraph

    fun markGraphAsSet() {
        _hasSetGraph.value = true
    }

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        _isUserLoggedIn.value = authManager.isLoggedIn()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authManager.login(email, password)
            if (result is AuthResult.Success) {
                _isUserLoggedIn.value = true
            } else {
                _isUserLoggedIn.value = false
            }
        }
    }

    fun logout() {
        authManager.logout()
        _isUserLoggedIn.value = false
    }

}