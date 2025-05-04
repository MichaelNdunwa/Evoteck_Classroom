package com.evoteckgeospatialconsult.features.classroom.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoteckgeospatialconsult.features.classroom.data.models.ClassroomResponse
import com.evoteckgeospatialconsult.features.classroom.domain.usecases.GetEnrolledCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(
    private val getEnrolledCoursesUseCase: GetEnrolledCourseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ClassroomUiState>(ClassroomUiState.Loading)
    val uiState: StateFlow<ClassroomUiState> = _uiState.asStateFlow()

    init {
        loadEnrolledCourses()
    }

    private fun loadEnrolledCourses() {
        viewModelScope.launch {
            try {
                getEnrolledCoursesUseCase().collect { courses ->
                    _uiState.value = ClassroomUiState.Success(courses)
                }
            } catch (e: Exception) {
                _uiState.value = ClassroomUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class ClassroomUiState {
    object Loading : ClassroomUiState()
    data class Success(val courses: List<ClassroomResponse>) : ClassroomUiState()
    data class Error(val message: String) : ClassroomUiState()
}