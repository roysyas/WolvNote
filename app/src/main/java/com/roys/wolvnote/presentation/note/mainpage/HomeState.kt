package com.roys.wolvnote.presentation.note.mainpage

import android.location.Location
import com.roys.wolvnote.data.database.NoteTable
import com.roys.wolvnote.domain.model.CurrentWeather

data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isToggle: Boolean = false,
    val noteList: List<NoteTable> = listOf(),
    val location: Location? = null,
    val currentWeather: CurrentWeather? = null,
    val isGranted: Boolean = false,
    val weatherError: String = "",
    val weatherIsLoading: Boolean = false
)