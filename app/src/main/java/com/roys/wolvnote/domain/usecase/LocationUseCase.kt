package com.roys.wolvnote.domain.usecase

import android.location.Location
import com.roys.wolvnote.domain.repository.LocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Location? = locationRepository.getCurrentLocation()
}