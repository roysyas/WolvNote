package com.roys.wolvnote.data.repository

import com.roys.wolvnote.data.database.PasswordDao
import com.roys.wolvnote.domain.model.PasswordTable
import com.roys.wolvnote.domain.repository.PasswordRepository
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(private val passwordDao: PasswordDao): PasswordRepository {
    override suspend fun insertPassword(passwordTable: PasswordTable) {
        passwordDao.insertPassword(passwordTable)
    }

    override suspend fun getPassword(): PasswordTable? {
        return passwordDao.getPassword()
    }
}