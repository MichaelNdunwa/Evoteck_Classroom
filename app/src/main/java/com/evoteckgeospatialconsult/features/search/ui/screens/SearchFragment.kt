package com.evoteckgeospatialconsult.features.search.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentSearchBinding
import com.evoteckgeospatialconsult.features.search.ui.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        setupObservers()
        setupSearchView()
    }

    private fun setupObservers() {
        // TODO: Setup LiveData observers
    }

    private fun setupSearchView() {
        // TODO: Setup search view and suggestions
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}