package com.evoteckgeospatialconsult.features.splash.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentSplashBinding
import com.evoteckgeospatialconsult.features.splash.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.lottieAnimationView.display
        binding.lottieAnimationView.playAnimation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)

        binding.lottieAnimationView.display
        binding.lottieAnimationView.playAnimation()
        observeProgress()
    }

    private fun observeProgress() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.progress.collectLatest { value ->
                binding.animatedProgressBar.progress = value
                binding.progressText.text = "Learning $value%"

                if (value == 100) {
                    delay(300)
                    navigateToNextScreen()
                }
            }
        }
    }

    private fun navigateToNextScreen() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isUserLoggedIn.collectLatest { loggedIn ->
                if (loggedIn) {
                    findNavController().navigate(R.id.action_splashFragment_to_courseListFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}