package com.roys.wolvnote.presentation.note.mainpage

import com.roys.wolvnote.data.database.NoteTable

sealed interface HomeEvent {
    data class OnToggle(val toggle: Boolean): HomeEvent
    data class OnRefresh(val refresh: Boolean): HomeEvent
    data class OnDelete(val item: NoteTable): HomeEvent
}