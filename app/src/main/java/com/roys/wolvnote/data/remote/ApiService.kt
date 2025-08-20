package com.roys.wolvnote.data.remote

import com.roys.wolvnote.data.remote.model.TemperatureResponse

interface ApiService {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double, timezone: String): TemperatureResponse
}