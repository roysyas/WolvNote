package com.roys.wolvnote.domain.usecase

import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.domain.model.NoteTable
import com.roys.wolvnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(noteTable: NoteTable): Flow<Resource<NoteTable>> = flow {
        try {
            emit(Resource.Loading())
            noteRepository.deleteNote(noteTable)
            emit(Resource.Success(null))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}