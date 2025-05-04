package com.evoteckgeospatialconsult.core.ui

import androidx.lifecycle.ViewModel
import com.evoteckgeospatialconsult.core.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authManager: AuthManager<Any?>
): ViewModel() {
}