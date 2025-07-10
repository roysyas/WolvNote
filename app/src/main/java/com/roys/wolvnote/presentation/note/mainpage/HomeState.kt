package com.roys.wolvnote.presentation.note.mainpage

import com.roys.wolvnote.data.database.NoteTable

data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isEmpty: Boolean = false,
    val isToggle: Boolean = false,
    val noteList: List<NoteTable> = listOf()
)