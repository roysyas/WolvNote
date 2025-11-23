package com.roys.wolvnote.domain.usecase.password

import com.roys.wolvnote.common.Resource
import com.roys.wolvnote.domain.model.PasswordTable
import com.roys.wolvnote.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPasswordUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
) {
    operator fun invoke(): Flow<Resource<PasswordTable>> = flow {
        try {
            emit(Resource.Loading())
            val password = passwordRepository.getPassword()
            emit(Resource.Success(password))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}