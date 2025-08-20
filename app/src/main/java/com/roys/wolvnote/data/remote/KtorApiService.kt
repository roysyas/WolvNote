package com.roys.wolvnote.data.remote

import com.roys.wolvnote.data.remote.model.TemperatureResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

private const val BASE_URL = "https://api.open-meteo.com/v1/forecast"

class KtorApiService @Inject constructor(
    private val client: HttpClient
): ApiService {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        timezone: String
    ): TemperatureResponse {
        return client.get(BASE_URL){
            url {
                parameters.append("latitude", latitude.toString())
                parameters.append("longitude", longitude.toString())
                parameters.append("timezone", timezone)
                parameters.append("current", "temperature_2m,weather_code")
            }
        }.body()
    }

}