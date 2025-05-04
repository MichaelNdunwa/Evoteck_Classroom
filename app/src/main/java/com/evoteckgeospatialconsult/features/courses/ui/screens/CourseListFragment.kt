package com.evoteckgeospatialconsult.features.courses.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentCourseListBinding
import com.evoteckgeospatialconsult.features.courses.ui.viewmodels.CourseListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseListFragment : Fragment() {

    private var _binding: FragmentCourseListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CourseListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCourseListBinding.bind(view)

        setupObservers()
    }

    private fun setupObservers() {
        // TODO: Setup LiveData observers
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}