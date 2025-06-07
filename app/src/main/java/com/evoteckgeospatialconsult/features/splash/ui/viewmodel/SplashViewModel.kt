package com.evoteckgeospatialconsult.features.splash.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _progress = MutableStateFlow(0)
    val progress: StateFlow<Int> = _progress

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        checkLoginStatus()
        startProgressAnimation()
    }

    private fun startProgressAnimation() {
        viewModelScope.launch {
            for (i in 0..100) {
                _progress.value = i
                delay(30L)
            }
        }
    }

    private fun checkLoginStatus() {
        val user = FirebaseAuth.getInstance().currentUser
        _isUserLoggedIn.value = user != null
    }
}