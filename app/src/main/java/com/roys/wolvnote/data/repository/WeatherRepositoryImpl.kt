package com.roys.wolvnote.data.repository

import com.roys.wolvnote.data.remote.ApiService
import com.roys.wolvnote.data.remote.model.TemperatureResponse
import com.roys.wolvnote.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): WeatherRepository {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        timezone: String
    ): TemperatureResponse {
        return apiService.getCurrentWeather(latitude,longitude,timezone)
    }

}