package com.roys.wolvnote.presentation.note.notetaker

sealed interface NoteEvent {
    data class TitleUpdate(val title: String): NoteEvent
    data object InsertNote: NoteEvent
    data class ContentUpdate(val content: String): NoteEvent
}