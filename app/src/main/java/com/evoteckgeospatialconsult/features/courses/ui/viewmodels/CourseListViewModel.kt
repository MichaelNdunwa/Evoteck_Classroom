package com.evoteckgeospatialconsult.features.courses.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoteckgeospatialconsult.features.courses.data.models.CourseResponse
import com.evoteckgeospatialconsult.features.courses.domain.usecases.GetAllCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val getAllCoursesUseCase: GetAllCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CourseListUiState>(CourseListUiState.Loading)
    val uiState: StateFlow<CourseListUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                getAllCoursesUseCase().collect { courses ->
                    _uiState.value = CourseListUiState.Success(courses)
                }
            } catch (e: Exception) {
                _uiState.value = CourseListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class CourseListUiState {
    object Loading : CourseListUiState()
    data class Success(val courses: List<CourseResponse>) : CourseListUiState()
    data class Error(val message: String) : CourseListUiState()
}