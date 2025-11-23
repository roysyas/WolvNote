package com.roys.wolvnote.domain.usecase

import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.domain.model.NoteTable
import com.roys.wolvnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(id: Int): Flow<Resource<NoteTable>> = flow {
        try {
            emit(Resource.Loading())
            val note = noteRepository.getNote(id)
            emit(Resource.Success(note))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}