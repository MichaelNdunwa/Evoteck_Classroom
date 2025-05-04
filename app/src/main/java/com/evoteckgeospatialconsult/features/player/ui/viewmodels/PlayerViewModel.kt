package com.evoteckgeospatialconsult.features.player.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoteckgeospatialconsult.features.player.data.models.PlayerResponse
import com.evoteckgeospatialconsult.features.player.domain.usecases.GetLessonContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getLessonContentUseCase: GetLessonContentUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    fun loadLessonContent(lessonId: String) {
        viewModelScope.launch {
            try {
                getLessonContentUseCase(lessonId).collect { content ->
                    _uiState.value = PlayerUiState.Success(content)
                }
            } catch (e: Exception) {
                _uiState.value = PlayerUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class PlayerUiState {
    object Loading : PlayerUiState()
    data class Success(val content: PlayerResponse) : PlayerUiState()
    data class Error(val message: String) : PlayerUiState()
}