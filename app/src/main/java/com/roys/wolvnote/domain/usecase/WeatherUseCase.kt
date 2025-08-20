package com.roys.wolvnote.domain.usecase

import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.domain.model.CurrentWeather
import com.roys.wolvnote.domain.model.getWeather
import com.roys.wolvnote.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(latitude: Double, longitude: Double, timezone: String): Flow<Resource<CurrentWeather>> = flow {
        try {
            emit(Resource.Loading())
            val temperatureResponse = weatherRepository.getWeather(latitude,longitude,timezone)
            val currentWeather = CurrentWeather(
                temperature = temperatureResponse.current.temperature_2m.toString() + " " + temperatureResponse.current_units.temperature_2m,
                weather = getWeather(temperatureResponse.current.weather_code)
            )
            emit(Resource.Success(currentWeather))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}