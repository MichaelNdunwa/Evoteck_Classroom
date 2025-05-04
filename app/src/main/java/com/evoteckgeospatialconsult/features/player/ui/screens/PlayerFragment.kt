package com.evoteckgeospatialconsult.features.player.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentPlayerBinding
import com.evoteckgeospatialconsult.features.player.ui.viewmodels.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupPlayer()
    }

    private fun setupObservers() {
        // TODO:  Setup LiveData observers
    }

    private fun setupPlayer() {
        // TODO: Setup ExoPlayer
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}