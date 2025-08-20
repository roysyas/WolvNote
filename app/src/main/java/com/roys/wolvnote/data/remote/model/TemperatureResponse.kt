package com.roys.wolvnote.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class TemperatureResponse(
    val current: Current,
    val current_units: CurrentUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)