package com.evoteckgeospatialconsult.features.classroom.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentClassroomBinding
import com.evoteckgeospatialconsult.features.classroom.ui.viewmodels.ClassroomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassroomFragment : Fragment() {

    private var _binding: FragmentClassroomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClassroomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassroomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentClassroomBinding.bind(view)

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