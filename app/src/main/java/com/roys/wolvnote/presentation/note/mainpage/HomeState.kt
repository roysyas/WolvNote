package com.roys.wolvnote.presentation.note.mainpage

import com.roys.wolvnote.domain.model.NoteTable

data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val isToggle: Boolean = false,
    val noteList: List<NoteTable> = listOf()
)