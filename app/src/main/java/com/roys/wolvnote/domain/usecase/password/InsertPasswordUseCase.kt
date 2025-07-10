package com.roys.wolvnote.domain.usecase.password

import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.data.database.PasswordTable
import com.roys.wolvnote.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertPasswordUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
) {
    operator fun invoke(passwordTable: PasswordTable): Flow<Resource<PasswordTable>> = flow {
        try {
            emit(Resource.Loading())
            passwordRepository.insertPassword(passwordTable)
            emit(Resource.Success(null))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}