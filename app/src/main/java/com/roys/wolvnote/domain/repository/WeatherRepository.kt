package com.roys.wolvnote.domain.repository

import com.roys.wolvnote.data.remote.model.TemperatureResponse

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double, timezone: String): TemperatureResponse
}