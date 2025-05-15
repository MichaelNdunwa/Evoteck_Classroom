package com.evoteckgeospatialconsult.features.onboarding.data

import androidx.annotation.RawRes

data class OnboardingItem(
    @RawRes val lottieRes: Int,
    val title: String,
    val description: String
)