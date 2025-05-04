package com.evoteckgeospatialconsult.features.profile.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentProfileBinding
import com.evoteckgeospatialconsult.features.profile.ui.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

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