package com.evoteckgeospatialconsult.features.onboarding.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentWelcomeBinding
import com.evoteckgeospatialconsult.features.onboarding.data.OnboardingItem
import com.evoteckgeospatialconsult.features.onboarding.ui.OnboardingAdapter

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        // Setup onboarding ViewPager
        val onboardingItems = listOf(
            OnboardingItem(
                R.raw.onboarding_welcome,
                "Welcome to Evoteck Classroom",
                "Your journey to professional growth starts here. Discover thousands of expert-led courses in geospatial technology and beyond."
            ),
            OnboardingItem(
                R.raw.onboarding_learn_anywhere,
                "Learn at Your Own Pace",
                "Access expert-taught courses anytime, anywhere. Download lessons for offline viewing and learn on your schedule."
            ),
            OnboardingItem(
                R.raw.onboarding_diverse_content,
                "From Beginner to Expert",
                "Explore our library of GIS, remote sensing, and geospatial analytics courses tailored for every skill level and career state."
            ),
            OnboardingItem(
                R.raw.onboarding_hands_on_learning,
                "Hands-On Learning",
                "Apply what you learn through practical exercises, real-would projects, and interactive assessments designed by industry experts."
            ),
            OnboardingItem(
                R.raw.onboarding_community,
                "Connect & Grow Together",
                "Join discussions, share insights, and network with fellow professionals. Your geospatial learning community awaits."
            )
        )
        val adapter = OnboardingAdapter(onboardingItems)
        binding.vpOnboarding.adapter = adapter
        binding.dotsIndicator.attachTo(binding.vpOnboarding)
        binding.vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.btnGetStarted.visibility = if (position == onboardingItems.lastIndex) View.VISIBLE else View.GONE
            }
        })
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}