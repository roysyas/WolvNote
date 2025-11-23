package com.roys.wolvnote.domain.usecase

import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.domain.model.NoteTable
import com.roys.wolvnote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<Resource<List<NoteTable>>> = flow {
        try {
            emit(Resource.Loading())
            val notes = noteRepository.getNotes()
            emit(Resource.Success(notes))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}